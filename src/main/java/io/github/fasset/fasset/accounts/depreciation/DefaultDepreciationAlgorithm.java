package io.github.fasset.fasset.accounts.depreciation;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.ghacupha.cash.Cash;
import org.springframework.stereotype.Component;

/**
 * Work in progress on this one
 */
@Component("defaultDepreciationAlgorithm")
public class DefaultDepreciationAlgorithm implements DepreciationAlgorithm {

    /**
     * Calculates the {@code Cash} amount of depreciation given the Asset to be depreciated and the DepreciationPeriod.
     * At the very list it is expected that an object will be created that tracks the last time an asset was depreciated
     * amount of depreciation, the date of next depreciation date and the depreciation method
     *
     * @param asset  Fixed Asset to be depreciated
     * @param period THe period of depreciation
     * @return The amount of depreciation
     */
    @Override
    public Cash getDepreciation(FixedAsset asset, DepreciationPeriod period) {
        return null;
    }

    /**
     * @return Name of the algorithm
     */
    @Override
    public String name() {
        return null;
    }
}
