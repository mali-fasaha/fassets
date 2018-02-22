package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.effects.DepreciationUpdateProperties;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import io.github.fasset.fasset.model.AccruedDepreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationColleague")
public class  AccruedDepreciationColleague extends Colleague{

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationColleague.class);


    @Autowired
    public AccruedDepreciationColleague(DepreciationUpdateDispatcher depreciationUpdateDispatcher,DepreciationUpdateProperties depreciationUpdateProperties) {
        super(depreciationUpdateDispatcher);
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage received for further processing
     */
    @Override
    public void receive(UpdateProvider updateMessage) {

        log.debug("Receiving update message {} from the mediator",updateMessage);

        AccruedDepreciation accruedDepreciation = null;

        try {
            if(updateMessage instanceof AccruedDepreciationColleague){
                log.debug("DepreciationUpdate is an instance of AccruedDepreciationColleague creating accruedDepreciationDto");
                /*accruedDepreciationDto = new AccruedDepreciationDto(update.getPeriod().getMonthValue(),
                        update.getPeriod().getYear(), update.getSolId(), update.getCategory(), update.getFixedAssetId(), update.getAccruedDepreciation());*/
                try {
                    accruedDepreciation = (AccruedDepreciation) updateMessage.get();
                } catch (ClassCastException e) {

                    // If exception then this is not the intended recipient
                    log.warn("This is not the expected colleague hence the exception...", e);
                }
                log.debug("AccruedDepreciationDto : {} has been created...",accruedDepreciation);
            }
        } catch (Throwable e) {
            String errorMessage = String.format("Exception encountered while receiving depreciation update : %s " +
                    "from the %s",updateMessage,this);
            throw new DepreciationUpdatesException(errorMessage,e);
        }


    }
}
