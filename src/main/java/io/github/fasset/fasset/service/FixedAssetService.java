package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

import java.util.List;

public interface FixedAssetService {

    /**
     * Saves all {@link FixedAsset} items passed in a list, avoiding duplicate items
     * @param fixedAssets
     */
    void saveAllFixedAssets(List<? extends FixedAsset> fixedAssets);

    /**
     * Fetches a List of all existing items in the {@link io.github.fasset.fasset.repository.FixedAssetRepository}
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
     * @param category for which we are preparing a brief
     * @return {@link CategoryBrief}
     */
    CategoryBrief getCategoryBrief(String category);

    /**
     * By querying the {@link io.github.fasset.fasset.repository.FixedAssetRepository} this method
     * is able to create a {@link ServiceOutletBrief} for the SOL queried in the parameter
     * @param solId for which we are preparing a brief
     * @return {@link CategoryBrief}
     */
    ServiceOutletBrief getServiceOutletBrief(String solId);

    /**
     *
     * @return A unique list of all solIds in the database
     */
    List<String> getAllSolIds();

    /**
     *
     * @return A unique list of all categories in the database
     */
    List<String> getAllCategories();

    /**
     *
     * @param fixedAsset to be saved to fixedAssetRepository
     */
    void saveFixedAsset(FixedAsset fixedAsset);

    /**
     *
     * @return # of assets
     */
    int getPoll();
}
