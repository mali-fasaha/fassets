/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.model.nil;

import io.github.fasset.fasset.model.CategoryConfiguration;

public class NilCategoryConfiguration {

    private String designation = "Category";

    /**
     * The name of the depreciation logic
     */
    private String depreciationLogic="DECLININGBALANCE";

    /**
     * This is the item on which the depreciation rate is applied, as in either the cost
     * or the net book value
     */
    private String deprecant = "NETBOOKVALUE";

    private double depreciationRate = 0.00;

    private String categoryLedgerId = "0000000000";

    public CategoryConfiguration getCategoryConfiguration() {
        return new CategoryConfiguration(designation,depreciationLogic,deprecant,depreciationRate,categoryLedgerId);
    }
}
