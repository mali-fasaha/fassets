package io.github.fasset.fasset.kernel.batch.depreciation.effects;

import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.queue.JobsExecutorProxy;
import io.github.fasset.fasset.kernel.queue.WorkInProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("accruedDepreciationUpdateJobExecutor")
public class AccruedDepreciationUpdateJobExecutor implements JobsExecutorProxy<AccruedDepreciationDto> {

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationUpdateJobExecutor.class);

    private final AccruedDepreciationUpdateJobProxy depreciationUpdateJobProxy;

    @Autowired
    public AccruedDepreciationUpdateJobExecutor(AccruedDepreciationUpdateJobProxy depreciationUpdateJobProxy) {
        this.depreciationUpdateJobProxy = depreciationUpdateJobProxy;
    }

    @Override
    public void executeJobs(List<AccruedDepreciationDto> allJobsFromQueue, WorkInProgressListener progressListener) {

        log.debug("Executing an update for  : {} from the jobsQueue, notifications will be coming from the workInProgress callBack interface : {}",allJobsFromQueue.size(),progressListener );

        depreciationUpdateJobProxy.initializeDepreciationUpdates(allJobsFromQueue,allJobsFromQueue.size(),progressListener);
    }


}
