package io.github.fasset.fasset.kernel.batch;

import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.kernel.util.WorkInProgressListener;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("fixedAssetsJobsActivator")
public class FixedAssetsJobsActivator {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetsJobsActivator.class);

    /**
     * Will start any job that draws poll data fom the fixedAssetService
     * @throws BatchJobExecutionException
     */
    public void bootstrap(JobLauncher jobLauncher, Job job, FixedAssetService fixedAssetService, WorkInProgressListener progressListener) throws BatchJobExecutionException {

            bootstrap(jobLauncher, job, fixedAssetService);
    }



    /**
     * Will start any job that draws poll data fom the fixedAssetService
     * @param jobLauncher
     * @param job
     * @param fixedAssetService
     * @throws BatchJobExecutionException
     */
    public void bootstrap(JobLauncher jobLauncher, Job job, FixedAssetService fixedAssetService) throws BatchJobExecutionException {

        int no_of_assets = fixedAssetService.getPoll();

        bootstrap(new JobParametersBuilder()
                .addString("no_of_assets", String.valueOf(no_of_assets))
                .addString("starting_time", LocalDateTime.now().toString())
                .toJobParameters(), jobLauncher, job, fixedAssetService,null);
    }

    /**
     * Will start any job that draws poll data fom the fixedAssetService
     * @param jobLauncher
     * @param job
     * @param fixedAssetService
     * @throws BatchJobExecutionException
     */
    public void bootstrap(JobParameters jobParameters,JobLauncher jobLauncher, Job job, FixedAssetService fixedAssetService,WorkInProgressListener progressListener) throws BatchJobExecutionException {

        int no_of_assets = fixedAssetService.getPoll();
        LocalDateTime starting_time = LocalDateTime.now();

        JobExecution jobExecution;

        log.info("Depreciation has begun with {} items at time: {}", no_of_assets, starting_time);

        try {
            jobExecution = jobLauncher.run(job, jobParameters);
        } catch (Throwable e) {

            String message = String.format("Exception encountered %s caused by %s,while launching job" +
                            "id %s at time %s",
                    e.getMessage(), e.getCause(), job.getName(), jobParameters.getString("starting_time"));

            throw new BatchJobExecutionException(message, e);

        }

        if(progressListener!= null) {
            log.debug("Checking is job execution is running...");
            if (!jobExecution.getStatus().isRunning()) {
                progressListener.isWorkStillInProgress(false);
                log.debug("Job execution has stopped, updating listener...");
            }
        }

    }

    public void bootstrap(JobParameters jobParameters, JobLauncher jobLauncher, Job job, FixedAssetService fixedAssetService) {
        try {
            bootstrap(jobParameters,jobLauncher,job,fixedAssetService,null);
        } catch (BatchJobExecutionException e) {
            e.printStackTrace();
        }
    }
}
