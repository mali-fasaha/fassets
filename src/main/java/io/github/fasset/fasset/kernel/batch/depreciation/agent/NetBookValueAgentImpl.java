package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationListener;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.util.DepreciationExecutionException;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component("netBookValueAgent")
public class NetBookValueAgentImpl extends Colleague<NetBookValue> implements NetBookValueAgent {

    private static final Logger log = LoggerFactory.getLogger(NetBookValueAgentImpl.class);

    @Autowired
    public NetBookValueAgentImpl(@Qualifier("depreciationUpdateDispatcher") DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
        super(depreciationUpdateDispatcher);
    }

    /**
     * Upon invocation the implementation will return the netBoookValue item for the relevant month
     * in which depreciation has occured
     *
     * @param asset FixedAsset item whose Net Book Value we are tracking
     * @param month YearMonth in which depreciation has occured
     * @return The relevant NetBookValue item
     */
    @Override
    public NetBookValue invoke(FixedAsset asset, YearMonth month, DepreciationListener listener) {

        log.debug("Processing NetBookValue item for the asset : {} in the period : {}",asset,month);

        NetBookValue netBookValue = createNetBookValue(asset,month);

        log.debug("Sending netBookValueItem created : {}",netBookValue);

        send(()->netBookValue);

        return netBookValue;
    }

    /**
     * Creates a NetBookValue object using the parameters given
     * @param asset the FixedAsset item whose netBookValue is to be revalued
     * @param month of the netBookValue valuation
     * @return NetBookValue as at the month
     */
    private NetBookValue createNetBookValue(FixedAsset asset, YearMonth month) {
        NetBookValue netBookValue = new NetBookValue();

        log.trace("Creating netBookValue instance relative to the asset : {} for the month : {}",asset,month);

        try {
            netBookValue
                    .setCategory(asset.getCategory())
                    .setFixedAssetId(asset.getId())
                    .setMonth(month)
                    .setSolId(asset.getSolId())
                    .setNetBookValue(asset.getNetBookValue());
        } catch (Throwable e) {
            String message = String.format("Exception encountered while creating netBookValue instance relative" +
                    "to the asset : %s for the month : %s",asset,month);
            throw new DepreciationExecutionException(message,e);
        }

        log.trace("NetBookValue item created : {}",netBookValue);

        return netBookValue;
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    @Override
    public void receive(UpdateProvider updateMessage) {
        //crickets
    }
}
