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
package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;

import java.util.List;

/**
 * Service for data retrieval from database for {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface MonthlyCategoryDepreciationService {

    /**
     * <p>fetchAllMonthlyCategoryDepreciations.</p>
     *
     * @return A List of all monthlyCategoryDepreciation items currently in the repository
     */
    List<MonthlyCategoryDepreciation> fetchAllMonthlyCategoryDepreciations();

    /**
     * Save all items in the parameter to the monthlyCategoryDepreciationRepository
     *
     * @param items to be saved in the repository
     */
    void saveAllMonthlyCategoryDepreciations(List<? extends MonthlyCategoryDepreciation> items);
}
