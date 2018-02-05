package io.github.fasset.fasset.kernel.batch.depreciation.report.sol;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;

public interface MonthlySolDepreciationExecutor {

    /**
     * Returns MonthlySolDepreciation item relative to the parameters of the year of depreciation
     * and the solId
     * @param solId
     * @param year
     * @return
     */
    MonthlySolDepreciation getMonthlyDepreciation(String solId, Integer year);
}
