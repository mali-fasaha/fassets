package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;

/**
 * This interface represents a way for sending messages in the application.
 * <br> Please note it is expected that the queue is expected to operate through use
 * of database persisted strings
 * <br> The class primarily uses the push method to add a typed message. For instance, for the
 * below implementation :
 * <br> {@code public void push(QueueMessage<FileUpload> queueMessage) }
 * <br> A client could add a file upload message as shown:
 *
 * <br> <pre>
 *     {@code
 *     QueueMessage<FileUpload> fileUploadsQueue = new FileUploadsQueue(fileUploadService);
 *
 *     fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName());
 *     }
 *     </pre>
 *
 * <br>
 * <br> Should the client need to carry out an action to show completion lifecycle method,
 * the client could call the {@link OnCompletion#handleCompletion()} method as shown:
 * <br>
 * <br> <pre>
 *     {@code
 *     fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName()),
 *      () -> log.debug("The file {} has been uploaded", fileUpload.getFileName()));
 *     }
 *     </pre>
 *
 *     TODO : implement consumers for this Queue
 */
public interface MessageQueue<T> {

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    void push(QueueMessage<T> queueMessage);

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     */
    void push(QueueMessage<T> queueMessage, OnError onError);

    /**
     * Adds a message to the queue and provides a method to allow the producer to handle 'task completion acts'
     *
     * @param queueMessage Item to be added to the queue
     * @param onCompletion This is a functional interface for handling task completion tasks
     */
    void push(QueueMessage<T> queueMessage, OnCompletion onCompletion);

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param onError This is a functional interface for handling errors when they occur
     * @param onCompletion This is a functional interface for handling task completion tasks
     */
    void push(QueueMessage<T> queueMessage, OnError onError, OnCompletion onCompletion);
}
