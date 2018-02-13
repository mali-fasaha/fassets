package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Update;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("depreciationUpdateDispatcher")
public class DepreciationUpdateDispatcherImpl implements DepreciationUpdateDispatcher{

    private static final Logger log = LoggerFactory.getLogger(DepreciationUpdateDispatcherImpl.class);

    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void send(Update updateMessage, Colleague originator) {

        for (Colleague colleague : colleagues){

            // don't send to self
            if(colleague != originator){

                log.debug("Sending update to colleague : {}",colleague);

                colleague.receive(updateMessage);
            }
        }
    }

    @Override
    public void addColleague(Colleague colleague) {

        colleagues.add(colleague);
    }
}
