package io.github.fasset.fasset.kernel.queue;

/**
 * CallBack interface to alert when the work is done and update the status in the client
 *
 * @author edwin.njeru
 */
@FunctionalInterface
public interface WorkInProgressListener {

    /**
     *
     * @param workIsInProgress False once the work has COMPLETED
     */
    void isWorkStillInProgress(boolean workIsInProgress);
}
