package io.github.fasset.fasset.kernel.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class JobsQueueImpl<T> implements JobsQueue<T> {

    private Queue<Job<T>> queue = new ConcurrentLinkedQueue<>();

    private static final Logger log = LoggerFactory.getLogger(JobsQueueImpl.class);


    /**
     * @param job Added to the end of the queue
     */
    @Override
    public void addJob(Job<T> job) {

        log.debug("Adding job : {} to the jobsQueue",job);

        queue.add(job);
    }

    /**
     * @return int number of items in the queue
     */
    @Override
    public int getNumberOfJobsEnqueued() {

        return queue.size();
    }

    /**
     * @return The entire queue of items
     */
    @Override
    public Queue<T> getQueue() {

        Queue<T> retVal = new ConcurrentLinkedQueue<>();

        queue.forEach(item -> retVal.add(item.getPayLoad()));

        log.debug("Returning the queue of : {} items",retVal.size());

        queue.clear();

        log.debug("Queue has been reset to : {} items",queue.size());

        return retVal;
    }

    /**
     * @param workInProgressQueue Will be added at the head of the underlying Queue
     *                            implementation
     */
    @Override
    public void enqueueAtTheHead(Queue<T> workInProgressQueue) {

        log.debug("Enqueueing the workInProgressQueue of : {} items at the head of the jobsQueue",workInProgressQueue.size());

        // temporary store for existing items
        Queue<Job<T>> temp = new ConcurrentLinkedQueue<>();
        temp.addAll(queue);

        // clear all items from queue
        queue.clear();

        // enqueue items from the workInProgressQueue
        workInProgressQueue.forEach(item -> queue.add(new Job<>(item)));

        // enqueue items from the temp storage
        queue.addAll(temp);
    }
}
