package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.LocalDateToYearMonthConverter;
import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.model.NilDepreciation;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
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
public class DepreciationExecutorImpl extends Colleague implements DepreciationExecutor{

    private static final Logger log = LoggerFactory.getLogger(DepreciationExecutorImpl.class);

    private LocalDateToYearMonthConverter localDateToYearMonthConverter;
    private DepreciationAgentsHandler depreciationAgentsHandler;
    private Depreciation depreciation;

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

    @Autowired
    public DepreciationExecutorImpl(@Qualifier("depreciationUpdateDispatcher") DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
        super(depreciationUpdateDispatcher);
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage Update message to be received from the DepreciationUpdateDispatcher
     */
    @Override
    public void receive(UpdateProvider updateMessage) {
        // Crickets
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
    @Override
    public Depreciation getDepreciation(FixedAsset asset, YearMonth month){

        if(asset.getNetBookValue() == 0.00) {

            log.trace("The netBookValue for asset : {} is nil, skipping depreciation and resorting to nil " +
                    "depreciation",asset);

           depreciation = getNilDepreciation(asset,month);

        } else if(localDateToYearMonthConverter.convert(asset.getPurchaseDate()).isAfter(month)){

            log.trace("The depreciation period : {} is sooner that the assets purchase date {} " +
                            "resorting to nil depreciation",month,asset.getPurchaseDate());

            depreciation = getNilDepreciation(asset,month);

        }else {

            log.trace("The asset : {} has passed the frontal Business rules filter, initiating configuration" +
                    "registry for category : {}",asset,asset.getCategory());

            //TODO: implement NilAccruedDepreciation, and UnmodifiedNetBookValue
            //TODO agents to handle nonNilNetBookValueCriteria and DateAuthenticCriteria logic
            depreciationAgentsHandler.sendRequest(asset, month, this::setDepreciation); //for now

        }

        return depreciation;
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
}
