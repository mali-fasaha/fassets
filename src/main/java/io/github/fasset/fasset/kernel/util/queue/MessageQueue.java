package io.github.fasset.fasset.kernel.util.queue;

/**
 * This interface represents a way for sending messages in the application.
 * <br> Please note it is expected that the queue is expected to operate through use
 * of database persisted strings
 * <br> The class primarily uses the push method to add a typed message. For instance, for the
 * below implementation :
 * <br> {code public void push(QueueMessage<FileUpload> queueMessage) }
 * <br> A client could add a file upload message as shown:
 *
 * <br> {code QueueMessage<FileUpload> fileUploadsQueue = new FileUploadsQueue(fileUploadService);}
 * <br> {code fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName());}
 *
 * <br>
 * <br> Should the client need to carry out an action to show completion lifecycle method,
 * the client could call the {@link MCompletion#complete()} method as shown:
 * <br>
 * <br> {code fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName()),
 *     () -> log.debug("The file {} has been uploaded", fileUpload.getFileName()));}
 */
public interface MessageQueue<T> {

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    void push(QueueMessage<T> queueMessage);

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    void push(QueueMessage<T> queueMessage, MCompletion mCompletion);
}
