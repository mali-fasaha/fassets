package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.kernel.util.queue.util.MCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.MQError;

/**
 * This class provides a common framework for how the completion and handle error methods should be seen.
 * <br> The client being a MessageQueue 'producer' needs not bother with who to 'arrange' handling errors
 * and handling completion. It only needs to provide an implementation of the {@link MessageQueue#push(QueueMessage)}
 * method.
 * <br> This example shows how the {@code FileUploadsQueue} 'producers' has implemented the {@link MessageQueue} interface
 * by implementing the {@link MessageQueue#push(QueueMessage)} method. The {@link MessageQueue#push(QueueMessage, MQError)} method
 * and the {@link MessageQueue#push(QueueMessage, MQError, MCompletion)} method become available to the {@code FileUploadQueue}
 * simply by extending this class. Should the client desire a different approach for the lifecycle methods, nothing's wrong
 * with it skipping this class and implementing the whole interface by itself.
 * <br>
 * <br> {@code @Override
 *     public void push(QueueMessage<FileUpload> queueMessage) {
 *
 *         fileUploadService.recordFileUpload(queueMessage.message());
 *     }
 *     }
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
     * @param mCompletion Completion lifecycle action
     */
    @Override
    public void push(QueueMessage<T> queueMessage, MCompletion mCompletion) {

        push(queueMessage);

        // call the lifecycle method
        mCompletion.handleCompletion();
    }

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param mqError this is a functional interface design to allow producers to have a custom way
     *                of handling errors
     */
    @Override
    public void push(QueueMessage<T> queueMessage, MQError mqError) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            mqError.handleError(e);
        }
    }

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param mqError      This is a functional interface for handling errors when they occur
     * @param mCompletion  This is a functional interface for handling task completion tasks
     */
    @Override
    public void push(QueueMessage<T> queueMessage, MQError mqError, MCompletion mCompletion) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            mqError.handleError(e);
        }

        mCompletion.handleCompletion();
    }
}
