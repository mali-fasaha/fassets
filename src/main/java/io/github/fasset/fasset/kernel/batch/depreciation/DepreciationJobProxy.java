package io.github.fasset.fasset.kernel.batch.depreciation;

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

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("fixedAssetService")
    private FixedAssetService fixedAssetService;

    @Autowired
    @Qualifier("depreciationJob")
    private Job depreciationRun;


    public void initializeDepreciationRun() throws BatchJobExecutionException {

        Map<String, Object> parameterMap = new HashedMap<>();
        parameterMap.put("no_of_assets", fixedAssetService.getPoll());
        parameterMap.put("starting_time", LocalDateTime.now());
        parameterMap.put("status", "Work in progress");

        int no_of_assets = fixedAssetService.getPoll();
        LocalDateTime starting_time = LocalDateTime.now();

        log.info("Depreciation has begun with {} items at time: {}", no_of_assets, starting_time);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("no_of_assets", String.valueOf(no_of_assets))
                .addString("starting_time", LocalDateTime.now().toString())
                .toJobParameters();

        try {
            jobLauncher.run(depreciationRun, jobParameters);
        } catch (Throwable e) {

            String message = String.format("Exception encountered %s caused by %s,while launching job" +
                            "id %s at time %s",
                    e.getMessage(), e.getCause(), depreciationRun.getName(), jobParameters.getString("starting_time"));

            throw new BatchJobExecutionException(message, e);

        }
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

