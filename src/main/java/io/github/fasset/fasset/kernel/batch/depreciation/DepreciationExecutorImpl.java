package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.LocalDateToYearMonthConverter;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Update;
import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.batch.depreciation.model.NilDepreciation;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;
import io.github.fasset.fasset.kernel.util.DepreciationExecutionException;
import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.CategoryConfiguration;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;
import io.github.fasset.fasset.service.AccruedDepreciationService;
import io.github.fasset.fasset.service.CategoryConfigurationService;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.Map;

/**
 * This class represents the main method which is to be abstracted by other layers that would
 * allow for flexibility in application of business rules, the main method is the
 * {@link DepreciationExecutorImpl#getDepreciation(FixedAsset, YearMonth)} which able to extract a
 * {@link Depreciation} as long as you have a {@link FixedAsset} and the {@YearMonth} for which the
 * depreciation is to be calculated
 *
 * @author edwin.njeru
 */
@Service("depreciationExecutor")
@Transactional
public class DepreciationExecutorImpl extends Colleague implements DepreciationExecutor {

    private static final Logger log = LoggerFactory.getLogger(DepreciationExecutorImpl.class);

    private final LocalDateToYearMonthConverter localDateToYearMonthConverter;

    private Map<String,CategoryConfiguration> configurationRegistry = new UnifiedMap<>();

    private final DepreciationAgent depreciationAgent;

    @Autowired
    public DepreciationExecutorImpl(@Qualifier("depreciationUpdateDispatcher") DepreciationUpdateDispatcher depreciationUpdateDispatcher,LocalDateToYearMonthConverter localDateToYearMonthConverter, DepreciationAgent depreciationAgent) {
        super(depreciationUpdateDispatcher);
        this.localDateToYearMonthConverter = localDateToYearMonthConverter;
        this.depreciationAgent = depreciationAgent;
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    @Override
    public void receive(Update updateMessage) {
        // To maintain listing of successfully processed items
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

        Depreciation depreciation;

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

            depreciationAgent.invoke(asset, month);

            /*NetBookValue netBookValue = depreciationAgent.getNetBookValue();
            AccruedDepreciation accruedDepreciation = depreciationAgent.getAccruedDepreciation();
            depreciation = depreciationAgent.getDepreciation();*/

            // send updates to depreciationUpdateDispatcher
            /*send(new DepreciationUpdate.from(new NetBookValueDto(netBookValue)).getPayload().setDestination(netBookValue.getClass()).setSentBy(this));
            send(new DepreciationUpdate.from(new AccruedDepreciationDto(accruedDepreciation)).getPayload().setDestination(accruedDepreciation.getClass()).setSentBy(this));*/
        }

        return depreciation;
    }

    /**
     * Creates a depreciation object whose depreciation is Zero relative to the
     * fixedAsset item given and the depreciation period
     *
     * @param asset
     * @param depreciationPeriod
     * @return
     */
    private Depreciation getNilDepreciation(FixedAsset asset,YearMonth depreciationPeriod){

        return new NilDepreciation(asset,depreciationPeriod).getNilDepreciation();
    }

}
