package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;

/**
 * This class provides a common framework for how the completion and handle error methods should be seen.
 * <br> The client being a MessageQueue 'producer' needs not bother with who to 'arrange' handling errors
 * and handling completion. It only needs to provide an implementation of the {@link MessageQueue#push(QueueMessage)}
 * method.
 * <br> This example shows how the {@code FileUploadsQueue} 'producers' has implemented the {@link MessageQueue} interface
 * by implementing the {@link MessageQueue#push(QueueMessage)} method. The {@link MessageQueue#push(QueueMessage, OnError)} method
 * and the {@link MessageQueue#push(QueueMessage, OnError, OnCompletion)} method become available to the {@code FileUploadQueue}
 * simply by extending this class. Should the client desire a different approach for the lifecycle methods, nothing's wrong
 * with it skipping this class and implementing the whole interface by itself.
 * <br>
 * <br> <pre>
 *     {@code
 *     @Override
 *     public void push(QueueMessage<FileUpload> queueMessage) {
 *
 *         fileUploadService.recordFileUpload(queueMessage.message());
 *     }
 *     }
 *    </pre>
 *
 * <br> Note:
 * <br> The client above simply implements the queue method push by recording the message to the {@code FileUploadService}
 * @param <T>
 */
public abstract class AbstractMessageQueue<T> implements MessageQueue<T> {

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    @Override
    public abstract void push(QueueMessage<T> queueMessage);

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     * @param onCompletion Completion lifecycle action
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnCompletion onCompletion) {

        push(queueMessage);

        // call the lifecycle method
        onCompletion.handleCompletion();
    }

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param onError this is a functional interface design to allow producers to have a custom way
     *                of handling errors
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnError onError) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            onError.handleError(e);
        }
    }

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param onError      This is a functional interface for handling errors when they occur
     * @param onCompletion  This is a functional interface for handling task completion tasks
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnError onError, OnCompletion onCompletion) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            onError.handleError(e);
        }

        onCompletion.handleCompletion();
    }
}
