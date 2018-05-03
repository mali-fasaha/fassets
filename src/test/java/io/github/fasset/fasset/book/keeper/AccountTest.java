package io.github.fasset.fasset.book.keeper;

import io.github.fasset.fasset.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import io.github.fasset.fasset.book.keeper.unit.time.ReadableDate;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnEnteredDetailsException;
import io.github.fasset.fasset.book.keeper.util.UntimelyBookingDateException;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

import static io.github.fasset.fasset.book.keeper.AccountAttribute.Account_Type;
import static io.github.fasset.fasset.book.keeper.AccountAttribute.Contra_Account;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.*;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception {

        account = new Account("Test Account",
            "TST001",
            DEBIT,
            Currency.getInstance("USD"),
            ReadableDate.on(2018,12,20));
    }

    @Test(expected = UnEnteredDetailsException.class)
    public void youCannotGetUnenteredDetails() throws Exception {

        account.addAttribute(Contra_Account,"Accumulated Test Account");

        assertEquals("Asset Account",account.getAttribute(Account_Type));
    }

    @Test
    public void addAttribute() throws Exception {

        account.addAttribute(Contra_Account,"Accumulated Test Acccount");

        assertEquals("Accumulated Test Acccount",account.getAttribute(Contra_Account));
    }

    @Test
    public void addEntry() throws Exception {

        account.addEntry(new Entry(SimpleDate.on(2018,12,21),account,"Entry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,23),account,"Entry Test 2",DEBIT, HardCash.dollar(110.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,24),account,"Entry Test 3",DEBIT, HardCash.dollar(95.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,28),account,"Entry Test 4",DEBIT, HardCash.dollar(90.23)));

        assertEquals(new AccountBalance(HardCash.dollar(320.69),DEBIT),account.balance(2018,12,25));
    }

    @Test(expected = UntimelyBookingDateException.class)
    public void bookingDateOnlyAfterAccountOpening() throws Exception {

        account.addEntry(new Entry(SimpleDate.on(2018,12,21),account,"Entry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,24),account,"Entry Test 3",DEBIT, HardCash.dollar(95.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,19),account,"Entry Test 4",DEBIT, HardCash.dollar(90.23)));

        // Exception thrown no need for assertion
    }

    @Test(expected = MismatchedCurrencyException.class)
    public void entryCurrencyMustMatchAccount() throws Exception {

        account.addEntry(new Entry(SimpleDate.on(2018,12,21),account,"Entry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,24),account,"Entry Test 3",DEBIT, HardCash.shilling(95.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,19),account,"Entry Test 4",DEBIT, HardCash.dollar(90.23)));// test never reaches here

        // Exception thrown no need for assertion
    }

    @Test
    public void balance() throws Exception {

        account.addEntry(new Entry(SimpleDate.on(2018,12,20),account,"Entry Test 1",DEBIT, HardCash.dollar(115.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,23),account,"Entry Test 2",DEBIT, HardCash.dollar(110.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,24),account,"Entry Test 3",DEBIT, HardCash.dollar(95.23)));
        account.addEntry(new Entry(SimpleDate.on(2018,12,26),account,"Entry Test 4",DEBIT, HardCash.dollar(90.23)));

        assertEquals(new AccountBalance(HardCash.dollar(410.92),DEBIT),account.balance(2018,12,27));

    }

}