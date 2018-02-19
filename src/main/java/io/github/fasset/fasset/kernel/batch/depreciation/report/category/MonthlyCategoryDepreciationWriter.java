package io.github.fasset.fasset.kernel.batch.depreciation.report.category;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import io.github.fasset.fasset.service.MonthlyCategoryDepreciationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class MonthlyCategoryDepreciationWriter implements ItemWriter<MonthlyCategoryDepreciation> {

    private final MonthlyCategoryDepreciationService monthlyCategoryDepreciationService;

    private static final Logger log = LoggerFactory.getLogger(MonthlyCategoryDepreciationWriter.class);

    public MonthlyCategoryDepreciationWriter(MonthlyCategoryDepreciationService monthlyCategoryDepreciationService) {
        this.monthlyCategoryDepreciationService = monthlyCategoryDepreciationService;
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
    public void write(List<? extends MonthlyCategoryDepreciation> items) throws Exception {

        log.info("Saving a  list of  : {} items to the monthlyCategoryDepreciationRepository...",items.size());

        try {
            monthlyCategoryDepreciationService.saveAllMonthlyCategoryDepreciations(items);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("{} items persisted successfully",items.size());
    }
}
