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

import io.github.fasset.fasset.model.brief.CategoryBrief;

import java.util.List;

/**
 * Service for data retrieval from database for {@link io.github.fasset.fasset.model.brief.CategoryBrief}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface CategoryBriefService {

    /**
     * <p>fetchAllCategoryBriefs.</p>
     *
     * @return {@link java.util.List} of {@link io.github.fasset.fasset.model.brief.CategoryBrief} items fetched from the repository
     */
    List<CategoryBrief> fetchAllCategoryBriefs();

    /**
     * <p>fetchCategoryBriefGivenId.</p>
     *
     * @param id of the CategoryBrief
     * @return {@link io.github.fasset.fasset.model.brief.CategoryBrief} of the nomenclature given as param
     */
    CategoryBrief fetchCategoryBriefGivenId(int id);

    /**
     * <p>saveAllCategoryBriefItems.</p>
     *
     * @param categoryBriefs items to be saved to repository
     */
    void saveAllCategoryBriefItems(List<? extends CategoryBrief> categoryBriefs);
}
