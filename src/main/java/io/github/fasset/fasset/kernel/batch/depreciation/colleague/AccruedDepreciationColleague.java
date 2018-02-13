package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationColleague")
public class AccruedDepreciationColleague extends Colleague<AccruedDepreciationDto>{

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
    public void receive(Update<AccruedDepreciationDto> updateMessage) {

        AccruedDepreciationDto accruedDepreciationDto = updateMessage.getPayload();

        log.debug("AccruedDepreciation received by the accruedDepreciationColleague : {}",accruedDepreciationDto);
    }

    @Override
    public void send(Update updateMessage) {

    }
}
