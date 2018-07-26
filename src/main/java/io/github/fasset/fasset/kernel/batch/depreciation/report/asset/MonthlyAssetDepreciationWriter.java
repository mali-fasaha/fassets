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
package io.github.fasset.fasset.kernel.batch.depreciation.report.asset;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Writes the MonthlyAssetDepreciation from the processor to persistent repository
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class MonthlyAssetDepreciationWriter implements ItemWriter<MonthlyAssetDepreciation> {


    private final MonthlyAssetDepreciationService monthlyAssetDepreciationService;

    /**
     * <p>Constructor for MonthlyAssetDepreciationWriter.</p>
     *
     * @param monthlyAssetDepreciationService a {@link io.github.fasset.fasset.service.MonthlyAssetDepreciationService} object.
     */
    public MonthlyAssetDepreciationWriter(MonthlyAssetDepreciationService monthlyAssetDepreciationService) {
        this.monthlyAssetDepreciationService = monthlyAssetDepreciationService;
    }

    /**
     * {@inheritDoc}
     *
     * Process the supplied data element. Will not be called with any null items in normal operation.
     */
    @Override
    public void write(List<? extends MonthlyAssetDepreciation> items) throws Exception {

        monthlyAssetDepreciationService.saveAllMonthlyDepreciationItems(items);
    }
}
