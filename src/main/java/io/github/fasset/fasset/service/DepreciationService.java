package io.github.fasset.fasset.service;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.model.Depreciation;

import java.util.List;

/**
 * This objects extracts and saves data into the {@link io.github.fasset.fasset.repository.DepreciationRepository}
 *
 * @author edwin.njeru
 */
public interface DepreciationService {

    /**
     * Saves the {@link Depreciation} object given as parameter to the {@link io.github.fasset.fasset.repository.DepreciationRepository}
     * @param depreciation
     */
    void saveDepreciation(Depreciation depreciation);

    /**
     * Saves all items in the list
     * @param depreciationList
     */
    void saveAllDepreciationItems(List<Depreciation> depreciationList);

    /**
     *
     * @return Return the number of distinct sols
     */
    int getDistinctSolIds();

    /**
     * Saves multiple items using multiple repositories for items encapsulated in the
     * DepreciationProceeds object
     *
     * @param list of depreciationProceeds
     */
    void saveAllDepreciationProceeds(List<DepreciationProceeds> list);
}
