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

import io.github.fasset.fasset.book.AccountDomainModel;
import io.github.fasset.fasset.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.book.keeper.state.AccountState;
import io.github.fasset.fasset.book.keeper.unit.time.DateRange;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.TimePointAttributeConverter;
import io.github.fasset.fasset.book.keeper.util.UnEnteredDetailsException;
import io.github.fasset.fasset.book.keeper.util.UntimelyBookingDateException;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;

/**
 * Implements the {@link Account} interface and maintains states for {@link Currency}, name, number, openingDate and
 * {@link AccountSide}. The AccountSide remains volatile, inorder to represent the changing nature of the account as the
 * {@link Entry} items are added. This is also assigned on initialization allowing the client to describe
 * default {@link AccountSide} of the {@link Account}.
 *
 * @author edwin.njeru
 * @implNote Some non-guaranteed care has been taken to make the Implementation as thread-safe as possible. This may not
 * be obviously evident by the usual use of words like "synchronized" et al. In fact synchronization would probably just
 * slow us down. Instead what has been done is that the {@link Collection} of {@link Entry} items, which is the whole
 * concept of this Account pattern, has been implemented using a {@link List} interface implementation that creates a new
 * copy of itself every time time a mutative process is carried out. It's iterator as a result is guaranteed never to throw
 * {@code ConcurrentModificationException} and it does not reflect additions, removals or changes to the list, once they
 * have been created. The rest of the getters return new instances of values of similar equivalence or copies of themselves
 * this objects attributes will largely therefore remain unchanged.
 *
 * @author edwin.njeru
 */
@Entity(name = "Account")
@Table(name = "account")
public class Account extends AccountDomainModel<String> {

    @Transient
    private static final Logger log = LoggerFactory.getLogger(Account.class);

    @Transient
    private final AccountAppraisalDelegate appraisalDelegate = new AccountAppraisalDelegate(this);

    @SuppressWarnings("unused")
    @Transient
    private AccountState accountState;

    @Column
    private Currency currency;

    @Column
    private String name;

    @Column
    private String number;

    @Column
    @Convert(converter = TimePointAttributeConverter.class)
    private TimePoint openingDate;

    @Column
    @Enumerated
    private volatile AccountSide accountSide;

