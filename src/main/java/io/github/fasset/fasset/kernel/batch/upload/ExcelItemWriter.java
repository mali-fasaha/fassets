package io.github.fasset.fasset.kernel.batch.upload;


import io.github.fasset.fasset.Service.FixedAssetService;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("excelItemWriter")
public class ExcelItemWriter implements ItemWriter<FixedAsset> {

    @Autowired
    @Qualifier("fixedAssetService")
    private FixedAssetService fixedAssetService;


    @Override
    public void write(List<? extends FixedAsset> list) throws Exception {

        fixedAssetService.saveAllFixedAssets(list);
    }
}
