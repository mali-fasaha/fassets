package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.util.DepreciationExecutionException;
import io.github.fasset.fasset.service.*;
import io.github.fasset.fasset.model.*;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.Map;

/**
 * This class represents the main method which is to be abstracted by other layers that would
 * allow for flexibility in application of business rules, the main method is the
 * {@link DepreciationExecutor#getDepreciation(FixedAsset, YearMonth)} which able to extract a
 * {@link Depreciation} as long as you have a {@link FixedAsset} and the {@YearMonth} for which the
 * depreciation is to be calculated
 *
 * @author edwin.njeru
 */
@Service("depreciationExecutor")
@Transactional
public class DepreciationExecutor {

    private static final Logger log = LoggerFactory.getLogger(DepreciationExecutor.class);

    private final CategoryConfigurationService categoryConfigurationService;

    //private final DepreciationService depreciationService;

    private final NetBookValueService netBookValueService;

    private final AccruedDepreciationService accruedDepreciationService;

    private final DepreciationPreprocessor preprocessor;

    private Map<String,CategoryConfiguration> configurationMap = new UnifiedMap<>();

    @Autowired
    public DepreciationExecutor(@Qualifier("categoryConfigurationService") CategoryConfigurationService categoryConfigurationService,
                                @Qualifier("netBookValueService") NetBookValueService netBookValueService,
                                @Qualifier("accruedDepreciationService") AccruedDepreciationService accruedDepreciationService,
                                @Qualifier("depreciationPreprocessor") DepreciationPreprocessor preprocessor) {
        this.categoryConfigurationService = categoryConfigurationService;
        this.netBookValueService = netBookValueService;
        this.accruedDepreciationService = accruedDepreciationService;
        this.preprocessor = preprocessor;
    }


    /**
     * Returns a Depreciation object given the fixed asset, and updates the fixed asset with the new
     * net book value and the month of depreciation
     *
     * @param asset {@link FixedAsset} to be depreciated
     * @param month the month for which we are calculating depreciation
     * @return {@link Depreciation} object
     */
    //@Cacheable
    public Depreciation getDepreciation(FixedAsset asset, YearMonth month){

        updateConfigurationMap();

        log.debug("Calculating depreciation for fixedAsset {}",asset);

        String categoryName = asset.getCategory();

        log.debug("Fetching categoryConfiguration instance from repository for designation : {}",categoryName);

        CategoryConfiguration configuration = configurationMap.get(categoryName);

        log.debug("Using categoryConfiguration instance : {}",configuration);

        double depreciationRate = configuration.getDepreciationRate();

        double deprecant = getDeprecant(asset, configuration);

        log.debug("Using deprecant : {}, and depreciation rate : {} for calculating depreciation",deprecant,depreciationRate);
        double depreciationAmount = calculate(deprecant,depreciationRate);

        //Depreciation depreciation = getDepreciation(asset, month, depreciationAmount);
        Depreciation depreciation = getDepreciation(preprocessor.setAsset(asset).setMonth(month).setDepreciationAmount(depreciationAmount).setProperties());

        //asset.setNetBookValue(asset.getNetBookValue()-depreciationAmount);
        asset.setNetBookValue(asset.getNetBookValue()-depreciation.getDepreciation());

        NetBookValue netBookValue = getNetBookValue(asset, month);

        AccruedDepreciation accruedDepreciation = getAccruedDepreciation(asset, month, depreciation.getDepreciation());

        //TODO write comprehensive message services and batch
        //depreciationService.saveDepreciation(depreciation); //Written by depreciation writer
        netBookValueService.saveNetBookValue(netBookValue);
        accruedDepreciationService.saveAccruedDepreciation(accruedDepreciation);


        return depreciation;
    }

    private void updateConfigurationMap() {

        if(configurationMap.isEmpty()) {

            log.debug("Refreshing the category configuration mapping...");

            categoryConfigurationService
                    .getAllCategoryConfigurations()
                    .stream()
                    .map(CategoryConfiguration::getDesignation)
                    .forEach(categoryName -> {
                        configurationMap.put(categoryName, categoryConfigurationService.getCategoryByName(categoryName));
                    });
        }

    }

