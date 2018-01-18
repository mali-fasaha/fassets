package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.FixedAsset;

import java.util.List;

public interface FixedAssetService {

    /**
     * Saves all {@link FixedAsset} items passed in a list
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
}
