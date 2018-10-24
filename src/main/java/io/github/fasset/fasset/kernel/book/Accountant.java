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

import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.kernel.book.keeper.AccountAttribute;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

/**
 * Clients of this interface are able to run account-related sub-routines such creating accounts and entries
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface Accountant {

    /**
     * <p>createAccount.</p>
     *
     * @param accountAttributes a {@link java.util.Map} object.
     * @param currency          a {@link java.util.Currency} object.
     * @param purchaseDate      a {@link java.time.LocalDate} object.
     * @return a {@link io.github.fasset.fasset.kernel.book.keeper.Account} object.
     */
    Account createAccount(Map<AccountAttribute, String> accountAttributes, Currency currency, LocalDate purchaseDate);

    /**
     * <p>deleteAccount.</p>
     *
     * @param account a {@link io.github.fasset.fasset.kernel.book.keeper.Account} object.
     * @return a {@link io.github.fasset.fasset.kernel.book.keeper.Account} object.
     */
    Account deleteAccount(Account account);

    /**
     * <p>retrieveAccount.</p>
     *
     * @param accountNumber a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.kernel.book.keeper.Account} object.
     */
    Account retrieveAccount(String accountNumber);
}
