package io.github.fasset.fasset.kernel.batch.depreciation.report;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import io.github.fasset.fasset.service.impl.MonthlyAssetDepreciationServiceImpl;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class MonthlyAssetDepreciationWriter implements ItemWriter<List<MonthlyAssetDepreciation>> {


    private final MonthlyAssetDepreciationService monthlyAssetDepreciationService;

    public MonthlyAssetDepreciationWriter(MonthlyAssetDepreciationService monthlyAssetDepreciationService) {
        this.monthlyAssetDepreciationService = monthlyAssetDepreciationService;
    }

    /**
     * Process the supplied data element. Will not be called with any null items
     * in normal operation.
     *
     * @param items items to be written
     * @throws Exception if there are errors. The framework will catch the
     *                   exception and convert or rethrow it as appropriate.
     */
    @Override
    public void write(List<? extends List<MonthlyAssetDepreciation>> items) throws Exception {

        items.forEach(monthlyAssetDepreciationService::saveAllMonthlyDepreciationItems);
    }
}
