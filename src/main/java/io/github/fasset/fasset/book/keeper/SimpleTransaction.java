/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.book.keeper;

import io.github.fasset.fasset.book.Transaction;
import io.github.fasset.fasset.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.book.keeper.unit.money.Cash;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;
import io.github.fasset.fasset.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnableToPostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static java.util.Collections.EMPTY_MAP;


/**
 * Immutable implementation of the {@link Transaction} interface once created nothing about it can change, except
 * addition of entries. The underlying {@link Collection} cannot be re-assigned once created, and is implemented by
 * {@link List} whose implementation involved a data structure that copies itself for every mutative procedure that
 * is done, in this case involving addition of {@link Entry} items. There is a boolean that says whether or not the
 * {@link Transaction} has been posted, which is dangerously non final, but is volatile nevertheless.
 *
 * @author edwin.njeru
 */
public final class SimpleTransaction implements Transaction {

    private static final Logger log = LoggerFactory.getLogger(SimpleTransaction.class);

    private final String label;

    private final TimePoint date;
    private final Currency currency;
    private final List<Entry> entries = new CopyOnWriteArrayList<>();
    private volatile boolean wasPosted;

    SimpleTransaction(String label, TimePoint date, Currency currency) {

        this.label = label;
        this.date = date;
        this.currency = currency;

        log.info("SimpleTransaction created {}", this);
    }

    private static Double mapCashToDouble(Entry entry) {
        return entry.getAmount().getNumber().doubleValue();
    }

    private static boolean predicateCredits(Entry entry) {
        boolean credit;
        log.trace("Checking if entry {} is credit ", entry);
        credit = entry.getAccountSide() == CREDIT;
        log.trace("Entry : {} is credit {}", entry, credit);
        return credit;
    }

    private static boolean predicateDebits(Entry entry) {
        boolean debit;
        log.trace("Checking if entry {} is debit ", entry);
        debit = entry.getAccountSide() == DEBIT;
        log.trace("Entry : {} is credit {}", entry, debit);
        return debit;
    }

    /**
     * The add method adds entries to the transaction provided the transaction has not already
     * been posted
     *
     * @param accountSide     to which the entry is being posted
     * @param amount          {@link Cash} amount being posted to the journal
     * @param account         {@link Account} into which the {@link Entry} is being added
     * @param narration       a brief narration of the entry
     * @param entryAttributes Map containing additional details about the entry being entered
     * @throws ImmutableEntryException when you addEntry to a posted transaction
     * @throws MismatchedCurrencyException when the {@code Account}, {@code Entry} or {@code Transaction} currencies
     * do not match
     */
    @Override
    public void addEntry(AccountSide accountSide, Cash amount, Account account, String narration, Map<EntryAttribute, String> entryAttributes)
        throws ImmutableEntryException, MismatchedCurrencyException {

        log.debug("Attempting to add entry {} amount of : {} in account : {} narration : {}", accountSide, amount, account, narration);
        // assign currency
        if (wasPosted) {
            throw new ImmutableEntryException("Cannot add entry to a transaction that's already posted");
        } else if (!account.getCurrency().equals(this.currency) || !amount.getCurrency().equals(this.currency)) {
            throw new MismatchedCurrencyException("Cannot add entry whose getCurrency differs to that of the transaction");
        } else {
            log.debug("Adding entry  : {} into transaction : {}", narration, this);
            Entry tempEntry = new Entry(date, account, narration, accountSide, amount);

            // Add attributes to the entry
            if (!entryAttributes.isEmpty()) {
                entryAttributes.forEach(tempEntry::addAttribute);
            }

            entries.add(tempEntry);

            log.debug("Entry {} has been added to {}", tempEntry, this);
        }
    }

    /**
     * Same method as {code Transaction.addEntry()} but with an empty map as description of the
     * entry. The {@code Entry} can therefore only be distinguished by its narration.
     * The add method adds entries to the transaction provided the transaction has not already
     * been posted
     *
     * @param accountSide to which the entry is being posted
     * @param amount      {@link Cash} amount being posted to the journal
     * @param account     {@link Account} into which the {@link Entry} is being added
     * @param narration   a brief narration of the entry
     * @throws ImmutableEntryException when you addEntry to a posted transaction
     * @throws MismatchedCurrencyException when the {@code Account}, {@code Entry} or {@code Transaction} currencies
     */
    @Override
    public void addEntry(AccountSide accountSide, Cash amount, Account account, String narration) throws ImmutableEntryException, MismatchedCurrencyException {
        this.addEntry(accountSide, amount, account, narration, EMPTY_MAP);
    }

    /**
     * Posts the transactions into respective {@link Account} items
     *
     * @throws UnableToPostException {@link UnableToPostException} thrown when the transaction is not balanced
     *                               That is if the items posted on the debit are more than those posted on the credit or vice versa.
     */
    @Override
    public void post() throws UnableToPostException {

        if (balanced() != 0) {

            if (balanced() > 0) {

                throw new UnableToPostException(String.format("The debits are more than credits by : %s", balanced()));

            } else {

                throw new UnableToPostException(String.format("The credits are more than debits by : %s", balanced() * -1));
            }

        } else {

            log.debug("Posting : {} entries ...", entries.size());

            entries.parallelStream().forEach(Entry::post);

            wasPosted = true;
        }
    }

    private double balanced() {

        double debits = entries.parallelStream().filter(SimpleTransaction::predicateDebits).map(SimpleTransaction::mapCashToDouble).reduce(0.00, (acc, val) -> acc + val);

        return debits - entries.parallelStream().filter(SimpleTransaction::predicateCredits).map(SimpleTransaction::mapCashToDouble).reduce(0.00, (acc, val) -> acc + val);
    }

    @Override
    public Set<Entry> getEntries() {

        return Collections.unmodifiableSet(new CopyOnWriteArraySet<>(entries));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleTransaction that = (SimpleTransaction) o;
        return wasPosted == that.wasPosted && Objects.equals(label, that.label) && Objects.equals(date, that.date) && Objects.equals(currency, that.currency) && Objects.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {

        return Objects.hash(label, date, wasPosted, currency, entries);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append('\'').append(label).append('\'');
        sb.append(", date=").append(date);
        sb.append(", currency=").append(currency);
        sb.append(", entries=").append(entries);
        sb.append('}');
        return sb.toString();
    }
}
