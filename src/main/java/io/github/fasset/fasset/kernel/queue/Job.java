package io.github.fasset.fasset.kernel.queue;

import java.util.Objects;

public class Job<T> {

    private T payLoad;

    public Job(T payLoad) {
        this.payLoad = payLoad;
    }

    public T getPayLoad() {
        return payLoad;
    }

    public Job setPayLoad(T payLoad) {
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
