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

import io.github.fasset.fasset.book.keeper.Account;
import io.github.fasset.fasset.book.keeper.AccountAttribute;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

/**
 * Clients of this interface are able to run account-related sub-routines such creating accounts
 * and entries
 */
public interface Accountant {

    Account createAccount(Map<AccountAttribute,String> accountAttributes, Currency currency, LocalDate purchaseDate);

    Account deleteAccount(Account account);

    Account retrieveAccount(String accountNumber);
}
