package io.github.fasset.fasset.kernel.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class JobsQueueImpl<T> implements JobsQueue<T> {

    private Queue<Job<T>> queue = new ConcurrentLinkedQueue<>();


    /**
     * @param job Added to the end of the queue
     */
    @Override
    public void addJob(Job<T> job) {

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

        return retVal;
    }

    /**
     * @param workInProgressQueue Will be added at the head of the underlying Queue
     *                            implementation
     */
    @Override
    public void enqueueAtTheHead(Queue<T> workInProgressQueue) {

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
