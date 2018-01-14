package io.github.fasset.fasset.Service;

import io.github.fasset.fasset.FixedAssetCategory;
import io.github.fasset.fasset.repository.FixedAssetCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("fixedAssetCategoryService")
public class FixedAssetCategoryServiceImpl implements FixedAssetCategoryService {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetCategoryServiceImpl.class);


    private final FixedAssetCategoryRepository fixedAssetCategoryRepository;

    @Autowired
    public FixedAssetCategoryServiceImpl(@Qualifier("fixedAssetCategoryRepository") FixedAssetCategoryRepository fixedAssetCategoryRepository) {
        this.fixedAssetCategoryRepository = fixedAssetCategoryRepository;
    }

    /**
     * Returns the fixed asset category given the string name of the category
     *
     * @param category
     * @return FixedAssetCategory from database
     */
    @Override
    @Cacheable
    public FixedAssetCategory getFixedAssetCategory(String category) {

        log.debug("Fetching the assetCategory for the name : {}",category);

        return fixedAssetCategoryRepository.findByCategory(category);
    }
}
