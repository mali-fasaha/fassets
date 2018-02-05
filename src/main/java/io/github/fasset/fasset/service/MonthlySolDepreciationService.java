package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;

import java.util.List;

public interface MonthlySolDepreciationService {

    /**
     *
     * @return A list of all items in the MonthlySolDepreciationRepository
     */
    List<MonthlySolDepreciation> fetchAllMonthlySolDepreciations();

    /**
     * Save all the items in the parameter into the MonthlySolDepreciationRepository
     *
     * @param items
     */
    void saveAllMonthlyDepreciationItems(List<? extends MonthlySolDepreciation> items);
}
