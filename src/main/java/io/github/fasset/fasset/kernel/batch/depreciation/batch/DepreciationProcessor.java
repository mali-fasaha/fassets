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
package io.github.fasset.fasset.kernel.batch.depreciation.batch;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationExecutor;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.kernel.util.ProcessingList;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Calculates the depreciation in  a given month for a given Asset. A list is generated and sent to the writer for persistence
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class DepreciationProcessor implements ItemProcessor<FixedAsset, ProcessingList<DepreciationProceeds>> {

    private static final Logger log = LoggerFactory.getLogger(DepreciationProcessor.class);


    private DepreciationRelay depreciationRelay;


    private DepreciationExecutor depreciationExecutor;

    private ProcessingList<DepreciationProceeds> processingList;

    /**
     * <p>Constructor for DepreciationProcessor.</p>
     *
     * @param processingList a {@link io.github.fasset.fasset.kernel.util.ProcessingList} object.
     */
    public DepreciationProcessor(ProcessingList<DepreciationProceeds> processingList) {
        this.processingList = processingList;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Process the provided item, returning a potentially modified or new item for continued processing.  If the returned result is null, it is assumed that processing of the item should not
     * continue.
     */
    @Override
    public ProcessingList<DepreciationProceeds> process(FixedAsset fixedAsset) throws Exception {

        //ProcessingList<DepreciationProceeds> depreciationProceeds = new ProcessingListImpl<>();

        depreciationRelay.getMonthlyDepreciationSequence().forEach(i -> {

            log.trace("Calculating depreciation in the month of :{} for asset {}", i, fixedAsset);

            processingList.add(depreciationExecutor.getDepreciation(fixedAsset, i));
        });


        return processingList;
    }


    /**
     * <p>Setter for the field <code>depreciationRelay</code>.</p>
     *
     * @param depreciationRelay a {@link io.github.fasset.fasset.kernel.batch.depreciation.batch.DepreciationRelay} object.
     * @return a {@link io.github.fasset.fasset.kernel.batch.depreciation.batch.DepreciationProcessor} object.
     */
    @Autowired
    @Qualifier("depreciationRelay")
    public DepreciationProcessor setDepreciationRelay(DepreciationRelay depreciationRelay) {
        this.depreciationRelay = depreciationRelay;
        return this;
    }

    /**
     * <p>Setter for the field <code>depreciationExecutor</code>.</p>
     *
     * @param depreciationExecutor a {@link io.github.fasset.fasset.kernel.batch.depreciation.DepreciationExecutor} object.
     * @return a {@link io.github.fasset.fasset.kernel.batch.depreciation.batch.DepreciationProcessor} object.
     */
    @Autowired
    @Qualifier("depreciationExecutor")
    public DepreciationProcessor setDepreciationExecutor(DepreciationExecutor depreciationExecutor) {
        this.depreciationExecutor = depreciationExecutor;
        return this;
    }
}
