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

package io.github.fasset.fasset.book;

import io.github.fasset.fasset.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UntimelyBookingDateException;

import java.util.Currency;
import java.util.List;

/**
 * A collection of {@link Entry} items.
 * @implNote The specification of this interface hides the fact that the {@link Account} contains an {@link AccountSide}
 * field. This reduces excessive of the implementation internals, but if the implementation does not contain
 * an {@link AccountSide} then the point of this interface is moot. Therefore the only to safely access the
 * {@link AccountSide} is through the {@link AccountBalance}.
 *
 * @author edwin.njeru
 */
public interface Account {

    /**
     *
     * @param entry {@link Entry} to be added to this
     */
    void addEntry(Entry entry) throws MismatchedCurrencyException, UntimelyBookingDateException;

    /**
     * Returns the balance of the Account
     * @param asAt {@link TimePoint} at which is Effective
     * @return {@link AccountBalance}
     */
    AccountBalance balance(TimePoint asAt);

    /**
     * Similar to the balance query for a given date except the date is provided through a
     * simple varags int argument
     *
     * @param asAt The date as at when the {@link AccountBalance} we want is effective given
     *             in the following order
     *             i) Year
     *             ii) Month
     *             iii) Date
     * @return {@link AccountBalance} effective the date specified by the varargs
     */
    AccountBalance balance(int... asAt);

    /**
     *
     * @return Currency of the account
     */
    Currency getCurrency();

    /**
     *
     * @return {@link TimePoint} date when the account was opened
     */
    TimePoint getOpeningDate();

    /**
     * @implSpec As per implementation notes this is for use only by the {@code AccountAppraisalDelegate}
     * allowing inexpensive evaluation of the {@link AccountBalance} without causing circular reference. Otherwise anyone else who needs
     * to know the {@code AccountSide} of this needs to query the {@link AccountBalance} first, and from it acquire the {@link AccountSide}
     *
     * @return Shows the side of the balance sheet to which this belongs which could be either
     * {@code AccountSide#DEBIT} or {@code AccountSide#CREDIT}
     */
    AccountSide getAccountSide();

    /**
     *
     * @return Returns this object's current copy of the {@link Entry} items
     */
    List<Entry> getEntries();

    void setAccountSide(AccountSide accountSide);
}
