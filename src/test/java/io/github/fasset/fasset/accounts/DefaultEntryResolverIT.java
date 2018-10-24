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
package io.github.fasset.fasset.accounts;

import com.google.common.collect.ImmutableList;
import io.github.fasset.fasset.accounts.nomenclature.AcquisitionCreditAccountIdResolver;
import io.github.fasset.fasset.accounts.nomenclature.AcquisitionDebitAccountIdResolver;
import io.github.fasset.fasset.accounts.nomenclature.properties.FileAccountIdService;
import io.github.fasset.fasset.accounts.nomenclature.properties.policy.AccountIdPolicyVersion1;
import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.book.keeper.AccountingTransaction;
import io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance;
import io.github.fasset.fasset.kernel.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.kernel.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.FixedAsset;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance.newBalance;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountBalance.nil;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.DEBIT;
import static io.github.ghacupha.cash.HardCash.shilling;
import static io.github.ghacupha.time.point.SimpleDate.on;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultEntryResolverIT {

    private final static Currency KES = Currency.getInstance("KES");

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200, "KES"), "ELECTRONIC EQUIPMENT", "001", LocalDate.of(2018, 2, 5), "abc01", Money.of(9.5, "KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600, "KES"), "COMPUTERS", "987", LocalDate.of(2018, 2, 13), "abc02", Money.of(13.42, "KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156, "KES"), "FURNITURE & FITTINGS", "010", LocalDate.of(2018, 1, 13), "abc03", Money.of(19.24, "KES"));

    private static List<FixedAsset> fixedAssets = ImmutableList.of(radio, lenovo, chair);
    private static List<AccountingEntry> entries;
    private static AccountingTransaction assetAcquisition;
    private static List<String> accountNames;
    private static Map<String, AccountBalance> accountBalances;
    private static Map<String, String> accountNumbers;
    private static List<AccountBalance> sundryCreditorBalances;
    private static List<String> sundryCreditorNumbers;

    @Before
    public void setUp() throws Exception {

        BatchAcquisitionEntryResolver batchAcquisitionEntryResolver = new BatchAcquisitionEntryResolver(
            new AcquisitionAccountResolver(new AcquisitionDebitAccountIdResolver(new FileAccountIdService(new AccountIdPolicyVersion1("account-id"))),
                                           new AcquisitionCreditAccountIdResolver(new FileAccountIdService(new AccountIdPolicyVersion1("account-id")))));

        entries = batchAcquisitionEntryResolver.resolveEntries(fixedAssets);

        assetAcquisition = AccountingTransaction.create("Test posting entry resolver", on(2018, 2, 21), Currency.getInstance("KES"));

        entries.forEach(entry -> {
            try {
                assetAcquisition.addEntry(entry);
            } catch (MismatchedCurrencyException | ImmutableEntryException e) {
                e.printStackTrace();
            }
        });

        assetAcquisition.post();

        List<Account> accountsFromEntries = IntStream.range(0, fixedAssets.size() * 2).mapToObj(i -> entries.get(i).getAccount()).collect(ImmutableListCollector.toImmutableList());

        accountNames = accountsFromEntries.stream().map(Account::getName).distinct().collect(ImmutableListCollector.toImmutableFastList());

        accountBalances = accountsFromEntries.stream().collect(Collectors.toMap(Account::getName, i -> i.balance(2018, 2, 26), (a, b) -> b, ConcurrentHashMap::new));

        accountNumbers = accountsFromEntries.stream().collect(Collectors.toMap(Account::getName, Account::getNumber, (a, b) -> b, ConcurrentHashMap::new));

        sundryCreditorBalances = accountsFromEntries.stream()
                                                    .filter(i -> i.getName().equalsIgnoreCase("SUNDRY CREDITORS"))
                                                    .map(account -> account.balance(on(2018, 2, 26)))
                                                    .collect(ImmutableListCollector.toImmutableFastList());

        sundryCreditorNumbers =
            accountsFromEntries.stream().filter(account -> account.getName().equalsIgnoreCase("SUNDRY CREDITORS")).map(Account::getNumber).collect(ImmutableListCollector.toImmutableFastList());
    }

    @Test
    public void numberOfEntriesGeneratedByEntryResolution() {

        // each asset is represented by an entry
        assertEquals(fixedAssets.size() * 2, entries.size());
    }

    @Test
    public void namesOfAccountsGeneratedByEntryResolution() {

        assertEquals(4, accountNames.size());
        assertTrue(accountNames.contains("ELECTRONIC EQUIPMENT"));
        assertTrue(accountNames.contains("FURNITURE AND FITTINGS"));
        assertTrue(accountNames.contains("COMPUTERS"));
        assertTrue(accountNames.contains("SUNDRY CREDITORS"));
    }

    @Test
    public void accountNumbersOfAccountsGeneratedByEntryResolution() {

        // Can't check "Sundry Creditors Account" since it has the same for different accounts
        assertEquals("9870000152001", accountNumbers.get("COMPUTERS"));
        assertEquals("0100000153001", accountNumbers.get("FURNITURE AND FITTINGS"));
        assertEquals("0010000156001", accountNumbers.get("ELECTRONIC EQUIPMENT"));
    }

    @Test
    public void sundryCreditorsAccountNumbersGeneratedByEntryResolution() {

        assertEquals(3, sundryCreditorNumbers.size());
        assertTrue(sundryCreditorNumbers.contains("9870010051001"));
        assertTrue(sundryCreditorNumbers.contains("0100010051001"));
        assertTrue(sundryCreditorNumbers.contains("0010010051001"));
    }

    @Test
    public void balancesOfAccountsGeneratedByEntryResolution() {

        assertEquals(newBalance(shilling(200), DEBIT), accountBalances.get("ELECTRONIC EQUIPMENT"));
        assertEquals(newBalance(shilling(156), DEBIT), accountBalances.get("FURNITURE AND FITTINGS"));
        assertEquals(newBalance(shilling(5600), DEBIT), accountBalances.get("COMPUTERS"));
        assertEquals(newBalance(shilling(5956), CREDIT), nil(KES, CREDIT).add(sundryCreditorBalances));
    }
}