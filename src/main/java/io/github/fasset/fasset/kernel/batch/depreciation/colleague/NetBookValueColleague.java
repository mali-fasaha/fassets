package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("netBookValueColleague")
public class NetBookValueColleague extends Colleague<DepreciationUpdate> {

    @Autowired
    private DepreciationUpdateDispatcher updateDispatcher;

    private static final Logger log = LoggerFactory.getLogger(NetBookValueColleague.class);

    public NetBookValueColleague(DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
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

        NetBookValueDto netBookValueDto = null;

        try {
            if (updateMessage.getDestination() instanceof NetBookValueColleague)
                netBookValueDto = updateMessage.getPayload().getNetBookValueItem();
        } catch (Throwable e) {
            String errorMessage = String.format("Exception encountered while deriving netBookValueDto at destianation : %s",this);
            throw new DepreciationUpdatesException(errorMessage,e);
        }

        log.debug("NetBookValueDto received by the NetBookValueColleague : {}", netBookValueDto);
    }

    @Override
    public void send(Update updateMessage) {

        super.send(updateMessage);
    }
}
