package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.FixedAssetCategory;

import java.util.Collection;

public interface FixedAssetCategoryService {



    /**
     *
     * @return Return all fixed assets categories
     */
    Collection<?> getAllFixedAssetCategories();

    /**
     *
     * @param fixedAssetCategory to be saved to fixedAssetCategory repository
     */
    void saveFixedAssetCategory(FixedAssetCategory fixedAssetCategory);
}
