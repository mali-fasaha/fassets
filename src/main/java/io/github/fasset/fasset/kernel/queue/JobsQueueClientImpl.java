package io.github.fasset.fasset.kernel.queue;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JobsQueueClientImpl<T> implements JobsQueueClient<T> {

    private JobsQueueService<T> jobsQueueService;
    private JobsExecutorProxy<T> jobsExecutorProxy;

    private boolean workInProgress = false;
    private Queue<T> workInProgressQueue = new ConcurrentLinkedQueue<>();



    public JobsQueueClientImpl(JobsExecutorProxy<T> jobsExecutorProxy,Integer tippingPoint) {

        if(tippingPoint==null){
            tippingPoint=100;
        }
        this.jobsExecutorProxy = jobsExecutorProxy;
        this.jobsQueueService = new JobsQueueServiceImpl<>(tippingPoint);
    }

    @Override
    public void addJob(T job) {

        if(workInProgress){
            //
            workInProgressQueue.add(job);

        } else {
            jobsQueueService.addJob(new Job<>(job));
        }

        // start batch update if tipping point is attained
        if(jobsQueueService.isQueueFull()){

            workInProgress = true;

            jobsExecutorProxy.executeJobs(jobsQueueService.getAllJobsFromQueue(),this::setWorkInProgress);
        }
    }

    public boolean isWorkInProgress() {
        return workInProgress;
    }

    public JobsQueueClientImpl setWorkInProgress(boolean workInProgress) {

        this.workInProgress = workInProgress;

        // Just to be sure before emptying the work-in-progress queue
        if(!this.workInProgress){

            // no need for this risky operation is the queue is empty then
            if(!workInProgressQueue.isEmpty()){

                // add these with higher priority than present items
                this.jobsQueueService.addAllJobs(workInProgressQueue);
            }
        }
        return this;
    }
}
