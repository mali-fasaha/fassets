package io.github.fasset.fasset.kernel.excel;

import io.github.fasset.fasset.kernel.excel.mapping.ExcelDataProvider;
import io.github.fasset.fasset.model.FixedAssetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("excelMapperService")
public class ExcelMapperServiceImpl implements ExcelMapperService{


    @Autowired
    private ExcelDataProvider excelDataProvider;

    /**
     * @param fileName where the excel file is located
     * @return {@link List < FixedAssetDTO >} from the fileName
     */
    @Override
    public List<FixedAssetDTO> fetchExcelData(String fileName) {

        return excelDataProvider.generateMappedList(fileName,FixedAssetDTO.class);
    }
}
