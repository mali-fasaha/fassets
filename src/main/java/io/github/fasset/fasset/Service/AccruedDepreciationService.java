package io.github.fasset.fasset.Service;

import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

public interface AccruedDepreciationService {

    /**
     * Returns the accruedDepreciationAmount for a fixed asset and month given as param
     * @param asset
     * @return
     */
    double getAccruedDepreciationForAsset(FixedAsset asset, YearMonth month);

    /**
     * Saves the {@link AccruedDepreciation} object given in the parameter
     * @param accruedDepreciation
     */
    void saveAccruedDepreciation(AccruedDepreciation accruedDepreciation);
}
