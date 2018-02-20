package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.effects.AccruedDepreciationUpdateJobExecutor;
import io.github.fasset.fasset.kernel.batch.depreciation.effects.DepreciationUpdateProperties;
import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.queue.JobsQueueClient;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.queue.JobsQueueClientImpl;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component("accruedDepreciationColleague")
public class  AccruedDepreciationColleague extends Colleague<DepreciationUpdate> implements JobsQueueClient<AccruedDepreciationDto> {

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationColleague.class);

    private final JobsQueueClient<AccruedDepreciationDto> jobsQueueClient;




    @Autowired
    public AccruedDepreciationColleague(DepreciationUpdateDispatcher depreciationUpdateDispatcher,@Qualifier("accruedDepreciationUpdateJobExecutor") AccruedDepreciationUpdateJobExecutor accruedDepreciationUpdateJobExecutor,DepreciationUpdateProperties depreciationUpdateProperties) {
        super(depreciationUpdateDispatcher);
        this.jobsQueueClient = new JobsQueueClientImpl<>(accruedDepreciationUpdateJobExecutor,depreciationUpdateProperties.getTippingPoint());
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

        log.debug("AccruedDepreciation received and enqueued by the accruedDepreciationColleague : {}",accruedDepreciationDto);

        jobsQueueClient.addJob(accruedDepreciationDto);
    }

    @Override
    protected void send(Update<DepreciationUpdate> updateMessage) {
        super.send(updateMessage);
    }

    public void addJob(AccruedDepreciationDto accruedDepreciationDto){

        jobsQueueClient.addJob(accruedDepreciationDto);
    }
}
