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

import io.github.fasset.fasset.book.Account;
import io.github.fasset.fasset.book.AccountDomainModel;
import io.github.fasset.fasset.book.Entry;
import io.github.fasset.fasset.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.book.keeper.unit.money.Cash;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnEnteredDetailsException;
import io.github.fasset.fasset.book.keeper.util.UntimelyBookingDateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import java.util.Currency;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This model maps unit of data entered into an account. It contains {@code bookingDate} when the transaction
 * is posted, the {@code account} into which it is posted, the {@code narration}, the {@code amount} and the
 * {@code accountSide} into which we are posting. The rest of attributes are added into the {@code entryAttributes}
 * field.
 */
@Entity(name = "PersistentEntry")
@Table(name = "persistent_entry")
public class PersistentEntry extends AccountDomainModel<String> implements Entry {

    private static final Logger log = LoggerFactory.getLogger(PersistentEntry.class);

    // Ref TimePointAttributeConverter.class
    @Column
    private TimePoint bookingDate;

    private Account account;

    @Column
    private String narration;

    @Enumerated
    private AccountSide accountSide;

    // Ref CashAttributeConverter
    @Column
    private Cash amount;

    @ElementCollection
    @MapKeyColumn(name = "entry_attribute_name", length = 100)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "entry_attribute")
    @CollectionTable(name = "entry_attributes", joinColumns = @JoinColumn(name = "persistent_entry_id"))
    private Map<EntryAttribute, Object> entryAttributes = new ConcurrentHashMap<>();

    public PersistentEntry(TimePoint bookingDate, Account account, String narration, AccountSide accountSide, Cash amount) {
        this.bookingDate = bookingDate;
        this.account = account;
        this.narration = narration;
        this.accountSide = accountSide;
        this.amount = amount;
    }

    /**
     * Adds unstructured additional attributes as required into the entry
     *
     * @param entryAttribute {@link EntryAttribute} identity of the attribute in an entry
     * @param attribute      the object content being registered as an attribute
     */
    @Override
    public void addAttribute(EntryAttribute entryAttribute, Object attribute) {
        this.entryAttributes.put(entryAttribute, attribute);
    }

    /**
     * @param entryAttribute identification of the attribute we wish to obtain from the entry
     * @return Object containing the attribute value
     */
    @Override
    public Object getAttribute(EntryAttribute entryAttribute) throws UnEnteredDetailsException {

        if (!this.entryAttributes.containsKey(entryAttribute)) {
            throw new UnEnteredDetailsException(String.format("Exception: %s has not been added to entry : %s", entryAttribute.toString(), toString()));
        }

        return this.entryAttributes.get(entryAttribute);
    }

    /**
     * @return Currency of the monetary amounts to be save in this
     */
    @Override
    public Currency getCurrency() {
        return amount.getCurrency();
    }

    /**
     * @return {@link AccountSide} to which this Entry is aggregating the
     * Account balance
     */
    @Override
    public AccountSide getAccountSide() {
        return accountSide;
    }

    /**
     * @return Booking date of the Entry
     */
    @Override
    public TimePoint getBookingDate() {
        return bookingDate;
    }

    /**
     * @return The amount being posted into the Account and encapsulated
     * by the Entry
     */
    @Override
    public Cash getAmount() {
        return amount;
    }

    public void setBookingDate(TimePoint bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public void setAccountSide(AccountSide accountSide) {
        this.accountSide = accountSide;
    }

    public void setAmount(Cash amount) {
        this.amount = amount;
    }

    /**
     * Assigns this Entry with a specific account into which it is aggregated as
     * {@code AccountBalance}
     */
    @Override
    public void post() {

        try {
            log.debug("Adding entry : {} into account : {}", this, account);
            account.addEntry(this);
        } catch (UntimelyBookingDateException e) {
            log.error("Could not post the entry : {} into forAccount : {}", this, account, e.getStackTrace());

            log.error("Cause : the Entry booking date :{} is sooner than the forAccount's opening date {} ", bookingDate, account.getOpeningDate(), e.getStackTrace());
        } catch (MismatchedCurrencyException e) {
            log.error("Could not post the entry : {} into the forAccount : {} ", this, account, e.getStackTrace());
            log.error("Cause the entry's currency : {} does not match the forAccount's currency : {}", amount.getCurrency(), account.getCurrency(), e.getStackTrace());
        }

    }
}
