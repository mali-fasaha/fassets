package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.AccruedDepreciationAgent;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.Agent;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.DepreciationAgent;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.NetBookValueAgent;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.YearMonth;
import java.util.List;

@Component("depreciationAgentsChain")
public class DepreciationAgentsChainImpl {

    private final DepreciationAgent depreciationAgent;
    private final AccruedDepreciationAgent accruedDepreciationAgent;
    private final NetBookValueAgent netBookValueAgent;

    @Autowired
    public DepreciationAgentsChainImpl(DepreciationAgent depreciationAgent, AccruedDepreciationAgent accruedDepreciationAgent, NetBookValueAgent netBookValueAgent) {
        this.depreciationAgent = depreciationAgent;
        this.accruedDepreciationAgent = accruedDepreciationAgent;
        this.netBookValueAgent = netBookValueAgent;
    }

    @PostConstruct
    private void updateAgents(){

        agents.add(depreciationAgent);
        agents.add(accruedDepreciationAgent);
        agents.add(netBookValueAgent);
    }

    private final List<Agent> agents = new FastList<>();


    void addAgent(Agent agent) {
        agents.add(agent);
    }

    public void execute(FixedAsset asset, YearMonth month,DepreciationProceeds depreciationProceeds) {

        // invoke all agents
        for(Agent agent : agents){

            agent.invoke(asset,month,depreciationProceeds);

        }

    }
}
