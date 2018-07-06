package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.model.files.FileUpload;
import io.github.fasset.fasset.service.FileUploadService;

/**
 * Implementation of the MessageQueue interface that allows queueing of messages related
 * to upload of files in the system.
 * <br> The implementation uses the {@link FileUploadService} in the push method to record
 * a file in the database.
 * <br> The lifecycle method is also called when the file is uploaded successfully but it is
 * only when the client calls the method {@link this#push(QueueMessage, MCompletion)}
 *
 * TODO: add validation methods here or create file validation service for the same in another
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
        mCompletion.complete();
    }
}
