package io.github.fasset.fasset.kernel.batch.depreciation.effects.accrued;

import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.model.AccruedDepreciation;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationDtoProcessor")
public class AccruedDepreciationDtoProcessor implements ItemProcessor<AccruedDepreciationDto,AccruedDepreciation>{

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
    public AccruedDepreciation process(AccruedDepreciationDto item) throws Exception {

        return item.getPayload();
    }
}
