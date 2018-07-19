package io.github.fasset.fasset.kernel.batch;

import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;

/**
 * This interface may be used to trigger job runs, for instance from within an implementation of a JobExecutionListener
 */
public interface JobProxy {

    /**
     * Method for triggering a job run
     *
     * @throws BatchJobExecutionException Thrown when a job fails to initiate
     */
    void initializeJobRun() throws BatchJobExecutionException;
}
