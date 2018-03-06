package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationListener;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.Agent;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;

import java.time.YearMonth;

public interface NetBookValueAgent extends Agent<NetBookValue> {

    /**
     * Upon invocation the implementation will return the netBoookValue item for the relevant month
     * in which depreciation has occured
     *
     * @param asset FixedAsset item whose Net Book Value we are tracking
     * @param month YearMonth in which depreciation has occured
     * @return The relevant NetBookValue item
     */
    NetBookValue invoke(FixedAsset asset, YearMonth month, DepreciationProceeds proceeds);
}
