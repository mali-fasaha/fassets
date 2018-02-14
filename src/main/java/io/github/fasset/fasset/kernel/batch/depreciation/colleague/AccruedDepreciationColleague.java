package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationColleague")
public class AccruedDepreciationColleague extends Colleague<DepreciationUpdate>{

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationColleague.class);

    @Autowired
    private DepreciationUpdateDispatcher updateDispatcher;

    public AccruedDepreciationColleague(DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
        super(depreciationUpdateDispatcher);
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    @Override
    public void receive(Update<DepreciationUpdate> updateMessage) {

        AccruedDepreciationDto accruedDepreciationDto = null;

        DepreciationUpdate update = updateMessage.getPayload();

        log.debug("DepreciationUpdate received : {}",update);

        try {
            if(updateMessage.getDestination() instanceof AccruedDepreciationColleague){
                accruedDepreciationDto = new AccruedDepreciationDto(update.getPeriod().getMonthValue(),
                        update.getPeriod().getYear(), update.getSolId(), update.getCategory(), update.getFixedAssetId(), update.getAccruedDepreciation());
            }
        } catch (Throwable e) {
            String errorMessage = String.format("Exception encountered while receiving depreciation update : %s " +
                    "from the %s",updateMessage,this);
            throw new DepreciationUpdatesException(errorMessage,e);
        }

        log.debug("AccruedDepreciation received by the accruedDepreciationColleague : {}",accruedDepreciationDto);
    }

    @Override
    public void send(Update updateMessage) {

    }
}
