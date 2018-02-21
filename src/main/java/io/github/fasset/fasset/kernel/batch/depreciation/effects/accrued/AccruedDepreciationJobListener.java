package io.github.fasset.fasset.kernel.batch.depreciation.effects.accrued;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component("accruedDepreciationJobListener")
public class AccruedDepreciationJobListener implements JobExecutionListener{

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationJobListener.class);

    /**
     * Callback before a job executes.
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {

        log.info("Job : {} has been initiated...",jobExecution.getJobParameters());
    }

    /**
     * Callback after completion of a job. Called after both both successful and
     * failed executions. To perform logic on a particular status, use
     * "if (jobExecution.getStatus() == BatchStatus.X)".
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void afterJob(JobExecution jobExecution) {

        log.info("Job : {} has been executed...",jobExecution.getJobParameters());
    }
}
