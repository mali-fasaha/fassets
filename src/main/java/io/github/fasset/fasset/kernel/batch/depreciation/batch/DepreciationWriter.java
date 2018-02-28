package io.github.fasset.fasset.kernel.batch.depreciation.batch;

import io.github.fasset.fasset.kernel.util.DepreciationExecutionException;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.service.DepreciationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class DepreciationWriter implements ItemWriter<List<Depreciation>> {

    private static final Logger log = LoggerFactory.getLogger(DepreciationWriter.class);

    @Autowired
    @Qualifier("depreciationService")
    private DepreciationService depreciationService;

    /**
     * Process the supplied data element. Will not be called with any null items
     * in normal operation.
     *
     * @param depreciationLists items to be written
     * @throws Exception if there are errors. The framework will catch the
     *                   exception and convert or rethrow it as appropriate.
     */
    @Override
    public void write(List<? extends List<Depreciation>> depreciationLists) throws Exception {

        log.info("Writing : {} DepreciationLists to the depreciationRepository",depreciationLists.size());

        try {
            depreciationLists.forEach( list ->{
                log.info("Saving to repository : {} depreciation items",list.size());
                    depreciationService.saveAllDepreciationItems(list);
            });
        } catch (Throwable e) {
            String message = String.format("Exception encountered while persisting depreciation items" +
                    "passed in the list to the depreciation writer. These are the items : %s",depreciationLists);
            throw new DepreciationExecutionException(message,e);
        }

    }
}
