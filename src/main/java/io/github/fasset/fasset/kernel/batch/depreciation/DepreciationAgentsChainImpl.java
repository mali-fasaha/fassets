/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.AccruedDepreciationAgent;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.Agent;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.DepreciationAgent;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.NetBookValueAgent;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Object encapsulates a chain of Agents through which a depreciation item is processed as a FixedAsset is passed through
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("depreciationAgentsChain")
public class DepreciationAgentsChainImpl {

    private final DepreciationAgent depreciationAgent;
    private final AccruedDepreciationAgent accruedDepreciationAgent;
    private final NetBookValueAgent netBookValueAgent;
    private final List<Agent> agents = new CopyOnWriteArrayList<>();

    /**
     * <p>Constructor for DepreciationAgentsChainImpl.</p>
     *
     * @param depreciationAgent        a {@link io.github.fasset.fasset.kernel.batch.depreciation.agent.DepreciationAgent} object.
     * @param accruedDepreciationAgent a {@link io.github.fasset.fasset.kernel.batch.depreciation.agent.AccruedDepreciationAgent} object.
     * @param netBookValueAgent        a {@link io.github.fasset.fasset.kernel.batch.depreciation.agent.NetBookValueAgent} object.
     */
    @Autowired
    public DepreciationAgentsChainImpl(DepreciationAgent depreciationAgent, AccruedDepreciationAgent accruedDepreciationAgent, NetBookValueAgent netBookValueAgent) {
        this.depreciationAgent = depreciationAgent;
        this.accruedDepreciationAgent = accruedDepreciationAgent;
        this.netBookValueAgent = netBookValueAgent;
    }

    @PostConstruct
    private void updateAgents() {

        agents.add(depreciationAgent);
        agents.add(accruedDepreciationAgent);
        agents.add(netBookValueAgent);
    }

    void addAgent(Agent agent) {
        agents.add(agent);
    }

    /**
     * <p>execute.</p>
     *
     * @param asset                a {@link io.github.fasset.fasset.model.FixedAsset} object.
     * @param month                a {@link java.time.YearMonth} object.
     * @param depreciationProceeds a {@link io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds} object.
     */
    public void execute(FixedAsset asset, YearMonth month, DepreciationProceeds depreciationProceeds) {

        // invoke all agents
        for (Agent agent : agents) {

            agent.invoke(asset, month, depreciationProceeds);

        }

    }
}
