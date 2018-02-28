package io.github.fasset.fasset.kernel.batch.depreciation.batch;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationExecutorImpl;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.kernel.batch.depreciation.ProceedsList;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.LinkedList;
import java.util.List;

public class DepreciationProcessor implements ItemProcessor<FixedAsset,List<DepreciationProceeds>> {

    private static final Logger log = LoggerFactory.getLogger(DepreciationProcessor.class);

    @Autowired
    @Qualifier("depreciationRelay")
    private DepreciationRelay depreciationRelay;

    @Autowired
    @Qualifier("depreciationExecutor")
    private DepreciationExecutorImpl depreciationExecutor;

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
    public List<DepreciationProceeds> process(FixedAsset fixedAsset) throws Exception {

        //List<Depreciation> depreciationList = new LinkedList<>();
        //DepreciationProceeds proceeds = new DepreciationProceeds();
        List<DepreciationProceeds> depreciationProceeds = new ProceedsList<>();

        depreciationRelay.getMonthlyDepreciationSequence()
                .forEach(
                        i -> {

                            log.debug("Calculating depreciation in the month of :{} for asset {}",i,fixedAsset);

                            depreciationProceeds.add(depreciationExecutor.getDepreciation(fixedAsset,i));
                        }
                );


        return depreciationProceeds;
    }
}
