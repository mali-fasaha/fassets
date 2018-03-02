package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import io.github.fasset.fasset.repository.MonthlySolDepreciationRepository;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import io.github.fasset.fasset.service.MonthlySolDepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("monthlySolDepreciationService")
@Transactional
public class MonthlySolDepreciationServiceImpl implements MonthlySolDepreciationService {


    @Qualifier("monthlySolDepreciationRepository")
    @Autowired
    private MonthlySolDepreciationRepository monthlySolDepreciationRepository;

    /**
     * @return A list of all items in the MonthlySolDepreciationRepository
     */
    @Override
    public List<MonthlySolDepreciation> fetchAllMonthlySolDepreciations() {

        return monthlySolDepreciationRepository.findAll()
                .parallelStream()
                .sorted(Comparator.comparing(MonthlySolDepreciation::getSolId))
                .collect(ImmutableListCollector.toImmutableList());
    }

    /**
     * Save all the items in the parameter into the MonthlySolDepreciationRepository
     *
     * @param items
     */
    @Override
    public void saveAllMonthlyDepreciationItems(List<? extends MonthlySolDepreciation> items) {

        monthlySolDepreciationRepository.save(items);
    }
}
