package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;

import java.util.List;

public interface MonthlyAssetDepreciationService {

    /**
     * Return an ordered list of all monthly depreciation from the
     * monthlyAssetDepreciationRepository
     *
     * @return
     */
    List<MonthlyAssetDepreciation> fetchAllMonthlyDepreciations();

    /**
     * Returns the MonthlyAssetDepreciation for a given assetId and Year
     * @param fixedAssetId
     * @param year
     * @return
     */
    MonthlyAssetDepreciation getMonthlyAssetDepreciationByAssetIdAndYear(int fixedAssetId, int year);

    /**
     * Saves all new monthly depreciation items and updates exiting ones
     *
     * @param items
     */
    void saveAllMonthlyDepreciationItems(List<? extends MonthlyAssetDepreciation> items);
}
