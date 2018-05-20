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
import io.github.fasset.fasset.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.book.keeper.util.UnableToPostException;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.managers.id.AccountIdConfigurationPropertiesService;
import io.github.fasset.fasset.managers.id.AcquisitionCreditAccountIDResolver;
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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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


    @Before
    public void setUp() throws Exception {

        defaultEntryResolver =
            new DefaultEntryResolver(
                new DefaultAccountResolver(
                    new AcquisitionDebitAccountIDResolver(
                        new AccountIdConfigurationPropertiesService("account-id-config")),
                    new AcquisitionCreditAccountIDResolver(
                        new AccountIdConfigurationPropertiesService("account-id-config"))));

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
                .collect(ImmutableListCollector.toImmutableList());

        // check names
        List<String> accountNames =
            accountsFromEntries
                .parallelStream()
            .map(Account::getName)
            .collect(ImmutableListCollector.toImmutableList());

        assertTrue(accountNames.contains("ELECTRONICS"));
        assertTrue(accountNames.contains("FURNITURE & FITTINGS"));
        assertTrue(accountNames.contains("COMPUTERS"));
        assertTrue(accountNames.contains("SUNDRY CREDITORS ACCOUNT"));

        // check balances
        Map<String, AccountBalance> accountBalances =
            accountsFromEntries
                .parallelStream()
                .collect(
                    Collectors.toMap(Account::getName, i -> i.balance(2018, 2, 26), (a, b) -> b, Hashtable::new));

        assertEquals(AccountBalance.newBalance(HardCash.shilling(200), DEBIT), accountBalances.get("ELECTRONICS"));
        assertEquals(AccountBalance.newBalance(HardCash.shilling(156), DEBIT), accountBalances.get("FURNITURE & FITTINGS"));
        assertEquals(AccountBalance.newBalance(HardCash.shilling(5600), DEBIT), accountBalances.get("COMPUTERS"));
        assertEquals(AccountBalance.newBalance(HardCash.shilling(5956), DEBIT), accountBalances.get("SUNDRY CREDITORS ACCOUNT"));

    }
}