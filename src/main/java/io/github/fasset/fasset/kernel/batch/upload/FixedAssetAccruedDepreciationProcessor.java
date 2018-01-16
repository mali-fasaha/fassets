package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.util.ConverterException;
import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component("fixedAssetAccruedDepreciationProcessor")
public class FixedAssetAccruedDepreciationProcessor implements ItemProcessor<FixedAsset,AccruedDepreciation> {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetAccruedDepreciationProcessor.class);

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
    public AccruedDepreciation process(FixedAsset fixedAsset) throws Exception {

        log.debug("Processing fixedAsset item : {}",fixedAsset);

        AccruedDepreciation retVal = new AccruedDepreciation();

        double acc = fixedAsset.getPurchaseCost() - fixedAsset.getNetBookValue();

        try {
            retVal.setCategory(fixedAsset.getCategory())
                    .setFixedAssetId(fixedAsset.getId())
                    .setSolId(fixedAsset.getSolId())
                    .setMonth(YearMonth.of(2017,12))
                    .setAccruedDepreciation(acc);
        } catch (Throwable e) {
            String message = String.format("Exception encountered while deriving accruedDepreciation from" +
                    "%S",fixedAsset);

            throw new ConverterException(message,e);
        }

        return retVal;
    }
}
