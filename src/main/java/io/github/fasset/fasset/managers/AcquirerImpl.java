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

package io.github.fasset.fasset.managers;

import io.github.fasset.fasset.book.keeper.AccountingEntry;
import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.model.FixedAsset;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;

/**
 * Implements {@link Acquirer} interface whose job really is to generate entries based on a list of actual
 * {@code FixedAsset} items passed through the parameter in the main method
 */
public class AcquirerImpl implements Acquirer {

    private ChartOfAccounts chartOfAccounts;

    @Autowired
    public AcquirerImpl(@Qualifier("chartOfAccounts") ChartOfAccounts chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    /**
     * Generates {@code AccountingEntry} items based on {@code FixedAsset} items passed in the parameter args
     *
     * @param fixedAssets Collection of {@code FixedAsset} items from which we are to generate entries
     * @return List containing Entry bookings for the fixedAssets passed in the parameter
     */
    @Override
    public List<AccountingEntry> getAccountingEntries(List<FixedAsset> fixedAssets) {

        List<AccountingEntry> entries = new FastList<>();

        fixedAssets.parallelStream().forEach(fixedAsset -> {
            entries.add(getAssetEntry(fixedAsset));
            entries.add(getSundryCreditorEntry(fixedAsset));
        });

        return entries;
    }

    /**
     * Generates the Entry which is to be made to the {@code sundryAccount} in respect of this fixedAsset
     *
     * @param fixedAsset for which we recognize acquisition settlement
     * @return AccountingEntry into the {@code sundryAccount}
     */
    private AccountingEntry getSundryCreditorEntry(FixedAsset fixedAsset) {

        AccountingEntry sundryEntry =
            new AccountingEntry(SimpleDate.ofLocal(fixedAsset.getPurchaseDate()), chartOfAccounts.getAcquisitionCreditAccount(fixedAsset), fixedAsset.getAssetDescription(), CREDIT,
                HardCash.fromMoneta(fixedAsset.getPurchaseCost()));

        sundryEntry.setEntryAttributes(getFixedAssetsAttributes(fixedAsset));

        return sundryEntry;
    }


    /**
     * Generates the Entry which is to be made in the {@code fixedAssetAccount} in respect to this fixedAsset
     *
     * @param fixedAsset for which we generate the appropriate {@code DEBIT} entry
     * @return AccountingEntry into the {@code fixedAssetAccount}
     */
    private AccountingEntry getAssetEntry(FixedAsset fixedAsset) {

        AccountingEntry fixedAssetEntry =
            new AccountingEntry(SimpleDate.ofLocal(fixedAsset.getPurchaseDate()), chartOfAccounts.getAcquistionDebitAccount(fixedAsset), fixedAsset.getAssetDescription(), DEBIT,
                HardCash.fromMoneta(fixedAsset.getPurchaseCost()));

        fixedAssetEntry.setEntryAttributes(getFixedAssetsAttributes(fixedAsset));

        return fixedAssetEntry;
    }

    private Map<String, String> getFixedAssetsAttributes(FixedAsset fixedAsset) {

        Map<String, String> fixedAssetsAttributes = new ConcurrentSkipListMap<>();
        fixedAssetsAttributes.put("Barcode", fixedAsset.getBarcode());
        fixedAssetsAttributes.put("SolId", fixedAsset.getSolId());
        fixedAssetsAttributes.put("Category", fixedAsset.getCategory());
        fixedAssetsAttributes.put("NetBookValue", fixedAsset.getNetBookValue().toString());

        return fixedAssetsAttributes;
    }
}
