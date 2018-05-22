package io.github.fasset.fasset.accounts;

import io.github.fasset.fasset.accounts.depreciation.DepreciationPeriod;
import io.github.fasset.fasset.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;

import java.util.List;

/**
 * This is interface is similar with the BatchEntryResolver however the depreciation concept must have Effective period
 * and such was lacking in that interface. A new interface must therefore be created to represent Effectivity of depreciation
 *
 * @author edwin.njeru
 */
public interface DepreciationEntryResolver {

    /**
     * Generates {@code AccountingEntry} items based on {@code FixedAssets} items passed in the parameter.
     * The method will generate both {@code DEBIT} and {@code CREDIT} side entries and will abstract from
     * client the logic of obtaining depreciation rates and values from configurations in the application
     *
     * @param fixedAssets Items to be depreciated
     * @param period when the depreciation Entries are effective
     * @return {@code AccountingEntries} to post depreciation
     */
    List<AccountingEntry> resolveEntries(List<FixedAsset> fixedAssets, DepreciationPeriod period);
}
