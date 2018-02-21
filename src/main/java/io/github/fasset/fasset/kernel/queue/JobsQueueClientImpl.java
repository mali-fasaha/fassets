package io.github.fasset.fasset.kernel.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JobsQueueClientImpl<T> implements JobsQueueClient<T> {

    private static final Logger log = LoggerFactory.getLogger(JobsQueueClientImpl.class);

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

        log.debug("JobQueue client created with a tipping-point of : {} and jpbExecutorProxy as : {} " +
                "using the jobsQueueService : {}",tippingPoint,jobsExecutorProxy,jobsQueueService);
    }

    @Override
    public void addJob(T job) {

        log.debug("Adding a job : {} to the queue",job);

        if(workInProgress){
            //
            log.debug("Work is in progress. Adding the job : {} to the workInProgress queue : {} whose " +
                    "membership is currently at : {} items",job,workInProgressQueue,workInProgressQueue.size());
            if(job!=null) {
                workInProgressQueue.add(job);
                log.debug("Job : {} , successfully added to the workInProgress queue, which contains : " +
                        "{} items",job,workInProgressQueue.size());
            }

        } else {
            log.debug("Adding the job : {} directly to the jobsQueueService : {} which currenty contains : {} items",
                    job,jobsQueueService,jobsQueueService.getNumberOfItems());
            if(job!=null) {
                jobsQueueService.addJob(new Job<>(job));
                log.debug("Job : {}, successfully added to the jobsQueueService which now contains : {} items",job,jobsQueueService.getNumberOfItems());
            }
        }

        // start batch update if tipping point is attained
        if(jobsQueueService.isQueueFull()){

            log.debug("The jobsQueue service has filled up the queue, emptying the queue and processing queue items...");
            workInProgress = true;

            jobsExecutorProxy.executeJobs(jobsQueueService.getAllJobsFromQueue(),this::setWorkInProgress);
        }
    }

    public boolean isWorkInProgress() {
        return workInProgress;
    }

    public JobsQueueClientImpl setWorkInProgress(boolean workInProgress) {

        log.debug("Setting workInProgress as : {}",workInProgress);

        this.workInProgress = workInProgress;

        // Just to be sure before emptying the work-in-progress queue
        if(!this.workInProgress){

            log.debug("Queued up work has been completed... Emptying the work in progress queue");

            // no need for this risky operation if the queue is empty then
            if(!workInProgressQueue.isEmpty()){

                log.debug("Adding : {} from the workInProgress queue at the head of the jobsQueue",workInProgressQueue.size());
                // add these with higher priority than present items
                this.jobsQueueService.addAllJobs(workInProgressQueue);
            }
        }
        return this;
    }
}
