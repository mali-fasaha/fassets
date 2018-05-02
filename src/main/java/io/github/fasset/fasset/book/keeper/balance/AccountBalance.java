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

package io.github.fasset.fasset.book.keeper.balance;

import io.github.fasset.fasset.book.keeper.unit.money.Cash;

/**
 * Represents the amount and sign of the amount you could find in an account. Using this
 * you could say an account has 20 dollars on Credit side.
 *
 * @author edwin.njeru
 */
public class AccountBalance {

    private final Cash amount;
    private final AccountSide accountSide;

    public AccountBalance(Cash amount, AccountSide accountSide) {
        this.amount = amount;
        this.accountSide = accountSide;
    }

    public static AccountBalance newBalance(Cash amount, AccountSide accountSide) {

        return new AccountBalance(amount, accountSide);
    }

    public Cash getAmount() {
        return amount;
    }

    public AccountSide getAccountSide() {
        return accountSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountBalance that = (AccountBalance) o;

        if (!amount.equals(that.amount)) {
            return false;
        }
        return accountSide == that.accountSide;
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + accountSide.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return amount.getNumber().doubleValue() + " " + accountSide;
    }
}
