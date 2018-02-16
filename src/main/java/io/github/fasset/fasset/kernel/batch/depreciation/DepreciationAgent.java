package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

/**
 * Executes the actual depreciation upon invocation by the DepreciationExecutor
 *
 * @author edwin.njeru
 */
public interface DepreciationAgent {

    /**
     * This method calculates depreciation and updates internal variables from
     * which the depreciation variables can be drawn
     *
     * @param asset
     * @param month
     */
    void invoke(FixedAsset asset, YearMonth month);
}
