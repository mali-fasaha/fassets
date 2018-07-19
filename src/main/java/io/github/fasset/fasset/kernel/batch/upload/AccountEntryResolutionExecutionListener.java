package io.github.fasset.fasset.kernel.batch.upload;

import org.slf4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Callback interface for execution of accountEntryResolutionJob
 */
@Component("accountEntryResolutionExecutionListener")
public class AccountEntryResolutionExecutionListener implements JobExecutionListener {

    private static final Logger log = getLogger(AccountEntryResolutionExecutionListener.class);

    @Override
    public void beforeJob(final JobExecution jobExecution) {

        log.debug("Initializing accountEntryResolutionJob, with parameters: {}", jobExecution.getJobParameters());

    }

    @Override
    public void afterJob(final JobExecution jobExecution) {

        log.debug("AccountEntryResolution has ended with status {}", jobExecution.getStatus());

    }
}
