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


public class DepreciationProcessor implements ItemProcessor<FixedAsset,ProcessingList<DepreciationProceeds>> {

    private static final Logger log = LoggerFactory.getLogger(DepreciationProcessor.class);


    private DepreciationRelay depreciationRelay;


    private DepreciationExecutor depreciationExecutor;

    private ProcessingList<DepreciationProceeds> processingList;

    public DepreciationProcessor(ProcessingList<DepreciationProceeds> processingList) {
        this.processingList = processingList;
    }

    /**
     * Process the provided item, returning a potentially modified or new item for continued
     * processing.  If the returned result is null, it is assumed that processing of the item
     * should not continue.
     *
     * @param fixedAsset to be processed
     * @return potentially modified or new item for continued processing, null if processing of the
     * provided item should not continue.
     * @throws Exception thrown if exception occurs during processing.
     */
    @Override
    public ProcessingList<DepreciationProceeds> process(FixedAsset fixedAsset) throws Exception {

        //ProcessingList<DepreciationProceeds> depreciationProceeds = new ProcessingListImpl<>();

        depreciationRelay.getMonthlyDepreciationSequence()
                .forEach(
                        i -> {

                            log.debug("Calculating depreciation in the month of :{} for asset {}",i,fixedAsset);

                            processingList.add(depreciationExecutor.getDepreciation(fixedAsset,i));
                        }
                );


        return processingList;
    }


    @Autowired
    @Qualifier("depreciationRelay")
    public DepreciationProcessor setDepreciationRelay(DepreciationRelay depreciationRelay) {
        this.depreciationRelay = depreciationRelay;
        return this;
    }

    @Autowired
    @Qualifier("depreciationExecutor")
    public DepreciationProcessor setDepreciationExecutor(DepreciationExecutor depreciationExecutor) {
        this.depreciationExecutor = depreciationExecutor;
        return this;
    }
}
