package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.Depreciation;
import io.github.fasset.fasset.FixedAsset;
import io.github.fasset.fasset.FixedAssetCategory;
import io.github.fasset.fasset.Service.FixedAssetCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;

import java.time.YearMonth;

/**
 * This class represents the main method which is to be abstracted by other layers that would
 * allow for flexibility in application of business rules, the main method is the
 * {@link DepreciationCalculation#getDepreciation(FixedAsset, YearMonth)} which able to extract a
 * {@link Depreciation} as long as you have a {@link FixedAsset} and the {@YearMonth} for which the
 * depreciation is to be calculated
 *
 * @author edwin.njeru
 */
public class DepreciationCalculation {

    private static final Logger log = LoggerFactory.getLogger(DepreciationCalculation.class);

    @Autowired
    @Qualifier("fixedAssetCategoryService")
    private FixedAssetCategoryService fixedAssetCategoryService;



    /**
     * Returns a Depreciation object given the fixed asset, and updates the fixed asset with the new
     * net book value and the month of depreciation
     *
     * @param asset {@link FixedAsset} to be depreciated
     * @param month the month for which we are calculating depreciation
     * @return {@link Depreciation} object
     */
    @Cacheable
    public Depreciation getDepreciation(FixedAsset asset, YearMonth month){

        log.debug("Calculating depreciation for fixedAsset {}",asset);

        String categoryName = asset.getCategory();

        log.debug("Fetching fixedAssetCategory from the fixedAssetCategoryServiceService using param : {}",categoryName);
        FixedAssetCategory fixedAssetCategory = fixedAssetCategoryService.getFixedAssetCategory(categoryName);

        double deprecant = 0.00;
        double depreciationRate = fixedAssetCategory.getDepreciationRate().getDepreciationRate();

        if(fixedAssetCategory.getDepreciationLogic().getDeprecant().equalsIgnoreCase("purchaseCost")){

            deprecant = asset.getPurchaseCost();
        } else if(fixedAssetCategory.getDepreciationLogic().getDeprecant().equalsIgnoreCase("netBookValue")){

            deprecant = asset.getNetBookValue();
        }

        Depreciation retVal = new Depreciation();

        log.debug("Using deprecant : {}, and depreciation rate : {} for calculating depreciation",deprecant,depreciationRate);
        double depreciationAmount = calculate(deprecant,depreciationRate);

        retVal.setDepreciationPeriod(month)
                .setFixedAssetId(asset.getId())
                .setCategory(asset.getCategory())
                .setSolId(asset.getSolId())
                .setDepreciation(depreciationAmount);

        asset.setNetBookValue(asset.getNetBookValue()-depreciationAmount);


        return retVal;

    }

    /**
     * This calculates the depreciation amount
     *
     * @param deprecant the amount of the asset (cost or NBV) on which depreciation is calculated
     * @param depreciationRate the depreciation rate to use
     * @return amount of depreciation
     */
    @Cacheable
    public double calculate(double deprecant, double depreciationRate){

        log.debug("Calculating depreciation amount using deprecant of : {}, and depreciation rate of : {}", deprecant,depreciationRate);

        return deprecant * depreciationRate;
    }
}
