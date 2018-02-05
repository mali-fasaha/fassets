package io.github.fasset.fasset.kernel.batch.depreciation.report.asset;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class QueryMonthlyDepreciationProcessor implements ItemProcessor<FixedAsset,List<MonthlyAssetDepreciation>> {

    @Autowired
    @Qualifier("queriedMonthlyAssetDepreciationExecutor")
    private QueriedMonthlyAssetDepreciationExecutor queriedMonthlyAssetDepreciationExecutor;


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
    public List<MonthlyAssetDepreciation> process(FixedAsset item) throws Exception {

        return queriedMonthlyAssetDepreciationExecutor.getMonthlyDepreciation(item);
    }
}
