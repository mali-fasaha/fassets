package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.service.AccruedDepreciationService;
import io.github.fasset.fasset.service.impl.AccruedDepreciationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("accruedDepreciationWriter")
public class AccruedDepreciationWriter implements ItemWriter<AccruedDepreciation> {

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationWriter.class);

    private final AccruedDepreciationService accruedDepreciationService;

    @Autowired
    public AccruedDepreciationWriter(@Qualifier("accruedDepreciationService") AccruedDepreciationService accruedDepreciationService) {
        this.accruedDepreciationService = accruedDepreciationService;
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
    public void write(List<? extends AccruedDepreciation> items) throws Exception {

        log.info("Wring a chunk of {} items to repository",items.size());

        accruedDepreciationService.saveAllAccruedDepreciationRecords(items);
    }
}
