package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.util.ConverterException;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component("fixedAssetNetBookValueProcessor")
public class FixedAssetNetBookValueProcessor implements ItemProcessor<FixedAsset,NetBookValue> {

    private final static Logger log = LoggerFactory.getLogger(FixedAssetNetBookValueProcessor.class);

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
    public NetBookValue process(FixedAsset fixedAsset) throws Exception {

        log.debug("Processing fixedAsset item : {}",fixedAsset);

        NetBookValue retVal = new NetBookValue();

        try {
            retVal.setFixedAssetId(fixedAsset.getId())
                    .setCategory(fixedAsset.getCategory())
                    .setSolId(fixedAsset.getSolId())
                    .setMonth(YearMonth.of(2017,12))//TODO configure to do this from controller
                    .setNetBookValue(fixedAsset.getNetBookValue());
        } catch (Throwable e) {
            String message = String.format("Exception encountered while processing fixedAsset item : %s",fixedAsset);
            throw new ConverterException(message,e);
        }

        return retVal;
    }
}
