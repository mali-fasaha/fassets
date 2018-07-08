package io.github.fasset.fasset.kernel.util.queue;

@FunctionalInterface
public interface MQError {

    /**
     * This is a lifecycle method called when enqueueing a message produces an error
     */
    void handleError();
}
