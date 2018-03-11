package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.config.MoneyProperties;
import io.github.fasset.fasset.kernel.util.convert.DateToLocalDateConverter;
import io.github.fasset.fasset.kernel.util.convert.StringToDoubleConverter;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.kernel.util.convert.StringToMoneyConverter;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.FixedAssetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Creates a persist-ready {@link FixedAsset} object from the {@link FixedAssetDTO} object
 * coming from the ExcelItemReader
 *
 * @author edwin.njeru
 */
@Component("excelItemProcessor")
public class ExcelItemProcessor implements ItemProcessor<FixedAssetDTO,FixedAsset>{

    private static final Logger log  = LoggerFactory.getLogger(ExcelItemProcessor.class);



    private final DateToLocalDateConverter dateToLocalDateConverter;

    private final StringToMoneyConverter stringToMoneyConverter;

    @Autowired
    public ExcelItemProcessor(@Qualifier("dateToLocalDateConverter") DateToLocalDateConverter dateToLocalDateConverter, @Qualifier("stringToMoneyConverter") StringToMoneyConverter stringToMoneyConverter) {
        this.dateToLocalDateConverter = dateToLocalDateConverter;
        this.stringToMoneyConverter = stringToMoneyConverter;
    }


    @Override
    public FixedAsset process(FixedAssetDTO fixedAssetDTO) throws Exception {

        log.debug("Processing {}",fixedAssetDTO);

        FixedAsset fixedAsset = new FixedAsset();

        try {
            fixedAsset.setAssetDescription(fixedAssetDTO.getAssetDescription())
                    .setBarcode(fixedAssetDTO.getBarcode())
                    .setCategory(fixedAssetDTO.getCategory())
                    .setNetBookValue(stringToMoneyConverter.convert(fixedAssetDTO.getNetBookValue()))
                    .setPurchaseCost(stringToMoneyConverter.convert(fixedAssetDTO.getPurchaseCost()))
                    .setSolId(fixedAssetDTO.getSolId())
                    .setPurchaseDate(dateToLocalDateConverter.convert(fixedAssetDTO.getPurchaseDate()));

        } catch (Throwable e) {
            String message = String.format("Exception encountered while processing : {}",fixedAssetDTO);
            throw new BatchJobExecutionException(message,e);
        }

        return fixedAsset;
    }
}
