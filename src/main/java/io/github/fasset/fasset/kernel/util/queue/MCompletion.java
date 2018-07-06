package io.github.fasset.fasset.kernel.util.queue;

/**
 * Interface allows clients to carry out an action after a message is completed when
 * the complete method is called
 */
public interface MCompletion {

    /**
     * This is simply a lifecycle method called when the message has been queued successfully
     */
    void complete();
}
