package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import io.github.fasset.fasset.repository.MonthlyCategoryDepreciationReposiory;
import io.github.fasset.fasset.service.MonthlyCategoryDepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("monthlyCategoryDepreciationService")
@Transactional
public class MonthlyCategoryDepreciationServiceImpl implements MonthlyCategoryDepreciationService{


    private final MonthlyCategoryDepreciationReposiory depreciationReposiory;

    @Autowired
    public MonthlyCategoryDepreciationServiceImpl(@Qualifier("monthlyCategoryDepreciationRepository") MonthlyCategoryDepreciationReposiory depreciationReposiory) {
        this.depreciationReposiory = depreciationReposiory;
    }


    /**
     * @return A List of all monthlyCategoryDepreciation items currently in the
     * repository
     */
    @Override
    public List<MonthlyCategoryDepreciation> fetchAllMonthlyCategoryDepreciations() {

        return depreciationReposiory.findAll()
                .parallelStream()
                .sorted(Comparator.comparing(MonthlyCategoryDepreciation::getCategoryName))
                .collect(ImmutableListCollector.toImmutableList());
    }

    /**
     * Save all items in the parameter to the monthlyCategoryDepreciationRepository
     *
     * @param items to be saved in the repository
     */
    @Override
    public void saveAllMonthlyCategoryDepreciations(List<? extends MonthlyCategoryDepreciation> items) {

        depreciationReposiory.saveAll(items);
    }
}
