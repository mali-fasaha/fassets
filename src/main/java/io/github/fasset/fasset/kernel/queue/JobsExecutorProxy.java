package io.github.fasset.fasset.kernel.queue;

import java.util.List; /**
 * Implementations of this interface are used to trigger a batch job
 * with a {@link org.springframework.batch.item.support.ListItemReader}
 *
 * @param <T> Type of DTO being processed
 *
 * @author edwin.njeru
 */
public interface JobsExecutorProxy<T> {

    void executeJobs(List<T> allJobsFromQueue, WorkInProgressListener progressListener);
}
