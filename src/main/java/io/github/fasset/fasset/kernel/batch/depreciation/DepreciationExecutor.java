package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.service.*;
import io.github.fasset.fasset.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

/**
 * This class represents the main method which is to be abstracted by other layers that would
 * allow for flexibility in application of business rules, the main method is the
 * {@link DepreciationExecutor#getDepreciation(FixedAsset, YearMonth)} which able to extract a
 * {@link Depreciation} as long as you have a {@link FixedAsset} and the {@YearMonth} for which the
 * depreciation is to be calculated
 *
 * @author edwin.njeru
 */
@Component("depreciationExecutor")
public class DepreciationExecutor {

    private static final Logger log = LoggerFactory.getLogger(DepreciationExecutor.class);

    private final CategoryConfigurationService categoryConfigurationService;

    private final DepreciationService depreciationService;

    private final NetBookValueService netBookValueService;

    private final AccruedDepreciationService accruedDepreciationService;

    @Autowired
    public DepreciationExecutor(@Qualifier("categoryConfigurationService") CategoryConfigurationService categoryConfigurationService, @Qualifier("depreciationService") DepreciationService depreciationService, @Qualifier("netBookValueService") NetBookValueService netBookValueService, @Qualifier("accruedDepreciationService") AccruedDepreciationService accruedDepreciationService) {
        this.categoryConfigurationService = categoryConfigurationService;
        this.depreciationService = depreciationService;
        this.netBookValueService = netBookValueService;
        this.accruedDepreciationService = accruedDepreciationService;
    }


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

        CategoryConfiguration configuration = categoryConfigurationService.getCategoryByName(categoryName);

        double depreciationRate = configuration.getDepreciationRate();

        double deprecant = getDeprecant(asset, configuration);

        Depreciation depreciation = new Depreciation();

        log.debug("Using deprecant : {}, and depreciation rate : {} for calculating depreciation",deprecant,depreciationRate);
        double depreciationAmount = calculate(deprecant,depreciationRate);

        depreciation.setDepreciationPeriod(month)
                .setFixedAssetId(asset.getId())
                .setCategory(asset.getCategory())
                .setSolId(asset.getSolId())
                .setDepreciation(depreciationAmount);

        asset.setNetBookValue(asset.getNetBookValue()-depreciationAmount);

        NetBookValue netBookValue = getNetBookValue(asset, month);

        AccruedDepreciation accruedDepreciation = getAccruedDepreciation(asset, month, depreciationAmount);

        //TODO write comprehensive message services and batch
        depreciationService.saveDepreciation(depreciation);
        netBookValueService.saveNetBookValue(netBookValue);
        accruedDepreciationService.saveAccruedDepreciation(accruedDepreciation);


        return depreciation;
    }

    private AccruedDepreciation getAccruedDepreciation(FixedAsset asset, YearMonth month, double depreciationAmount) {
        AccruedDepreciation accruedDepreciation = new AccruedDepreciation();
        accruedDepreciation.setCategory(asset.getCategory())
                .setFixedAssetId(asset.getId())
                .setCategory(asset.getCategory())
                .setSolId(asset.getSolId())
                .setAccruedDepreciation(accruedDepreciationService.getAccruedDepreciationForAsset(asset,month) + depreciationAmount);
        return accruedDepreciation;
    }

    private NetBookValue getNetBookValue(FixedAsset asset, YearMonth month) {
        NetBookValue netBookValue = new NetBookValue();

        netBookValue.setFixedAssetId(asset.getId())
                .setMonth(month)
                .setSolId(asset.getSolId())
                .setNetBookValue(asset.getNetBookValue());
        return netBookValue;
    }

    private double getDeprecant(FixedAsset asset, CategoryConfiguration configuration) {

        double deprecant = 0.00;

        if(configuration.getDeprecant().equalsIgnoreCase("purchaseCost")){

            deprecant = asset.getPurchaseCost();
        } else if(configuration.getDeprecant().equalsIgnoreCase("netBookValue")){

            deprecant = asset.getNetBookValue();
        }
        return deprecant;
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

        return deprecant * depreciationRate/100;
    }
}
