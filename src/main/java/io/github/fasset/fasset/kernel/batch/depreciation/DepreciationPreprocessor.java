package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

/**
 * Please note that no actual processing is done here. The interface reviews the asset
 * against the current depreciation amount and the date of purchase compared with the
 * month of depreciation.
 * After that is reviewed we return only the items that make sense in the business domain
 *
 * @author edwin.njeru
 */
public interface DepreciationPreprocessor {

    /**
     *
     * @return Depreciation period as month
     */
    YearMonth getMonth();

    /**
     *
     * @return FixedAsset item being depreciated
     */
    FixedAsset getAsset();

    /**
     *
     * @return amount of depreciation
     */
    double getDepreciationAmount();

    /**
     * Sets the asset to be reviewed for depreciation
     *
     * @param asset
     * @return
     */
    DepreciationPreprocessor setAsset(FixedAsset asset);

    /**
     * Sets depreciation period in months
     * @param month
     * @return
     */
    DepreciationPreprocessor setMonth(YearMonth month);

    /**
     * Sets the amount of depreciation for review
     *
     * @param depreciationAmount
     * @return
     */
    DepreciationPreprocessor setDepreciationAmount(double depreciationAmount);

    /**
     * This method ensures all properties are set and evaluated
     * @return
     */
    DepreciationPreprocessor setProperties();
}
