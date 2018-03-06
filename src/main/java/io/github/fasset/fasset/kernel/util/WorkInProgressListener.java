package io.github.fasset.fasset.kernel.util;

/**
 * Callback interface for notifying when a process is done
 */
public interface WorkInProgressListener {

    /**
     *
     * @param b Set false if work is done
     */
    void isWorkStillInProgress(boolean b);
}
