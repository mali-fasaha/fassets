package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("fixedAssetsUpdatePoller")
public class FixedAssetsUpdatePoller implements UpdatePoller<FixedAssetDto> {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetsUpdatePoller.class);

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    @Override
    public void pollUpdates(Message<FixedAssetDto> updateMessage) {

        log.debug("Update received for fixedAsset item : {}",updateMessage);
    }
}
