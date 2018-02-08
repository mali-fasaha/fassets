package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

/**
 * Implements the {@link DepreciationExecutor} interface by direct query running in the
 * {@link io.github.fasset.fasset.repository.FixedAssetRepository}
 *
 * @author edwin.njeru
 */
public class QueryDrivenDepreciationExecutor implements DepreciationExecutor {

    /**
     * Returns a Depreciation object given the fixed asset, and updates the fixed asset with the new
     * net book value and the month of depreciation
     *
     * @param asset {@link FixedAsset} to be depreciated
     * @param month the month for which we are calculating depreciation
     * @return {@link Depreciation} object
     */
    @Override
    public Depreciation getDepreciation(FixedAsset asset, YearMonth month) {
        return null;
    }
}
