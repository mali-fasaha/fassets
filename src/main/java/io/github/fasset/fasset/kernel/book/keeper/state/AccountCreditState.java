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
package io.github.fasset.fasset.kernel.book.keeper.state;

import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide;
import io.github.ghacupha.cash.Cash;

import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.DEBIT;

/**
 * This class represents the Account's state when it is in CREDIT balance
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class AccountCreditState implements AccountState {

    private Account account;

    /**
     * <p>Constructor for AccountCreditState.</p>
     *
     * @param account a {@link io.github.fasset.fasset.kernel.book.keeper.Account} object.
     */
    public AccountCreditState(Account account) {
        this.account = account;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Get AccountBalance given the sum of debits and sum of credits
     */
    @Override
    public AccountBalance getAccountBalance(final Cash debits, final Cash credits) {

        if (credits.isMoreThan(debits)) {
            return new AccountBalance(credits.minus(debits).abs(), account.getAccountSide());
        }

        // The journal side of the account changes to DEBIT
        account.setAccountSide(DEBIT);

        return new AccountBalance(credits.minus(debits).abs(), DEBIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountSide getAccountSide() {

        return account.getAccountSide();
    }
}
