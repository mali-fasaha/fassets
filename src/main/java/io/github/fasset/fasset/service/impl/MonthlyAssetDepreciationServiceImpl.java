package io.github.fasset.fasset.service.impl;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.repository.MonthlyAssetDepreciationRepository;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("monthlyAssetDepreciationService")
public class MonthlyAssetDepreciationServiceImpl implements MonthlyAssetDepreciationService{


    private final MonthlyAssetDepreciationRepository monthlyAssetDepreciationRepository;

    @Autowired
    public MonthlyAssetDepreciationServiceImpl(@Qualifier("monthlyAssetDepreciationRepository") MonthlyAssetDepreciationRepository monthlyAssetDepreciationRepository) {
        this.monthlyAssetDepreciationRepository = monthlyAssetDepreciationRepository;
    }

    /**
     * Return an ordered list of all monthly depreciation from the
     * monthlyAssetDepreciationRepository
     *
     * @return
     */
    @Override
    public List<MonthlyAssetDepreciation> fetchAllMonthlyDepreciations() {
        return monthlyAssetDepreciationRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MonthlyAssetDepreciation::getYear))
                .collect(Collectors.toList());
    }

    /**
     * Returns the MonthlyAssetDepreciation for a given assetId and Year
     *
     * @param fixedAssetId
     * @param year
     * @return
     */
    @Override
    @Cacheable("monthlyAssetDepreciationByIdAnYears")
    public MonthlyAssetDepreciation getMonthlyAssetDepreciationByAssetIdAndYear(int fixedAssetId, int year) {

        return monthlyAssetDepreciationRepository.findFirstByAssetIdAndYearEquals(fixedAssetId,year);
    }

    /**
     * Saves all new monthly depreciation items and updates exiting ones
     *
     * @param items
     */
    @Override
    public void saveAllMonthlyDepreciationItems(List<? extends MonthlyAssetDepreciation> items) {

        /*Set<MonthlyAssetDepreciation> filter = new UnifiedSet<>();

        filter.addAll(monthlyAssetDepreciationRepository.findAll());

        List<MonthlyAssetDepreciation> savedItems = new FastList<>();*/

        /*items
                .stream()
                .filter(i -> !monthlyAssetDepreciationRepository.exists(i.getId()))
                .forEach(unsavedItems::add);*/
        /*items
                .stream()
                .filter(filter::contains)
                .forEach(savedItems::add);

        //items.removeAll(savedItems);

        savedItems.forEach(i -> monthlyAssetDepreciationRepository.deleteById(i.getId()));*/

        monthlyAssetDepreciationRepository.saveAll(items);
    }
}
