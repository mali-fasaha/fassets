/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.book.keeper;

import io.github.fasset.fasset.kernel.book.AccountDomainModel;
import io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.kernel.book.keeper.state.AccountState;
import io.github.fasset.fasset.kernel.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.kernel.book.keeper.util.UnEnteredDetailsException;
import io.github.fasset.fasset.kernel.book.keeper.util.UntimelyBookingDateException;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.ghacupha.time.point.DateRange;
import io.github.ghacupha.time.point.SimpleDate;
import io.github.ghacupha.time.point.TimePoint;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.DEBIT;

/**
 * Implements the {@link io.github.fasset.fasset.kernel.book.keeper.Account} interface and maintains states for {@link java.util.Currency}, name, number, openingDate and {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide}. The AccountSide remains volatile, inorder to represent the
 * changing nature of the account as the {@link io.github.fasset.fasset.kernel.book.keeper.AccountingEntry} items are added. This is also assigned on initialization allowing the client to describe default {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide} of the {@link
 * Account}.
 * <p>
 * Implementation note : Some non-guaranteed care has been taken to make the Implementation as thread-safe as possible. This may not be obviously evident by the usual use of words like "synchronized"
 * et al. In fact synchronization would probably just slow us down. Instead what has been done is that the {@link java.util.Collection} of {@link io.github.fasset.fasset.kernel.book.keeper.AccountingEntry} items, which is the whole concept of this
 * Account pattern, has been implemented using a {@link java.util.List} interface implementation that creates a new copy of itself every time time a mutative process is carried out. It's iterator as a result is
 * guaranteed never to throw {@code ConcurrentModificationException} and it does not reflect additions, removals or changes to the list, once they have been created. The rest of the getters return new
 * instances of values of similar equivalence or copies of themselves this objects attributes will largely therefore remain unchanged.
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Entity(name = "Account")
@Table(name = "account")
public class Account extends AccountDomainModel<String> implements Comparable<Account> {

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

    //@Convert(converter = TimePointAttributeConverter.class)
    @Column
    private TimePoint openingDate;

    @Column
    @Enumerated
    private volatile AccountSide accountSide;

    @ElementCollection
    @MapKeyColumn(name = "attribute_name", length = 100)
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "account_attribute")
    @CollectionTable(name = "account_attributes", joinColumns = @JoinColumn(name = "account_id"))
    private Map<AccountAttribute, String> accountAttributes = new ConcurrentHashMap<>();

    @OneToMany(mappedBy = "account")
    private volatile List<AccountingEntry> entries = new CopyOnWriteArrayList<>();

    /**
     * This constructor will one day allow someone to implement the {@link List} interface with anything, including a database and assign the same to this {@link Account} making this object
     * persistent.
     *
     * @param name           Name of the account
     * @param accountSide    AccountSide to which this account belongs by default
     * @param currency       Currency to be used for all {@link AccountingEntry} items to be added to this account
     * @param accountDetails AccountDetails describes the basic nature of this account from business domain's perspective
     * @param entries        List collection of AccountingEntry items allowing assignment of a Collection interface for this account. One day this parameter will allow a dev to something like
     *                       implement the list interface with a back end like a database or some Restful service making changes in this account persistent.
     */
    @SuppressWarnings("unused")
    Account(final String name, final String number, AccountSide accountSide, final TimePoint openingDate, final Currency currency, Map<AccountAttribute, String> accountDetails,
            final List<AccountingEntry> entries) {
        this.currency = currency;
        this.accountSide = accountSide;
        this.accountAttributes = accountDetails;
        this.entries = entries;
        this.name = name;
        this.number = number;
        this.openingDate = openingDate;
    }

    /**
     * <p>Constructor for Account.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param number a {@link java.lang.String} object.
     * @param accountSide a {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide} object.
     * @param currency a {@link java.util.Currency} object.
     * @param openingDate a {@link io.github.ghacupha.time.point.TimePoint} object.
     */
    @SuppressWarnings("unused")
    public Account(final String name, final String number, final AccountSide accountSide, final Currency currency, final TimePoint openingDate) {
        this.name = name;
        this.number = number;
        this.currency = currency;
        this.accountSide = accountSide;
        this.openingDate = openingDate;
    }

    /**
     * <p>Constructor for Account.</p>
     */
    public Account() {
    }

    /**
     * Used to add additional attributes to the account
     *
     * @param accountAttribute Name of the attribute
     * @param value            value of the attribute
     */
    public void addAttribute(AccountAttribute accountAttribute, String value) {
        checkNotNull(value, "Sorry mate, you cannot add a null attribute");
        this.accountAttributes.put(accountAttribute, value);
    }

    /**
     * <p>getAttribute.</p>
     *
     * @param accountAttribute Name of attribute being searched for e.g. Owner, Contra a/c, Rereference
     * @return The value of the attribute
     * @throws io.github.fasset.fasset.kernel.book.keeper.util.UnEnteredDetailsException if any.
     */
    public String getAttribute(AccountAttribute accountAttribute) throws UnEnteredDetailsException {

        if (!this.accountAttributes.containsKey(accountAttribute)) {
            throw new UnEnteredDetailsException(String.format("account # %s does not contain field : %s", getId(), accountAttribute));
        }

        return this.accountAttributes.get(accountAttribute);
    }

    /**
     * @param accountingEntry {@link AccountingEntry} to be added to this
     * @throws MismatchedCurrencyException  when the AccountingEntry's currency is not similar to the account's currency
     * @throws UntimelyBookingDateException when the AccountingEntry's booking date is sooner than the account's opening date
     */
    void addEntry(AccountingEntry accountingEntry) throws MismatchedCurrencyException, UntimelyBookingDateException {

        log.debug("Adding accountingEntry {} to account : {}", accountingEntry, this);

        if (accountingEntry.getBookingDate()
                           .before(this.openingDate)) {

            String message = String.format("Opening date : %s . The accountingEntry date was %s", this.openingDate, accountingEntry.getBookingDate());
            throw new UntimelyBookingDateException("The booking date cannot be earlier than the account opening date : " + message);

        } else if (!this.currency.equals(accountingEntry.getAmount()
                                                        .getCurrency())) {

            String message = String.format("Currencies mismatched :Expected getCurrency : %s but found accountingEntry denominated in %s", this.currency.toString(), accountingEntry.getAmount()
                                                                                                                                                                                    .getCurrency());
            throw new MismatchedCurrencyException(message);

        } else {

            entries.add(accountingEntry); // done

            log.debug("AccountingEntry : {} has been added into account : {}", accountingEntry, this);
        }
    }

    /**
     * Returns the balance of the Account
     *
     * @param asAt {@link io.github.ghacupha.time.point.TimePoint} at which is Effective
     * @return {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance}
     */
    public AccountBalance balance(TimePoint asAt) {

        log.debug("Account balance enquiry raised as at {}, for account : {}", asAt, this);

        AccountBalance balance = appraisalDelegate.balance(new DateRange(this.openingDate, asAt));

        log.debug("Returning accounting balance for {} as at : {} as : {}", this, asAt, balance);

        return balance;
    }

    /**
     * Similar to the balance query for a given date except the date is provided through a simple {@code VarArgs} int argument
     *
     * @param asAt <p>The date as at when the {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance} we want is effective given in the following order:</p> <p>i) Year</p> <p>ii) Month</p> <p>iii) Date</p>
     * @return {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance} effective the date specified by the varargs
     */
    public AccountBalance balance(int... asAt) {

        AccountBalance balance = balance(new SimpleDate(asAt[0], asAt[1], asAt[2]));

        log.debug("Returning accounting balance for {} ,as at : {} as : {}", this, Arrays.toString(asAt), balance);

        return balance;
    }

    /**
     * <p>Getter for the field <code>currency</code>.</p>
     *
     * @return Currency of the account
     */
    public Currency getCurrency() {
        return Currency.getInstance(this.currency.getCurrencyCode());
    }

    /**
     * <p>Setter for the field <code>currency</code>.</p>
     *
     * @param currency a {@link java.util.Currency} object.
     */
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperationException("The currency for Account can only be set in the constructor");
    }

    /**
     * <p>Getter for the field <code>entries</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<AccountingEntry> getEntries() {

        return new CopyOnWriteArrayList<>(entries.parallelStream()
                                                 .collect(ImmutableListCollector.toImmutableList()));
    }

    /**
     * <p>Setter for the field <code>entries</code>.</p>
     *
     * @param entries a {@link java.util.List} object.
     */
    public void setEntries(List<AccountingEntry> entries) {
        throw new UnsupportedOperationException("Entries can only be entered through the #addEntry method");
    }

    TimePoint getOpeningDate() {
        return SimpleDate.newTimePoint(openingDate);
    }

    /**
     * <p>Setter for the field <code>openingDate</code>.</p>
     *
     * @param openingDate a {@link io.github.ghacupha.time.point.TimePoint} object.
     */
    public void setOpeningDate(TimePoint openingDate) {
        throw new UnsupportedOperationException("Dude! Can you imagine, setting the opening date for an account that's already opened?");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.name + " " + this.number;
    }

    /**
     * <p>Getter for the field <code>accountSide</code>.</p>
     *
     * @return Shows the side of the balance sheet to which this belongs which could be either {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide#DEBIT} or {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide#CREDIT} Implementation Note : As per implementation notes
     * this is for use only by the {@link io.github.fasset.fasset.kernel.book.keeper.AccountAppraisalDelegate} allowing inexpensive evaluation of the {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance} without causing circular reference. Otherwise anyone else who needs to
     * know the {@code AccountSide} of this needs to query the {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance} first, and from it acquire the {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide}. Also note that the object's {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide} is never
     * really exposed since this implementation is returning a value based on its current status.
     */
    public AccountSide getAccountSide() {

        // The original accountSide remains. No side effects
        return this.accountSide == DEBIT ? DEBIT : CREDIT;
    }

    /**
     * <p>Setter for the field <code>accountSide</code>.</p>
     *
     * @param accountSide a {@link io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide} object.
     */
    public void setAccountSide(final AccountSide accountSide) {

        this.accountSide = accountSide;
    }

    @SuppressWarnings("unused")
    Map<AccountAttribute, String> getAccountAttributes() {

        return Collections.unmodifiableMap(accountAttributes);
    }

    /**
     * <p>Setter for the field <code>accountAttributes</code>.</p>
     *
     * @param accountAttributes a {@link java.util.Map} object.
     */
    public void setAccountAttributes(Map<AccountAttribute, String> accountAttributes) {
        throw new UnsupportedOperationException("Kindly use the #addAttribute. This method only exists to make JPA happy :)");
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        throw new UnsupportedOperationException("Setting the name of the account is reserved for the constructor. This method only exists to make JPA happy :)");
    }

    /**
     * <p>Getter for the field <code>number</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNumber() {
        return number;
    }

    /**
     * <p>Setter for the field <code>number</code>.</p>
     *
     * @param number a {@link java.lang.String} object.
     */
    public void setNumber(String number) {
        throw new UnsupportedOperationException("Setting the number of the account is reserved for the constructor. This method only exists to make JPA happy :)");
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))} for all {@code x} and {@code y}.  (This implies that {@code x.compareTo(y)} must throw an exception iff {@code y.compareTo(x)} throws an
     * exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements the {@code Comparable} interface and violates this condition should clearly indicate this fact.  The
     * recommended language is "Note: this class has a natural ordering that is inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(final Account o) {

        return Comparator.comparing(Account::getNumber).compare(this, o);
    }
}
