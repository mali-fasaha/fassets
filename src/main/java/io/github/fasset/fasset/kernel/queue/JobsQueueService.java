package io.github.fasset.fasset.kernel.queue;

import java.util.List;
import java.util.Queue;

/**
 * Provides queueing services to the JobsQueueClient
 *
 * @param <T>
 */
public interface JobsQueueService<T> {

    /**
     * The implementation is synchronized to prevent man-handling the Queue
     * when it is in unstable state
     *
     * @param job To be added to the queue
     */
    void addJob(Job<T> job);

    /**
     *
     * @return True if the queue has reached its tipping point beyond which we must action all items in the
     * queue
     */
    boolean isQueueFull();

    /**
     * Used to add all jobs from a waiting queue incase anything was added while the
     * batch process was running.
     * Items from this queue are to be added at the head of the queue as they are of higher priority than
     * those added as soon as the work-in-progress changed
     *
     * @param workInProgressQueue from which we read items added during batch process
     */
    void addAllJobs(Queue<T> workInProgressQueue);

    /**
     *
     * @return Ordered List containing all the jobs in the Queue
     */
    List<T> getAllJobsFromQueue();

    int getNumberOfItems();
}
