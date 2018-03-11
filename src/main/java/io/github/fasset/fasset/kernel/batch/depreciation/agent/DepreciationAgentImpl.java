package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.CategoryConfigurationRegistry;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationPreprocessor;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.kernel.util.DepreciationExecutionException;
import io.github.fasset.fasset.model.CategoryConfiguration;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@DependsOn("depreciationExecutor")
@Component("depreciationAgent")
public class DepreciationAgentImpl implements DepreciationAgent{

    private final Logger log = LoggerFactory.getLogger(DepreciationAgentImpl.class);

    private CategoryConfigurationRegistry categoryConfigurationRegistry;
    private DepreciationPreprocessor preprocessor;

    @Autowired
    public DepreciationAgentImpl setCategoryConfigurationRegistry(CategoryConfigurationRegistry categoryConfigurationRegistry) {
        this.categoryConfigurationRegistry = categoryConfigurationRegistry;
        return this;
    }

    @Autowired
    public DepreciationAgentImpl setPreprocessor(DepreciationPreprocessor preprocessor) {
        this.preprocessor = preprocessor;
        return this;
    }

    @Cacheable("depreciationCalculation")
    @Override
    public Depreciation invoke(FixedAsset asset, YearMonth month, DepreciationProceeds proceeds) {

        Depreciation depreciation;

        log.trace("Calculating depreciation for fixedAsset {}", asset);

        String categoryName = asset.getCategory();

        log.trace("Fetching categoryConfiguration instance from repository for designation : {}", categoryName);

        CategoryConfiguration configuration = categoryConfigurationRegistry.getCategoryConfiguration(categoryName);

        log.trace("Using categoryConfiguration instance : {}", configuration);

        double depreciationRate = configuration.getDepreciationRate();

        Money deprecant = getDeprecant(asset, configuration);

        log.trace("Using deprecant : {}, and depreciation rate : {} for calculating depreciation", deprecant, depreciationRate);
        Money depreciationAmount = calculate(deprecant, depreciationRate);

        depreciation = getDepreciation(preprocessor.setAsset(asset).setMonth(month).setDepreciationAmount(depreciationAmount).setProperties());

        Money nbv = asset.getNetBookValue().subtract(depreciation.getDepreciation());

        //send changes to queue for flushing through entityManager
        //send(() -> depreciation);

        // set new nbv in the fixedAsset item
        asset.setNetBookValue(nbv);

        proceeds.setDepreciation(depreciation);

        return depreciation;
    }

    /**
     * This method determines the amount to be used as deprecant relative to the CategoryConfiguration
     * item passed
     *
     * @param asset FixedAsset item being depereciated
     * @param configuration CategoryConfiguration relevant to the asset being depreciated
     * @return amount of the deprecant
     */
    private Money getDeprecant(FixedAsset asset, CategoryConfiguration configuration) {

        log.trace("Determining the deprecant for Asset : {}, with category configuration : {}",
                asset,configuration);

        Money deprecant = null;

        try {
            if(configuration.getDeprecant().equalsIgnoreCase("purchaseCost")){

                deprecant = asset.getPurchaseCost();

                log.trace("Using purchase cost as deprecant : {}",deprecant);

            } else if(configuration.getDeprecant().equalsIgnoreCase("netBookValue")){

                deprecant = asset.getNetBookValue();

                log.trace("Using the netBookValue as deprecant : {}",deprecant);
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
    private Money calculate(Money deprecant, double depreciationRate){

        Money depreciation;

        try {
            log.trace("Calculating depreciation amount using deprecant of : {}, and depreciation rate of : {}", deprecant,depreciationRate);

            //depreciation = deprecant * depreciationRate;
            depreciation = deprecant.multiply(depreciationRate/100 * 1/12);

            log.trace("Depreciation for deprecant : {} and depreciationRate : {} calculated as : {}",deprecant,depreciationRate,depreciation);
        } catch (Throwable e) {
            String message = String.format("Exception encountered while calculating depreciation amount for " +
                    "deprecant amount of : %s and depreciation rate of :%s",deprecant,depreciationRate);
            throw new DepreciationExecutionException(message,e);
        }

        return depreciation;
    }

    /**
     * Creates a depreciation item using data from the preprocessor
     *
     * @param preprocessor DepreciationPreprocessor for the depreciation amounts
     * @return Depreciation item in accordance with the preprocessor passed
     */
    private Depreciation getDepreciation(DepreciationPreprocessor preprocessor){

        log.trace("Creating depreciation instance relative to the fixedAsset item : {} for the month : {}",preprocessor.getAsset(),preprocessor.getMonth());
        Depreciation depreciation = new Depreciation();
        try {
            depreciation
                    .setDepreciationPeriod(preprocessor.getMonth())
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

        log.trace("Returning depreciation instance : {}",depreciation);
        return depreciation;
    }

}
