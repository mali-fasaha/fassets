package io.github.fasset.fasset.kernel.batch.depreciation.report;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonthlyAssetDepreciationProcessor
        implements ItemProcessor<Depreciation,MonthlyAssetDepreciation>{

    @Autowired
    @Qualifier("monthlyAssetDepreciationExecutor")
    private MonthlyAssetDepreciationExecutor monthlyAssetDepreciationExecutor;


    /**
     * Process the provided item, returning a potentially modified or new item for continued
     * processing.  If the returned result is null, it is assumed that processing of the item
     * should not continue.
     *
     * @param item to be processed
     * @return potentially modified or new item for continued processing, null if processing of the
     * provided item should not continue.
     * @throws Exception thrown if exception occurs during processing.
     */
    @Override
    public MonthlyAssetDepreciation process(Depreciation item) throws Exception {

        return monthlyAssetDepreciationExecutor.getMonthlyDepreciation(item);
    }
}
