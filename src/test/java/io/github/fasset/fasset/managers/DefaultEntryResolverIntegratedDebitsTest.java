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
package io.github.fasset.fasset.managers;

import io.github.fasset.fasset.book.keeper.Account;
import io.github.fasset.fasset.book.keeper.AccountingEntry;
import io.github.fasset.fasset.book.keeper.AccountingTransaction;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnableToPostException;
import io.github.fasset.fasset.managers.id.AccountIdConfigurationPropertiesService;
import io.github.fasset.fasset.managers.id.AcquisitionDebitAccountIDResolver;
import io.github.fasset.fasset.managers.id.CreditAccountIDResolver;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static io.github.fasset.fasset.book.keeper.unit.time.SimpleDate.on;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class DefaultEntryResolverIntegratedDebitsTest {

    private DefaultEntryResolver defaultEntryResolver;

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200,"KES"), "Electronics", "001", LocalDate.of(2018,2,5), "abc01", Money.of(9.5,"KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600,"KES"), "COMPUTERS", "987",LocalDate.of(2018,2,13), "abc02", Money.of(13.42,"KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156,"KES"), "FURNITURE & FITTINGS", "010",LocalDate.of(2018,1,13),"abc03", Money.of(19.24,"KES"));

    private final static List<FixedAsset> fixedAssets = new ArrayList<>();


    // Mock account
    private static final Account electronics = new Account("Electronics", "101", DEBIT, Currency.getInstance("KES"), SimpleDate.of(2018,1,1));
    private static final Account computers = new Account("Computers", "102", DEBIT, Currency.getInstance("KES"), SimpleDate.of(2018,1,1));
    private static final Account furniture = new Account("Furnitures", "103", DEBIT, Currency.getInstance("KES"), SimpleDate.of(2018,1,1));
    private static final Account sundryCreditors = new Account("Sundry Creditors", "104", CREDIT, Currency.getInstance("KES"), SimpleDate.of(2018,1,1));

    @Before
    public void setUp() throws Exception {

        CreditAccountIDResolver creditAccountIDResolver = Mockito.mock(CreditAccountIDResolver.class);

        when(creditAccountIDResolver.resolveName(radio)).thenReturn("Sundry Creditors Account");
        when(creditAccountIDResolver.resolveName(lenovo)).thenReturn("Sundry Creditors Account");
        when(creditAccountIDResolver.resolveName(chair)).thenReturn("Sundry Creditors Account");
        when(creditAccountIDResolver.resolveNumber(radio)).thenReturn("0230010051011");
        when(creditAccountIDResolver.resolveNumber(lenovo)).thenReturn("0140010051011");
        when(creditAccountIDResolver.resolveNumber(chair)).thenReturn("0180010051011");
        when(creditAccountIDResolver.resolveCategoryId(radio)).thenReturn("ELECTRONICS");
        when(creditAccountIDResolver.resolveCategoryId(lenovo)).thenReturn("COMPUTERS");
        when(creditAccountIDResolver.resolveCategoryId(chair)).thenReturn("FURNITURE");
        when(creditAccountIDResolver.resolveGeneralLedgerName(chair)).thenReturn("FURNITURE");
        when(creditAccountIDResolver.resolveGeneralLedgerName(lenovo)).thenReturn("COMPUTERS");
        when(creditAccountIDResolver.resolveGeneralLedgerName(radio)).thenReturn("ELECTRONICS");

        defaultEntryResolver =
            new DefaultEntryResolver(
                new DefaultAccountResolver(
                    new AcquisitionDebitAccountIDResolver(
                        new AccountIdConfigurationPropertiesService("account-id-config")), creditAccountIDResolver));

        fixedAssets.add(radio);
        fixedAssets.add(lenovo);
        fixedAssets.add(chair);
    }

    @Test
    public void resolveAcquisitionEntries() throws ImmutableEntryException, UnableToPostException {

        List<AccountingEntry> entries = defaultEntryResolver.resolveAcquisitionEntries(fixedAssets);

        assertEquals(fixedAssets.size() * 2, entries.size());

        AccountingTransaction testPostingAssets = AccountingTransaction.create("Test posting entry resolver",on(2018,2,21),Currency.getInstance("KES"));

        entries.forEach(entry -> {
            try {
                testPostingAssets.addEntry(entry);
            } catch (MismatchedCurrencyException | ImmutableEntryException e) {
                e.printStackTrace();
            }
        });

        testPostingAssets.post();

        // each asset is represented by an entry
        assertEquals(fixedAssets.size() * 2, entries.size());

        // fetching accounts
        List<Account> accountsFromEntries =
            IntStream.range(0, fixedAssets.size() * 2 - 1)
                .mapToObj(i -> entries.get(i).getAccount())
                .collect(Collectors.toList());


        /* TODO Implement assertions for checking account balances along with credit side acquisition accounts
        assertTrue(accountsFromEntries.contains(electronics));
        assertTrue(accountsFromEntries.contains(computers));
        assertTrue(accountsFromEntries.contains(furniture));
        assertTrue(accountsFromEntries.contains(sundryCreditors));
        assertEquals(new AccountBalance(HardCash.shilling(200),DEBIT), accountsFromEntries.get(accountsFromEntries.indexOf(electronics)).balance(on(2018,2,21)));
        assertEquals(new AccountBalance(HardCash.shilling(5600),DEBIT), accountsFromEntries.get(accountsFromEntries.indexOf(computers)).balance(on(2018,2,21)));
        assertEquals(new AccountBalance(HardCash.shilling(156),DEBIT), accountsFromEntries.get(accountsFromEntries.indexOf(furniture)).balance(on(2018,2,21)));
        assertEquals(new AccountBalance(HardCash.shilling(5956),CREDIT), accountsFromEntries.get(accountsFromEntries.indexOf(sundryCreditors)).balance(on(2018,2,21)));*/
    }
}