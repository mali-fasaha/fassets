package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.LocalDateToYearMonthConverter;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.model.NilDepreciation;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

/**
 * This class represents the main method which is to be abstracted by other layers that would
 * allow for flexibility in application of business rules, the main method is the
 * {@link DepreciationExecutorImpl#getDepreciation(FixedAsset, YearMonth)} which able to extract a
 * {@link Depreciation} as long as you have a {@link FixedAsset} and the {@link YearMonth} month for which the
 * depreciation is to be calculated
 *
 * @author edwin.njeru
 */
@Service("depreciationExecutor")
@Transactional
public class DepreciationExecutorImpl implements DepreciationExecutor{

    private static final Logger log = LoggerFactory.getLogger(DepreciationExecutorImpl.class);

    private LocalDateToYearMonthConverter localDateToYearMonthConverter;
    private DepreciationAgentsHandler depreciationAgentsHandler;
    private Depreciation depreciation;
    private DepreciationProceeds depreciationProceeds;

    @Autowired
    public DepreciationExecutorImpl setLocalDateToYearMonthConverter(LocalDateToYearMonthConverter localDateToYearMonthConverter) {
        this.localDateToYearMonthConverter = localDateToYearMonthConverter;
        return this;
    }

    @Autowired
    public DepreciationExecutorImpl setDepreciationAgentsHandler(DepreciationAgentsHandler depreciationAgentsHandler) {
        this.depreciationAgentsHandler = depreciationAgentsHandler;
        return this;
    }

    /**
     * Returns a Depreciation object given the fixed asset, and updates the fixed asset with the new
     * net book value and the month of depreciation
     *
     * @param asset {@link FixedAsset} to be depreciated
     * @param month the month for which we are calculating depreciation
     * @return {@link DepreciationProceeds} object
     */
    //@Cacheable
    @Override
    public DepreciationProceeds getDepreciation(FixedAsset asset, YearMonth month){

        DepreciationProceeds depreciationProceeds = new DepreciationProceeds();

        if(asset.getNetBookValue() == 0.00) {

            log.trace("The netBookValue for asset : {} is nil, skipping depreciation and resorting to nil " +
                    "depreciation",asset);

           depreciation = getNilDepreciation(asset,month);

           depreciationProceeds
                   .setDepreciation(depreciation)
                   .setNetBookValue(new UnModifiedNetBookValue(asset,month).getNetBookValue())
                   .setAccruedDepreciation(new UnModifiedAccruedDepreciation(asset,month).getAccruedDepreciation());

        } else if(localDateToYearMonthConverter.convert(asset.getPurchaseDate()).isAfter(month)){

            log.trace("The depreciation period : {} is sooner that the assets purchase date {} " +
                            "resorting to nil depreciation",month,asset.getPurchaseDate());

            depreciation = getNilDepreciation(asset,month);

            depreciationProceeds
                    .setDepreciation(depreciation)
                    .setNetBookValue(new UnModifiedNetBookValue(asset,month).getNetBookValue())
                    .setAccruedDepreciation(new UnModifiedAccruedDepreciation(asset,month).getAccruedDepreciation());

        }else {

            log.trace("The asset : {} has passed the frontal Business rules filter, initiating configuration" +
                    "registry for category : {}",asset,asset.getCategory());

            //TODO agents to handle nonNilNetBookValueCriteria and DateAuthenticCriteria logic
            depreciationAgentsHandler.sendRequest(asset, month, this::setDepreciationProceeds); //for now

        }

        return depreciationProceeds;
    }

    /**
     * Creates a depreciation object whose depreciation is Zero relative to the
     * fixedAsset item given and the depreciation period
     *
     * @param asset FixedAsset with no depreciation for the period
     * @param depreciationPeriod Depreciation period for which depreciation is nil
     * @return NilDepreciation
     */
    private Depreciation getNilDepreciation(FixedAsset asset,YearMonth depreciationPeriod){

        return new NilDepreciation(asset,depreciationPeriod).getNilDepreciation();
    }

    public void setDepreciation(Depreciation depreciation) {
        this.depreciation = depreciation;
    }

    public Depreciation getDepreciation() {
        return depreciation;
    }

    public DepreciationProceeds getDepreciationProceeds() {
        return depreciationProceeds;
    }

    public DepreciationExecutorImpl setDepreciationProceeds(DepreciationProceeds depreciationProceeds) {
        this.depreciationProceeds = depreciationProceeds;
        return this;
    }
}
