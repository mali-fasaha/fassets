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

package io.github.fasset.fasset.book.keeper.service;

import io.github.fasset.fasset.book.Transaction;
import io.github.fasset.fasset.book.keeper.Account;
import io.github.fasset.fasset.book.keeper.AccountingTransaction;
import io.github.fasset.fasset.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.book.keeper.balance.AccountSide;
import io.github.fasset.fasset.book.keeper.unit.money.Cash;
import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.book.keeper.unit.time.TimePoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {

    private static final Currency KES = Currency.getInstance("KES");

    @Qualifier("accountService")
    @Autowired
    private AccountService persistentAccountService;

    private TimePoint openingDate = SimpleDate.on(2018, 2, 1);

    private Account testAccount;

    private Account computers;
    private Account sundryDebtorsAccount;
    private Account cashAccount;

    int computersId;
    int sundryDebtorsId;
    int cashAccountId;

    @Before
    public void setUp() throws Exception {

        testAccount = new Account("Computers", "001", DEBIT, KES, openingDate);

        computers = persistentAccountService.saveAccount(testAccount);
        sundryDebtorsAccount = persistentAccountService.saveAccount(new Account("Sundry Debtor's Account", "002", DEBIT, KES, openingDate));
        cashAccount = persistentAccountService.saveAccount(new Account("Cash Account", "003", CREDIT, KES, openingDate));

        computersId = computers.getId();
        sundryDebtorsId = sundryDebtorsAccount.getId();
        cashAccountId = cashAccount.getId();

    }

    @Test
    public void serviceOutletBriefRepositoryWorks() throws Exception {

        assertNotNull(persistentAccountService.saveAccount(new Account()));
    }

    @Test
    public void dataRecallFromDB() throws Exception {

        assertEquals("Computers", persistentAccountService.fetchAccountById(computersId).getName());
        assertEquals("001", persistentAccountService.fetchAccountById(computersId).getNumber());
        assertEquals(DEBIT, persistentAccountService.fetchAccountById(computersId).getAccountSide());
        assertEquals(Currency.getInstance("KES"), persistentAccountService.fetchAccountById(computersId).getCurrency());

        assertEquals("Sundry Debtor's Account", persistentAccountService.fetchAccountById(sundryDebtorsId).getName());
        assertEquals("002", persistentAccountService.fetchAccountById(sundryDebtorsId).getNumber());
        assertEquals(DEBIT, persistentAccountService.fetchAccountById(sundryDebtorsId).getAccountSide());
        assertEquals(KES, persistentAccountService.fetchAccountById(sundryDebtorsId).getCurrency());

        assertEquals("Cash Account", persistentAccountService.fetchAccountById(cashAccountId).getName());
        assertEquals("003", persistentAccountService.fetchAccountById(cashAccountId).getNumber());
        assertEquals(CREDIT, persistentAccountService.fetchAccountById(cashAccountId).getAccountSide());
        assertEquals(KES, persistentAccountService.fetchAccountById(cashAccountId).getCurrency());
    }

    @Test
    public void entriesRecallFromDB() throws Exception {

        payForNewComputer();

        assertEquals(balance(shilling(350.23),DEBIT), persistentAccountService.fetchAccountById(computersId).balance(SimpleDate.now()));
        assertEquals(balance(shilling(150.23),CREDIT), persistentAccountService.fetchAccountById(sundryDebtorsId).balance(SimpleDate.now()));
        assertEquals(balance(shilling(200),CREDIT), persistentAccountService.fetchAccountById(cashAccountId).balance(SimpleDate.now()));

    }

    @Test
    public void entriesRecallAfterAcSideReversal() throws Exception {
        payForNewComputer();
        reQuisitionForChairs();

        assertEquals(balance(shilling(350.23),DEBIT), persistentAccountService.fetchAccountById(computersId).balance(SimpleDate.now()));
        assertEquals(balance(shilling(330),DEBIT), persistentAccountService.fetchAccountById(sundryDebtorsId).balance(SimpleDate.now()));
        assertEquals(balance(shilling(660),CREDIT), persistentAccountService.fetchAccountById(cashAccountId).balance(SimpleDate.now()));

    }

    private void reQuisitionForChairs() throws Exception {
        Transaction buysChairs = AccountingTransaction.create("Buy chairs",SimpleDate.of(2018,4,20),KES);
        Account taxAccount = new Account("Vat Account","211",CREDIT,KES,SimpleDate.of(2018,4,20));

        buysChairs.addEntry(DEBIT,shilling(480.23),sundryDebtorsAccount,"Furniture Suppliers invoice 218");
        buysChairs.addEntry(CREDIT,shilling(20.23),taxAccount,"16% Vat for furniture suppliers");
        buysChairs.addEntry(CREDIT,shilling(460.00),cashAccount,"Cash IFO Furniture Suppliers");

        buysChairs.post();
    }

    private void payForNewComputer() throws Exception {
        Transaction purchaseComputers = AccountingTransaction.create("Purchase Computers", SimpleDate.of(2018, 3, 31), KES);

        purchaseComputers.addEntry(DEBIT, shilling(350.23), computers, "Invoice100");
        purchaseComputers.addEntry(CREDIT,shilling(150.23),sundryDebtorsAccount,"Accounting for expense Id 100");
        purchaseComputers.addEntry(CREDIT,shilling(200),cashAccount,"Cash IFO Computer Suppliers");

        purchaseComputers.post();
    }

    private static Cash shilling(double amount){
        return HardCash.shilling(amount);
    }

    private static AccountBalance balance(Cash ammount, AccountSide accountSide){

        return AccountBalance.newBalance(ammount,accountSide);
    }
}