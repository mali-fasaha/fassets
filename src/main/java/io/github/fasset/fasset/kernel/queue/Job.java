package io.github.fasset.fasset.kernel.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Job<T> {

    private static final Logger log = LoggerFactory.getLogger(Job.class);

    private T payLoad;

    public Job(T payLoad) {
        this.payLoad = payLoad;
        log.debug("Setting payload as : {}",payLoad);
    }

    public T getPayLoad() {
        log.debug("Returning payload as : {}",payLoad);
        return payLoad;
    }

    public Job setPayLoad(T payLoad) {
        log.debug("Setting payload as : {}",payLoad);
        this.payLoad = payLoad;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job<?> job = (Job<?>) o;
        return Objects.equals(payLoad, job.payLoad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payLoad);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Job{");
        sb.append("payLoad=").append(payLoad);
        sb.append('}');
        return sb.toString();
    }
}
