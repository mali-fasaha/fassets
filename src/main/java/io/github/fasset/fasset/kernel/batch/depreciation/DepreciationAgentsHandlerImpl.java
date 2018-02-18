package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.Agent;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

public class DepreciationAgentsHandlerImpl implements DepreciationAgentsHandler {

    private DepreciationAgentsChain depreciationAgentsChain;

    public DepreciationAgentsHandlerImpl(DepreciationAgentsChain depreciationAgentsChain) {
        this.depreciationAgentsChain = depreciationAgentsChain;
    }

    public void setDepreciationAgent(Agent agent){

        depreciationAgentsChain.addAgent(agent);
    }

    @Override
    public Depreciation sendRequest(FixedAsset asset, YearMonth month) {

        return depreciationAgentsChain.execute(asset,month);
    }
}
