package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.service.FixedAssetService;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Map;

@Component("depreciationJobProxy")
public class DepreciationJobProxy {

    private final static Logger log = LoggerFactory.getLogger(DepreciationJobProxy.class);

    private final JobLauncher jobLauncher;

    private final FixedAssetService fixedAssetService;

    private final Job depreciationRun;

    private final FixedAssetsJobsActivator fixedAssetsJobsActivator;

    @Autowired
    public DepreciationJobProxy(JobLauncher jobLauncher, @Qualifier("fixedAssetService") FixedAssetService fixedAssetService, @Qualifier("depreciationJob") Job depreciationRun, FixedAssetsJobsActivator fixedAssetsJobsActivator) {
        this.jobLauncher = jobLauncher;
        this.fixedAssetService = fixedAssetService;
        this.depreciationRun = depreciationRun;
        this.fixedAssetsJobsActivator = fixedAssetsJobsActivator;
    }


    public void initializeDepreciationRun() throws BatchJobExecutionException {

        fixedAssetsJobsActivator.bootstrap(jobLauncher, depreciationRun,fixedAssetService);
    }

        /**
         * Listens for messages from the queue
         * @param message
         * @throws JMSException
         *//*
        @JmsListener(destination = "depreciationJobs", containerFactory = "messageFactory")
        public void listenForMessages(FileUploadNotification message){

            String fileName = message.getFileName();
            String month = message.getMonth();

            log.debug("File : {} has been received on the server side and is about to be actioned",fileName);

            try {
                initializeDepreciationRun();
            } catch (BatchJobExecutionException e) {
                log.error("Exception encountered while uploading excel file from : {}",fileName);
            }
        }*/

}

