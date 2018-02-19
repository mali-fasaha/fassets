package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;

import java.util.List;

public interface MonthlyCategoryDepreciationService {

    /**
     *
     * @return A List of all monthlyCategoryDepreciation items currently in the
     * repository
     */
    List<MonthlyCategoryDepreciation> fetchAllMonthlyCategoryDepreciations();

    /**
     * Save all items in the parameter to the monthlyCategoryDepreciationRepository
     *
     * @param items to be saved in the repository
     */
    void saveAllMonthlyCategoryDepreciations(List<? extends MonthlyCategoryDepreciation> items);
}
