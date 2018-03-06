package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationListener;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

public interface AccruedDepreciationAgent extends Agent<AccruedDepreciation> {

    @Override
    AccruedDepreciation invoke(FixedAsset asset, YearMonth month, DepreciationProceeds proceeds);
}
