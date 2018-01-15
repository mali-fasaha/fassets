package io.github.fasset.fasset.kernel.excel;

import io.github.fasset.fasset.model.FixedAssetDTO;

import java.util.List;

/**
 * This interface generates collection of data from an excel file given the fileName
 */
public interface ExcelMapperService {

    /**
     *
     * @param fileName where the excel file is located
     * @return {@link List<FixedAssetDTO>} from the fileName
     */
    List<FixedAssetDTO> fetchExcelData(String fileName);
}
