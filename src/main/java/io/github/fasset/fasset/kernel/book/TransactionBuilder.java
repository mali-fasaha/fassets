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
package io.github.fasset.fasset.kernel.book;

import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;

import java.util.List;

/**
 * Creates Transaction from a Collection of accounting Entries
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface TransactionBuilder {

    /**
     * <p>createTransaction.</p>
     *
     * @param entries a {@link java.util.List} object.
     * @return a {@link io.github.fasset.fasset.kernel.book.Transaction} object.
     */
    Transaction createTransaction(List<AccountingEntry> entries);
}
