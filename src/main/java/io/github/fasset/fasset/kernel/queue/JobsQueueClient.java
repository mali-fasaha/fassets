package io.github.fasset.fasset.kernel.queue;

public interface JobsQueueClient<T> {

    void addJob(T job);
}
