package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.service.AccruedDepreciationService;
import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.repository.AccruedDepreciationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.util.List;

@Service("accruedDepreciationService")
@Transactional
public class AccruedDepreciationServiceImpl implements AccruedDepreciationService {

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationServiceImpl.class);


    @Qualifier("accruedDepreciationRepository")
    @Autowired
    private AccruedDepreciationRepository accruedDepreciationRepository;

    /**
     * Returns the accruedDepreciationAmount for a fixed asset  and month given as param.
     * This will give the accrued depreciation up to the previous month
     *
     * @param asset the asset for which we week accrued depreciation
     * @param month the previous of which the depreciation has accrued up to
     * @return amount of accrued depreciation in double precision
     */
    @Override
    @Cacheable
    public double getAccruedDepreciationForAsset(FixedAsset asset, YearMonth month) {

        log.debug("Fetching the AccruedDepreciation for assetId : {}, for the month : {}",asset.getId(),month);

        return accruedDepreciationRepository.findByFixedAssetIdAndMonthBefore(asset.getId(),month).getAccruedDepreciation();
    }

    /**
     * Saves the {@link AccruedDepreciation} object given in the parameter
     *
     * @param accruedDepreciation {@link AccruedDepreciation} object to be saved
     */
    @Override
    public void saveAccruedDepreciation(AccruedDepreciation accruedDepreciation) {

        log.debug("Saving AccruedDepreciationId : {} into the AccruedDepreciationRepository",accruedDepreciation);

        accruedDepreciationRepository.save(accruedDepreciation);
    }


    /**
     * Saves a {@link List} collection of {@link AccruedDepreciation} items
     *
     * @param items
     */
    @Override
    public void saveAllAccruedDepreciationRecords(List<? extends AccruedDepreciation> items) {

        log.info("Saving a collection of : {} accruedDepreciation items", items.size());

        accruedDepreciationRepository.saveAll(items);
    }
}
