package io.github.fasset.fasset.kernel.util.queue;

/**
 * Message item being added to the queue
 */
public interface QueueMessage<T> {

    T message();
}