    /**
     * Creates a depreciation item using data from the preprocessor
     *
     * @param preprocessor
     * @return
     */
    private Depreciation getDepreciation(DepreciationPreprocessor preprocessor){

        log.debug("Creating depreciation instance relative to the fixedAsset item : {} for the month : {}",preprocessor.getAsset(),preprocessor.getMonth());
        Depreciation depreciation = new Depreciation();
        try {
            depreciation.setDepreciationPeriod(preprocessor.getMonth())
                    .setFixedAssetId(preprocessor.getAsset().getId())
                    .setCategory(preprocessor.getAsset().getCategory())
                    .setSolId(preprocessor.getAsset().getSolId())
                    .setYear(preprocessor.getMonth().getYear())
                    .setMonth(preprocessor.getMonth().getMonthValue())
                    .setDepreciation(preprocessor.getDepreciationAmount());
        } catch (Throwable e) {
            String message = String.format("Exception encountered while creating depreciation instance relative to" +
                    " asset : %s, for the period : %s",preprocessor.getAsset(),preprocessor.getMonth());
            throw new DepreciationExecutionException(message,e);
        }

        log.debug("Returning depreciation instance : {}",depreciation);
        return depreciation;
    }

    /**
     * Creates {@link AccruedDepreciation} instance relative to the parameter items and fixedAsset item given
     * @param asset
     * @param month
     * @param depreciationAmount
     * @return
     */
    private AccruedDepreciation getAccruedDepreciation(FixedAsset asset, YearMonth month, double depreciationAmount) {
        AccruedDepreciation accruedDepreciation = new AccruedDepreciation();

        log.debug("Creating accruedDepreciation instance relative to the asset : {}, for the month : {}",asset,month);

        try {
            accruedDepreciation.setCategory(asset.getCategory())
                    .setFixedAssetId(asset.getId())
                    .setCategory(asset.getCategory())
                    .setSolId(asset.getSolId())
                    .setAccruedDepreciation(accruedDepreciationService.getAccruedDepreciationForAsset(asset,month) + depreciationAmount);
        } catch (Throwable e) {
            String message = String.format("Exception encountered while creating accruedDepreciation instance relative" +
                    "to the asset : %s for the month : %s",asset,month);
            throw new DepreciationExecutionException(message,e);
        }

        log.debug("AccruedDepreciation instance created : {}",accruedDepreciation);

        return accruedDepreciation;
    }

    private NetBookValue getNetBookValue(FixedAsset asset, YearMonth month) {
        NetBookValue netBookValue = new NetBookValue();

        log.debug("Creating netBookValue instance relative to the asset : {} for the month : {}",asset,month);

        try {
            netBookValue.setFixedAssetId(asset.getId())
                    .setMonth(month)
                    .setSolId(asset.getSolId())
                    .setNetBookValue(asset.getNetBookValue());
        } catch (Throwable e) {
            String message = String.format("Exception encountered while creating netBookValue instance relative" +
                    "to the asset : %s for the month : %s",asset,month);
            throw new DepreciationExecutionException(message,e);
        }

        log.debug("NetBookValue item created : {}",netBookValue);

        return netBookValue;
    }

    private double getDeprecant(FixedAsset asset, CategoryConfiguration configuration) {

        log.debug("Determining the deprecant for Asset : {}, with category configuration : {}",
                asset,configuration);

        double deprecant = 0.00;

        try {
            if(configuration.getDeprecant().equalsIgnoreCase("purchaseCost")){

                deprecant = asset.getPurchaseCost();

                log.debug("Using purchase cost as deprecant : {}",deprecant);

            } else if(configuration.getDeprecant().equalsIgnoreCase("netBookValue")){

                deprecant = asset.getNetBookValue();

                log.debug("Using the netBookValue as deprecant : {}",deprecant);
            }
        } catch (Throwable e) {
            String message = String.format("Exception encountered while determining the deprecant applicable for the " +
                    "asset : %s, pursuant to the categoryConfiguration : %s",asset,configuration);
            throw new DepreciationExecutionException(message,e);
        }
        return deprecant;
    }

    /**
     * This calculates the depreciation amount per month
     *
     * @param deprecant the amount of the asset (cost or NBV) on which depreciation is calculated
     * @param depreciationRate the depreciation rate to use
     * @return amount of depreciation
     */
    //@Cacheable
    public double calculate(double deprecant, double depreciationRate){

        double depreciation = 0.00;

        try {
            log.debug("Calculating depreciation amount using deprecant of : {}, and depreciation rate of : {}", deprecant,depreciationRate);

            depreciation = deprecant * depreciationRate/100 * 1/12;

            log.debug("Depreciation for deprecant : {} and depreciationRate : {} calculated as : {}",deprecant,depreciationRate,depreciation);
        } catch (Throwable e) {
            String message = String.format("Exception encountered while calculating depreciation amount for " +
                    "deprecant amount of : %s and depreciation rate of :%s",deprecant,depreciationRate);
            throw new DepreciationExecutionException(message,e);
        }

        return depreciation;
    }
}
