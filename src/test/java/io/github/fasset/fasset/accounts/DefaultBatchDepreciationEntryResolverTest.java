/**
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.accounts;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import io.github.fasset.fasset.accounts.depreciation.DepreciationAlgorithm;
import io.github.fasset.fasset.accounts.depreciation.DepreciationPeriod;
import io.github.fasset.fasset.book.keeper.AccountingEntry;
import io.github.fasset.fasset.book.keeper.util.UnEnteredDetailsException;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;

import io.github.fasset.fasset.book.keeper.Account;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.model.FixedAsset;

public class DefaultBatchDepreciationEntryResolverTest {

    private DepreciationEntryResolver batchEntryResolver;

    private final static Currency KES = Currency.getInstance("KES");
    private final DepreciationPeriod period = Mockito.mock(DepreciationPeriod.class);

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200, "KES"), "Electronics", "001", LocalDate.of(2018, 2, 5), "abc01", Money.of(9.5, "KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600, "KES"), "COMPUTERS", "987", LocalDate.of(2018, 2, 13), "abc02", Money.of(13.42, "KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156, "KES"), "FURNITURE", "010", LocalDate.of(2018, 1, 13), "abc03", Money.of(19.24, "KES"));

    private List<FixedAsset> fixedAssets = ImmutableList.of(radio, lenovo, chair);

    // Mock account
    private static final Account electronics = new Account("Electronics", "101", DEBIT, Currency.getInstance("KES"), SimpleDate.of(2018, 1, 1));
    private static final Account computers = new Account("Computers", "102", DEBIT, Currency.getInstance("KES"), SimpleDate.of(2018, 1, 1));
    private static final Account furniture = new Account("Furnitures", "103", DEBIT, Currency.getInstance("KES"), SimpleDate.of(2018, 1, 1));
    private static final Account sundryCreditors = new Account("Sundry Creditors", "104", CREDIT, Currency.getInstance("KES"), SimpleDate.of(2018, 1, 1));

    List<AccountingEntry> entries;
    @Before
    public void setUp() throws Exception {

        AccountResolver accountResolver = Mockito.mock(AccountResolver.class);
        DepreciationAlgorithm depreciationAlgorithm = Mockito.mock(DepreciationAlgorithm.class);

        when(depreciationAlgorithm.name()).thenReturn("Mock Depreciation Algorithm");

        batchEntryResolver = new DefaultDepreciationEntryResolver(accountResolver, depreciationAlgorithm);

        entries = batchEntryResolver.resolveEntries(fixedAssets, period);
    }

    /**
     * For each asset we can expect to generate 2 entries
     */
    @Test
    public void numberOfAccountsGenerated() {

        assertEquals(6, entries.size());

    }

    @Test
    public void depreciationAlgorithmSignature() throws Exception {

        assertTrue(entries.stream()
                .filter(entry -> entry.getAccountSide() == DEBIT)
                .map(debitEntry -> {
                    try {
                        return debitEntry.getAttribute("DEPRECIATION_ALGORITHM");
                    } catch (UnEnteredDetailsException e) {
                        e.printStackTrace();
                    }
                return "";})
            .allMatch(att -> att.equalsIgnoreCase("Mock Depreciation Algorithm")));
    }
}
