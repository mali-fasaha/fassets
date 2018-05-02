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
import io.github.fasset.fasset.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnableToPostException;

import java.util.Set;

/**
 * A collection of {@link Entry} items being posted at the same time
 */
public interface Transaction {

    /**
     * @param accountSide {@link io.github.fasset.fasset.book.keeper.balance.AccountSide} in which the entry is for
     * @param account     {@link Account} into which the {@link Entry} is posted
     * @param entry       {@link Entry} being added to the above account for this transaction
     */
    void addEntry(AccountSide accountSide, Account account, Entry entry) throws ImmutableEntryException, MismatchedCurrencyException;

    /**
     * Adds the {@link Entry} items into the accounts involved in this {@link Transaction}
     */
    void post() throws UnableToPostException, ImmutableEntryException;

    /**
     * @return Collection of {@link Entry} items in the Transaction
     */
    Set<Entry> getEntries();
}
