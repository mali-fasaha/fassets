package io.github.fasset.fasset.kernel.batch.depreciation.report.sol;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component("monthlySolDepreciationJobListener")
public class MonthlySolDepreciationJobListener implements JobExecutionListener {

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

    }
}
