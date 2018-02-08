package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.model.AccruedDepreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationUpdatePoller")
public class AccruedDepreciationUpdatePoller implements UpdatePoller<AccruedDepreciationDto> {

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationUpdatePoller.class);

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    @Override
    @JmsListener(destination = "accruedDepreciationQueue")
    public void pollUpdates(Message<AccruedDepreciationDto> updateMessage) {

        log.debug("Update received for accruedDepreciation item : {}",updateMessage);
    }
}
