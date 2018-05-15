/**
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
package io.github.fasset.fasset.book.keeper;

import io.github.fasset.fasset.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import io.github.fasset.fasset.book.keeper.unit.time.ReadableDate;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnEnteredDetailsException;
import io.github.fasset.fasset.book.keeper.util.UntimelyBookingDateException;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;
import java.util.Map;

import static io.github.fasset.fasset.book.keeper.AccountAttribute.Account_Type;
import static io.github.fasset.fasset.book.keeper.AccountAttribute.Contra_Account;
import static io.github.fasset.fasset.book.keeper.AccountAttribute.General_Ledger;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.*;

public class AccountUnitTest {

    private Account account;

    @Before
    public void setUp() throws Exception {

        account = new Account("Test Account",
            "TST001",
            DEBIT,
            Currency.getInstance("USD"),
            ReadableDate.on(2018,12,20));

        account.addAttribute(General_Ledger,"4875");
    }

    @Test(expected = UnEnteredDetailsException.class)
    public void youCannotGetUnenteredDetails() throws Exception {

        account.addAttribute(Contra_Account,"Accumulated Test Account");

        assertEquals("Asset Account",account.getAttribute(Account_Type));
    }

    @Test
    public void addAttribute() throws Exception {

        account.addAttribute(Contra_Account,"Accumulated Test Account");

        assertEquals("Accumulated Test Account",account.getAttribute(Contra_Account));
    }

    @Test
    public void addEntry() throws Exception {

        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,21),account,"AccountingEntry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,23),account,"AccountingEntry Test 2",DEBIT, HardCash.dollar(110.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,24),account,"AccountingEntry Test 3",DEBIT, HardCash.dollar(95.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,28),account,"AccountingEntry Test 4",DEBIT, HardCash.dollar(90.23)));

        assertEquals(new AccountBalance(HardCash.dollar(320.69),DEBIT),account.balance(2018,12,25));
    }

    @Test(expected = UntimelyBookingDateException.class)
    public void bookingDateOnlyAfterAccountOpening() throws Exception {

        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,21),account,"AccountingEntry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,24),account,"AccountingEntry Test 3",DEBIT, HardCash.dollar(95.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,19),account,"AccountingEntry Test 4",DEBIT, HardCash.dollar(90.23)));

        // Exception thrown no need for assertion
    }

    @Test(expected = MismatchedCurrencyException.class)
    public void entryCurrencyMustMatchAccount() throws Exception {

        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,21),account,"AccountingEntry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,24),account,"AccountingEntry Test 3",DEBIT, HardCash.shilling(95.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,19),account,"AccountingEntry Test 4",DEBIT, HardCash.dollar(90.23)));// test never reaches here

        // Exception thrown no need for assertion
    }

    @Test
    public void balance() throws Exception {

        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,20),account,"AccountingEntry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,23),account,"AccountingEntry Test 2",DEBIT, HardCash.dollar(110.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,24),account,"AccountingEntry Test 3",DEBIT, HardCash.dollar(95.23)));
        account.addEntry(new AccountingEntry(SimpleDate.on(2018,12,26),account,"AccountingEntry Test 4",DEBIT, HardCash.dollar(90.23)));

        assertEquals(new AccountBalance(HardCash.dollar(410.92),DEBIT),account.balance(2018,12,27));

    }

    @Test
    public void openingDateRemainsUnchanged() throws Exception {

        TimePoint openingDate = account.getOpeningDate();

        assertEquals(SimpleDate.on(2018,12,20),openingDate);

        // Adding 50 days to the opening date of the account
        openingDate.addDays(50);

        // Opening date should not have changed
        assertEquals(SimpleDate.on(2018,12,20),openingDate);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void accountAttributesAreImmutable() throws Exception {

        Map<AccountAttribute,String> attributes = account.getAccountAttributes();

        assertEquals("4875",attributes.get(General_Ledger));

        attributes.put(General_Ledger,"4846");

        assertEquals("4875",attributes.get(General_Ledger));
    }
}