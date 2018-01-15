package io.github.fasset.fasset.Service;

import io.github.fasset.fasset.model.Depreciation;

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
}
