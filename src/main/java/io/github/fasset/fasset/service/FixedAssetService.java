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

package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

import java.util.List;

public interface FixedAssetService {

    /**
     * Saves all {@link FixedAsset} items passed in a list, avoiding duplicate items
     *
     * @param fixedAssets
     */
    void saveAllFixedAssets(List<? extends FixedAsset> fixedAssets);

    /**
     * Fetches a List of all existing items in the {@link io.github.fasset.fasset.repository.FixedAssetRepository}
     *
     * @return
     */
    List<FixedAsset> fetchAllExistingAssets();

    /**
     * Extracts the fixed asset when the id is known
     *
     * @param id
     * @return
     */
    FixedAsset fetchAssetGivenId(int id);

    /**
     * By querying the {@link io.github.fasset.fasset.repository.FixedAssetRepository} this method
     * is able to create a {@link CategoryBrief} for the category given in the parameter
     *
     * @param category for which we are preparing a brief
     * @return {@link CategoryBrief}
     */
    CategoryBrief getCategoryBrief(String category);

    /**
     * By querying the {@link io.github.fasset.fasset.repository.FixedAssetRepository} this method
     * is able to create a {@link ServiceOutletBrief} for the SOL queried in the parameter
     *
     * @param solId for which we are preparing a brief
     * @return {@link CategoryBrief}
     */
    ServiceOutletBrief getServiceOutletBrief(String solId);

    /**
     * @return A unique list of all solIds in the database
     */
    List<String> getAllSolIds();

    /**
     * @return A unique list of all categories in the database
     */
    List<String> getAllCategories();

    /**
     * @param fixedAsset to be saved to fixedAssetRepository
     */
    FixedAsset saveFixedAsset(FixedAsset fixedAsset);

    /**
     * @return # of assets
     */
    int getPoll();
}
