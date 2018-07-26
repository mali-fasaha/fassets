/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.excel;

import io.github.fasset.fasset.kernel.excel.mapping.ExcelDataProvider;
import io.github.fasset.fasset.model.FixedAssetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Creates a map of the fields in the FixedAssetDto to be read from the excel file
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("excelMapperService")
public class ExcelMapperServiceImpl implements ExcelMapperService {


    private final ExcelDataProvider excelDataProvider;

    /**
     * <p>Constructor for ExcelMapperServiceImpl.</p>
     *
     * @param excelDataProvider a {@link io.github.fasset.fasset.kernel.excel.mapping.ExcelDataProvider} object.
     */
    @Autowired
    public ExcelMapperServiceImpl(ExcelDataProvider excelDataProvider) {
        this.excelDataProvider = excelDataProvider;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public List<FixedAssetDTO> fetchExcelData(String fileName) {

        return excelDataProvider.generateMappedList(fileName, FixedAssetDTO.class);
    }
}
