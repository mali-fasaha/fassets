package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;
import io.github.fasset.fasset.model.NetBookValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("netBookValueUpdatePoller")
public class NetBookValueUpdatePoller implements UpdatePoller<NetBookValueDto> {

    private static final Logger log = LoggerFactory.getLogger(NetBookValueUpdatePoller.class);

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    @Override
    @JmsListener(destination = "netBookValueQueue")
    public void pollUpdates(Message<NetBookValueDto> updateMessage) {

        log.debug("Update received for netBookValue item : {}",updateMessage);

    }
}
