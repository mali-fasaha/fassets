package io.github.fasset.fasset.kernel.queue;

import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.MultiReaderFastList;

import java.util.List;
import java.util.Queue;

public class JobsQueueServiceImpl<T> implements JobsQueueService<T> {

    private JobsQueue<T> jobsQueue;
    private boolean queueFull=false;

    // Point at which the queue is to be emptied
    private int tippingPoint;

    JobsQueueServiceImpl(int tippingPoint) {
        this.jobsQueue = new JobsQueueImpl<>();
        this.tippingPoint = tippingPoint;
    }

    public synchronized void addJob(Job<T> jobItem){
            jobsQueue.addJob(jobItem);

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

        jobsQueue.enqueueAtTheHead(workInProgressQueue);

    }

    @Override
    public List<T> getAllJobsFromQueue() {

        return new FastList<>(jobsQueue.getQueue())
                .parallelStream()
                .collect(ImmutableListCollector.toImmutableList());
    }
}
