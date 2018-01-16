package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.FixedAssetCategory;

public interface FixedAssetCategoryService {

    /**
     * Returns the fixed asset category given the string name of the category
     * @param category
     * @return FixedAssetCategory from database
     */
    FixedAssetCategory getFixedAssetCategory(String category);
}
