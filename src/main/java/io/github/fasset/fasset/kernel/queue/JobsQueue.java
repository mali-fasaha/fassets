package io.github.fasset.fasset.kernel.queue;

import java.util.List;
import java.util.Queue;

public interface JobsQueue<T> {

    /**
     *
     * @param job Added to the end of the queue
     */
    void addJob(Job<T> job);

    /**
     *
     * @return int number of items in the queue
     */
    int getNumberOfJobsEnqueued();

    /**
     *
     * @return The entire queue of items
     */
    Queue<T> getQueue();

    /**
     *
     * @param workInProgressQueue Will be added at the head of the underlying Queue
     *        implementation
     */
    void enqueueAtTheHead(Queue<T> workInProgressQueue);
}
