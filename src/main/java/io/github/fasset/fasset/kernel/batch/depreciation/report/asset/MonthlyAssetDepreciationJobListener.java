package io.github.fasset.fasset.kernel.batch.depreciation.report.asset;

import io.github.fasset.fasset.kernel.batch.depreciation.report.MonthlyDepreciationJobProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MonthlyAssetDepreciationJobListener implements JobExecutionListener{

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationJobListener.class);


    /*@Autowired
    private MonthlyDepreciationJobProxy monthlyDepreciationJobProxy;*/

    /**
     * Callback before a job executes.
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {

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

        /*log.info("MonthlyAssetDepreciation job has been executed with the following status : {} executing" +
                "monthlySolDepreciation job",jobExecution.getJobParameters());*/

    }
}
