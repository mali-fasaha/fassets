package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationListener;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

/**
 * Executes the actual depreciation upon invocation by the DepreciationExecutor
 *
 * @author edwin.njeru
 */
public interface DepreciationAgent extends Agent<Depreciation> {

    /**
     * This method calculates depreciation and updates internal variables from
     * which the depreciation variables can be drawn
     *
     * @param asset
     * @param month
     */
    Depreciation invoke(FixedAsset asset, YearMonth month,DepreciationProceeds proceeds);
}