    @ElementCollection
    @MapKeyColumn(name="attribute_name", length = 100)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "account_attribute")
    @CollectionTable(name="account_attributes", joinColumns = @JoinColumn(name = "account_id"))
    private Map<AccountAttribute, String> accountAttributes = new ConcurrentHashMap<>();

    @OneToMany(mappedBy = "account")
    private volatile List<Entry> entries = new CopyOnWriteArrayList<>();

    /**
     * This constructor will one day allow someone to implement the {@link List} interface with anything,
     * including a database and assign the same to this {@link Account} making this object persistent.
     *
     * @param accountSide    {@link AccountSide} to which this account belongs by default
     * @param currency       {@link Currency} to be used for all {@link Entry} items to be added to this account
     * @param accountDetails {@code AccountDetails} describes the basic nature of this account from business domain's perspective
     * @param entries        {@link List<Entry>} collection allowing assignment of a Collection interface for this account. One day this
     *                       parameter will allow a dev to something like implement the list interface with a back end
     *                       like a database or some Restful service making changes in this account persistent.
     */
    @SuppressWarnings("unused")
    Account(final String name, final String number, AccountSide accountSide, final TimePoint openingDate, final Currency currency, Map<AccountAttribute, String> accountDetails, final
    List<Entry> entries) {
        this.currency = currency;
        this.accountSide = accountSide;
        this.accountAttributes = accountDetails;
        this.entries = entries;
        this.name = name;
        this.number = number;
        this.openingDate = openingDate;
    }

    @SuppressWarnings("unused")
    public Account(final String name, final String number, final AccountSide accountSide, final Currency currency, final TimePoint openingDate) {
        this.name = name;
        this.number = number;
        this.currency = currency;
        this.accountSide = accountSide;
        this.openingDate = openingDate;
    }

    public Account() {
    }

    /**
     * Used to add additional attributes to the account
     *
     * @param accountAttribute Name of the attribute
     * @param value            value of the attribute
     */
    public void addAttribute(AccountAttribute accountAttribute, String value) {
        this.accountAttributes.put(accountAttribute,value);
    }

    /**
     * @param accountAttribute Name of attribute being searched for e.g. Owner, Contra a/c, Rereference
     * @return The value of the attribute
     */
    public String getAttribute(AccountAttribute accountAttribute) throws UnEnteredDetailsException {

        if(!this.accountAttributes.containsKey(accountAttribute)){
            throw new UnEnteredDetailsException(String.format("account # %s does not contain field : %s",getId(),accountAttribute));
        }

        return this.accountAttributes.get(accountAttribute);
    }

    /**
     * @param entry {@link Entry} to be added to this
     */
    public void addEntry(Entry entry) throws MismatchedCurrencyException, UntimelyBookingDateException {

        log.debug("Adding entry {} to account : {}", entry,this);

        if (entry.getBookingDate().before(this.openingDate)) {

            String message = String.format("Opening date : %s . The entry date was %s", this.openingDate, entry.getBookingDate());
            throw new UntimelyBookingDateException("The booking date cannot be earlier than the account opening date : " + message);

        } else if (!this.currency.equals(entry.getAmount().getCurrency())) {

            String message = String.format("Currencies mismatched :Expected getCurrency : %s but found entry denominated in %s", this.currency.toString(), entry.getAmount().getCurrency());
            throw new MismatchedCurrencyException(message);

        } else {

            entries.add(entry); // done

            log.debug("Entry : {} has been added into account : {}", entry, this);
        }
    }

    /**
     * Returns the balance of the Account
     *
     * @param asAt {@link TimePoint} at which is Effective
     * @return {@link AccountBalance}
     */
    public AccountBalance balance(TimePoint asAt) {

        log.debug("Account balance enquiry raised as at {}, for account : {}", asAt, this);

        AccountBalance balance = appraisalDelegate.balance(new DateRange(this.openingDate, asAt));

        log.debug("Returning accounting balance for {} as at : {} as : {}", this, asAt, balance);

        return balance;
    }

    /**
     * Similar to the balance query for a given date except the date is provided through a
     * simple {@code VarArgs} int argument
     *
     * @param asAt <p>The date as at when the {@link AccountBalance} we want is effective given
     *             in the following order:</p>
     *             <p>i) Year</p>
     *             <p>ii) Month</p>
     *             <p>iii) Date</p>
     * @return {@link AccountBalance} effective the date specified by the varargs
     */
    public AccountBalance balance(int... asAt) {

        AccountBalance balance = balance(new SimpleDate(asAt[0], asAt[1], asAt[2]));

        log.debug("Returning accounting balance for {} ,as at : {} as : {}", this, Arrays.toString(asAt), balance);

        return balance;
    }

    /**
     * @return Currency of the account
     */
    public Currency getCurrency() {
        return Currency.getInstance(this.currency.getCurrencyCode());
    }

    public List<Entry> getEntries() {

        return new CopyOnWriteArrayList<>(
            entries
                .parallelStream()
                .collect(
                    ImmutableListCollector.toImmutableList()));
    }

    public TimePoint getOpeningDate() {
        return SimpleDate.newTimePoint(openingDate);
    }

    @Override
    public String toString() {
        return this.name+" "+this.number;
    }

    /**
     * @return Shows the side of the balance sheet to which this belongs which could be either
     * {@link AccountSide#DEBIT} or {@link AccountSide#CREDIT}
     * @implSpec As per implementation notes this is for use only by the {@link AccountAppraisalDelegate}
     * allowing inexpensive evaluation of the {@link AccountBalance} without causing circular reference. Otherwise anyone else who needs
     * to know the {@code AccountSide} of this needs to query the {@link AccountBalance} first, and from it acquire the {@link AccountSide}.
     * Also note that the object's {@link AccountSide} is never really exposed since this implementation is returning a value based on its
     * current status.
     */
    public AccountSide getAccountSide() {

        // The original accountSide remains. No side effects
        return this.accountSide == DEBIT ? DEBIT : CREDIT;
    }

    @SuppressWarnings("unused")
    public Map<AccountAttribute, String> getAccountAttributes() {

        return accountAttributes;
    }

    public void setAccountSide(final AccountSide accountSide) {

        this.accountSide = accountSide;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOpeningDate(TimePoint openingDate) {
        this.openingDate = openingDate;
    }

    public void setAccountAttributes(Map<AccountAttribute, String> accountAttributes) {
        this.accountAttributes = accountAttributes;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Account account = (Account) o;

        if (appraisalDelegate != null ? !appraisalDelegate.equals(account.appraisalDelegate) : account.appraisalDelegate != null) {
            return false;
        }
        if (accountState != null ? !accountState.equals(account.accountState) : account.accountState != null) {
            return false;
        }
        if (!currency.equals(account.currency)) {
            return false;
        }
        if (!name.equals(account.name)) {
            return false;
        }
        if (number != null ? !number.equals(account.number) : account.number != null) {
            return false;
        }
        if (!openingDate.equals(account.openingDate)) {
            return false;
        }
        if (accountSide != account.accountSide) {
            return false;
        }
        if (accountAttributes != null ? !accountAttributes.equals(account.accountAttributes) : account.accountAttributes != null) {
            return false;
        }
        return entries != null ? entries.equals(account.entries) : account.entries == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (appraisalDelegate != null ? appraisalDelegate.hashCode() : 0);
        result = 31 * result + (accountState != null ? accountState.hashCode() : 0);
        result = 31 * result + currency.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + openingDate.hashCode();
        result = 31 * result + accountSide.hashCode();
        result = 31 * result + (accountAttributes != null ? accountAttributes.hashCode() : 0);
        result = 31 * result + (entries != null ? entries.hashCode() : 0);
        return result;
    }
}
