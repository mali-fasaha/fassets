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
public class DepreciationAgentsChain {

    private final DepreciationAgent depreciationAgent;
    private final AccruedDepreciationAgent accruedDepreciationAgent;
    private final NetBookValueAgent netBookValueAgent;

    @Autowired
    public DepreciationAgentsChain(DepreciationAgent depreciationAgent, AccruedDepreciationAgent accruedDepreciationAgent, NetBookValueAgent netBookValueAgent) {
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


    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public Depreciation execute(FixedAsset asset, YearMonth month) {

        Depreciation depreciation = null;

        // invoke all agents
        for(Agent agent : agents){

            Object t = agent.invoke(asset,month);

            if(t instanceof Depreciation)
                depreciation = (Depreciation) t;
        }

        return depreciation;
    }
}
