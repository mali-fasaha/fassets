package io.github.fasset.fasset.kernel.queue;

import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;

public class JobsQueueServiceImpl<T> implements JobsQueueService<T> {

    private static final Logger log = LoggerFactory.getLogger(JobsQueueServiceImpl.class);

    private JobsQueue<T> jobsQueue;
    private boolean queueFull=false;

    private int numberOfItems;

    // Point at which the queue is to be emptied
    private int tippingPoint;

    JobsQueueServiceImpl(int tippingPoint) {
        this.jobsQueue = new JobsQueueImpl<>();
        this.tippingPoint = tippingPoint;
        numberOfItems=0;
    }

    public synchronized void addJob(Job<T> jobItem){

        log.debug("Adding job : {} to the jobsQueue",jobItem );

            jobsQueue.addJob(jobItem);

            numberOfItems++;

            if(jobsQueue.getNumberOfJobsEnqueued()==tippingPoint){
                queueFull=true;
            }
    }

    /**
     * @return True if the queue has reached its tipping point beyond which we must action all items in the
     * queue
     */
    @Override
    public boolean isQueueFull() {
        return queueFull;
    }

    /**
     * Used to add all jobs from a waiting queue incase anything was added while the
     * batch process was running.
     * Items from this queue are to be added at the head of the queue as they are of higher priority than
     * those added as soon as the work-in-progress changed
     *
     * @param workInProgressQueue from which we read items added during batch process
     */
    @Override
    public void addAllJobs(Queue<T> workInProgressQueue) {

        log.debug("Adding : {} jobs at the head of the queue",workInProgressQueue.size());

        jobsQueue.enqueueAtTheHead(workInProgressQueue);

        numberOfItems += workInProgressQueue.size();

    }

    /**
     *
     * @return List of all items in the Queue. After this the queue is to be emptied
     */
    @Override
    public List<T> getAllJobsFromQueue() {

        numberOfItems=0;

        return new FastList<>(jobsQueue.getQueue())
                .parallelStream()
                .collect(ImmutableListCollector.toImmutableList());
    }

    @Override
    public int getNumberOfItems() {

        return numberOfItems;
    }
}
