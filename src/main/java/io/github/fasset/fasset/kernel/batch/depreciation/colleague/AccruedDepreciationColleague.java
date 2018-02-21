package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.effects.AccruedDepreciationUpdateJobExecutor;
import io.github.fasset.fasset.kernel.batch.depreciation.effects.DepreciationUpdateProperties;
import io.github.fasset.fasset.kernel.queue.JobsQueueClient;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.queue.JobsQueueClientImpl;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import io.github.fasset.fasset.model.AccruedDepreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationColleague")
public class  AccruedDepreciationColleague extends Colleague<AccruedDepreciation> implements JobsQueueClient<AccruedDepreciation> {

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
    public void receive(UpdateProvider updateMessage) {

        log.debug("Receiving update message {} from the mediator",updateMessage);

        AccruedDepreciation accruedDepreciation = null;

        try {
            if(updateMessage instanceof AccruedDepreciationColleague){
                log.debug("DepreciationUpdate is an instance of AccruedDepreciationColleague creating accruedDepreciationDto");
                /*accruedDepreciationDto = new AccruedDepreciationDto(update.getPeriod().getMonthValue(),
                        update.getPeriod().getYear(), update.getSolId(), update.getCategory(), update.getFixedAssetId(), update.getAccruedDepreciation());*/
                accruedDepreciation = (AccruedDepreciation) updateMessage.get();
                log.debug("AccruedDepreciationDto : {} has been created...",accruedDepreciation);
            }
        } catch (Throwable e) {
            String errorMessage = String.format("Exception encountered while receiving depreciation update : %s " +
                    "from the %s",updateMessage,this);
            throw new DepreciationUpdatesException(errorMessage,e);
        }

        log.debug("AccruedDepreciation : {} received and enqueued by the jobsQueueClient : {}",accruedDepreciation,jobsQueueClient);

        jobsQueueClient.addJob(AccruedDepreciationDto.from(accruedDepreciation));
    }

    public void addJob(AccruedDepreciation accruedDepreciation){

        log.debug("Adding the Dto : {} to the queue...",accruedDepreciation);
        jobsQueueClient.addJob(AccruedDepreciationDto.from(accruedDepreciation));
    }
}
