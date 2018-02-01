package io.github.fasset.fasset.kernel.batch.depreciation.report;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;


public class MonthlyAssetDepreciationProcessor implements ItemProcessor<FixedAsset,MonthlyAssetDepreciation>{

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationProcessor.class);

    private String year;

    private MonthlyAssetDepreciationExecutor monthlyAssetDepreciationExecutor;

    public MonthlyAssetDepreciationProcessor(@Qualifier("monthlyAssetDepreciationExecutor") MonthlyAssetDepreciationExecutor monthlyAssetDepreciationExecutor,String year) {
        this.year=year;
        this.monthlyAssetDepreciationExecutor=monthlyAssetDepreciationExecutor;
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
    public MonthlyAssetDepreciation process(FixedAsset fixedAsset) throws Exception {

        if(year==null){

            log.warn("The year value passed is null : {}",year);
        }

        return monthlyAssetDepreciationExecutor.getMonthlyDepreciation(fixedAsset,Integer.parseInt(year));
    }
}
