package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.service.CategoryConfigurationService;
import io.github.fasset.fasset.model.CategoryConfiguration;
import io.github.fasset.fasset.repository.CategoryConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryConfigurationService")
public class CategoryConfigurationServiceImpl implements CategoryConfigurationService {

    private static final Logger log = LoggerFactory.getLogger(CategoryConfigurationServiceImpl.class);


    private final CategoryConfigurationRepository fixedAssetCategoryRepository;

    @Autowired
    public CategoryConfigurationServiceImpl(@Qualifier("categoryConfigurationRepository") CategoryConfigurationRepository fixedAssetCategoryRepository) {
        this.fixedAssetCategoryRepository = fixedAssetCategoryRepository;
    }



    /**
     * @return Return all fixed assets categories
     */
    @Override
    public List<CategoryConfiguration> getAllCategoryConfigurations() {

        return fixedAssetCategoryRepository.findAll();
    }

    /**
     * @param fixedAssetCategory to be saved to fixedAssetCategory repository
     */
    @Override
    public void saveCategoryConfiguration(CategoryConfiguration fixedAssetCategory) {

        fixedAssetCategoryRepository.save(fixedAssetCategory);
    }

    @Override
    public CategoryConfiguration getCategoryConfigurationById(int id) {

        return fixedAssetCategoryRepository.findById(id).get();
    }
}
