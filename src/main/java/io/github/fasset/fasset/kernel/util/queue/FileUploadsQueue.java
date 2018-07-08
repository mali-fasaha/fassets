package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.model.files.FileUpload;

/**
 * Implementation of the MessageQueue interface that allows queueing of messages related
 * to upload of files in the system.
 * <br> The implementation uses the {@link FileUploadService} in the push method to record
 * a file in the database.
 * <br> The lifecycle method is also called when the file is uploaded successfully but it is
 * only when the client calls the method {@link this#push(QueueMessage, MCompletion)}
 *
 * TODO: add validation methods here or create file validation service for the same in another
 * // Todo Add duplicate upload checks service
 * interface
 */
public class FileUploadsQueue implements MessageQueue<FileUpload> {

    private FileUploadService fileUploadService;

    public FileUploadsQueue(FileUploadService fileUploadService) {

        this.fileUploadService = fileUploadService;
    }

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    @Override
    public void push(QueueMessage<FileUpload> queueMessage) {

        fileUploadService.recordFileUpload(queueMessage.message());
    }

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     * @param mCompletion Completion lifecycle action
     */
    @Override
    public void push(QueueMessage<FileUpload> queueMessage, MCompletion mCompletion) {

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
    public void push(QueueMessage<FileUpload> queueMessage, MQError mqError) {

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
    public void push(QueueMessage<FileUpload> queueMessage, MQError mqError, MCompletion mCompletion) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            mqError.handleError(e);
        }

        mCompletion.handleCompletion();
    }
}
