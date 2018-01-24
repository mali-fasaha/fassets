package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.CategoryConfiguration;

import java.util.Collection;
import java.util.List;

public interface CategoryConfigurationService {



    /**
     *
     * @return Return all fixed assets categories
     */
    List<CategoryConfiguration> getAllCategoryConfigurations();

    /**
     *
     * @param fixedAssetCategory to be saved to fixedAssetCategory repository
     */
    void saveCategoryConfiguration(CategoryConfiguration fixedAssetCategory);

    CategoryConfiguration getCategoryConfigurationById(int id);

    CategoryConfiguration getCategoryByName(String categoryName);

    double getDepreciationRateByCategoryName(String categoryName);
}
