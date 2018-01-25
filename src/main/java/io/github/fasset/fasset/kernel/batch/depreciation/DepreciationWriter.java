package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.service.DepreciationService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class DepreciationWriter implements ItemWriter<List<Depreciation>> {

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

        depreciationLists.forEach(
                depreciationList ->{

                    depreciationService.saveAllDepreciationItems(depreciationList);
                }
        );

    }
}
