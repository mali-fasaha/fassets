package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.service.FixedAssetCategoryService;
import io.github.fasset.fasset.model.FixedAssetCategory;
import io.github.fasset.fasset.repository.FixedAssetCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("fixedAssetCategoryService")
public class FixedAssetCategoryServiceImpl implements FixedAssetCategoryService {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetCategoryServiceImpl.class);


    private final FixedAssetCategoryRepository fixedAssetCategoryRepository;

    @Autowired
    public FixedAssetCategoryServiceImpl(@Qualifier("fixedAssetCategoryRepository") FixedAssetCategoryRepository fixedAssetCategoryRepository) {
        this.fixedAssetCategoryRepository = fixedAssetCategoryRepository;
    }



    /**
     * @return Return all fixed assets categories
     */
    @Override
    public Collection<?> getAllFixedAssetCategories() {

        return fixedAssetCategoryRepository.findAll();
    }

    /**
     * @param fixedAssetCategory to be saved to fixedAssetCategory repository
     */
    @Override
    public void saveFixedAssetCategory(FixedAssetCategory fixedAssetCategory) {

        fixedAssetCategoryRepository.save(fixedAssetCategory);
    }
}
