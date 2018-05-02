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

import io.github.fasset.fasset.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.book.keeper.unit.money.Cash;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;

import java.util.Currency;

/**
 * Unit of Account pattern implementation
 *
 * @author edwin.njeru
 */
public interface Entry {

    /**
     *
     * @return Currency of the monetary amounts to be save in this
     */
    Currency getCurrency();

    /**
     *
     * @return {@link AccountSide} to which this Entry is aggregating the
     * Account balance
     */
    AccountSide getAccountSide();

    /**
     *
     * @return Booking date of the Entry
     */
    TimePoint getBookingDate();

    /**
     *
     * @return The amount being posted into the Account and encapsulated
     * by the Entry
     */
    Cash getAmount();

    /**
     * Assigns this Entry with a specific account into which it is aggregated as
     * {@code AccountBalance}
     */
    void post();
}
