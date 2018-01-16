package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.repository.FixedAssetRepository;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.service.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fixedAssetService")
public class FixedAssetServiceImpl implements FixedAssetService {

    @Autowired
    @Qualifier("fixedAssetRepository")
    private FixedAssetRepository fixedAssetRepository;

    /**
     * Saves all {@link FixedAsset} items passed in a list
     *
     * @param fixedAssets
     */
    @Override
    public void saveAllFixedAssets(List<? extends FixedAsset> fixedAssets) {

        fixedAssetRepository.saveAll(fixedAssets);
    }

    /**
     * Fetches a List of all existing items in the {@link FixedAssetRepository}
     *
     * @return
     */
    @Override
    public List<FixedAsset> fetchAllExistingAssets() {

        return fixedAssetRepository.findAll()
                .parallelStream()
                .sorted()
                .collect(ImmutableListCollector.toImmutableList());
    }
}
