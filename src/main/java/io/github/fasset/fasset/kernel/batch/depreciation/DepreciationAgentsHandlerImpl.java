package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.Agent;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component("depreciationAgentsHandler")
public class DepreciationAgentsHandlerImpl implements DepreciationAgentsHandler {

    private DepreciationAgentsChainImpl depreciationAgentsChain;

    public DepreciationAgentsHandlerImpl(DepreciationAgentsChainImpl depreciationAgentsChain) {
        this.depreciationAgentsChain = depreciationAgentsChain;
    }

    public void setDepreciationAgent(Agent agent){

        depreciationAgentsChain.addAgent(agent);
    }

    @Override
    public void sendRequest(FixedAsset asset, YearMonth month,DepreciationListener listener) {

        depreciationAgentsChain.execute(asset,month,listener);
    }
}
