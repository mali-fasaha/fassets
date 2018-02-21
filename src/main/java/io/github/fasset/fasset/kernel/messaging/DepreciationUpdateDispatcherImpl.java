package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("depreciationUpdateDispatcher")
public class DepreciationUpdateDispatcherImpl implements DepreciationUpdateDispatcher{

    private static final Logger log = LoggerFactory.getLogger(DepreciationUpdateDispatcherImpl.class);

    private List<Colleague<DepreciationUpdate>> colleagues = new ArrayList<>();

    @Override
    public void send(UpdateProvider updateMessage, Colleague originator) {

        for (Colleague<DepreciationUpdate> colleague : colleagues){

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

    @Override
    public List<Colleague<DepreciationUpdate>> getColleagues() {
        return colleagues;
    }
}
