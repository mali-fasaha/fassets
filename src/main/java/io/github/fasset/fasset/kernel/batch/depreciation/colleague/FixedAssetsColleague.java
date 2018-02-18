package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fixedAssetsColleague")
public class FixedAssetsColleague extends Colleague<DepreciationUpdate> {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetsColleague.class);

    @Autowired
    public FixedAssetsColleague(DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
        super(depreciationUpdateDispatcher);
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage sent by the depreciationUpdateDispatcher
     */
    @Override
    public void receive(Update<DepreciationUpdate> updateMessage) {

        FixedAssetDto fixedAssetDto = null;

        try {
            if(updateMessage.getDestination() instanceof FixedAssetsColleague) {
                fixedAssetDto = updateMessage.getPayload().getFixedAssetItem();
            }
        } catch (Throwable e) {
           String errorMessage = String.format("Exception encountered while creating fixedAssetDto at destination : %s",this);
           throw new DepreciationUpdatesException(errorMessage,e);
        }

        log.debug("FixedAssetDto received by the fixedAssetsColleague : {}",fixedAssetDto);
    }

    @Override
    protected void send(Update<DepreciationUpdate> updateMessage) {
        super.send(updateMessage);
    }
}
