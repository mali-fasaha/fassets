package io.github.fasset.fasset.kernel.batch.depreciation.report.asset;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;

/**
 * This object takes a depreciation item and extracts from the
 * {@link io.github.fasset.fasset.service.MonthlyAssetDepreciationService} the corresponding
 * {@link io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation} item. Which is then updated
 * using the depreciation data passed to the object
 *
 * @author edwin.njeru
 *
 */
public interface MonthlyAssetDepreciationExecutor {

    /**
     * Returns {@link MonthlyAssetDepreciation} item that is updated with data from the depreciation
     * item
     *
     * @param fixedAsset
     * @return
     */
    MonthlyAssetDepreciation getMonthlyDepreciation(FixedAsset fixedAsset,Integer year);
}
