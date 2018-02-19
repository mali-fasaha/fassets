package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.report.MonthlyDepreciationJobProxy;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("depreciationJobListener")
public class DepreciationJobListener implements JobExecutionListener {


    @Autowired
    private MonthlyDepreciationJobProxy monthlyDepreciationJobProxy;

    /**
     * Callback before a job executes.
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {

        //TODO poll job parameters and update the depreciationJobExecutionRepository
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

        //TODO poll paramters and update the status of the depreciationJobExecutionRepository

        monthlyDepreciationJobProxy.initializeMonthlyDepreciationReporting();
    }

    public DepreciationJobListener setMonthlyDepreciationJobProxy(MonthlyDepreciationJobProxy monthlyDepreciationJobProxy) {
        this.monthlyDepreciationJobProxy = monthlyDepreciationJobProxy;
        return this;
    }
}
