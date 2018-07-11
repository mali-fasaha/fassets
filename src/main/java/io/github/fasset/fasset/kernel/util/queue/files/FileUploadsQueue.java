package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.kernel.util.queue.AbstractMessageQueue;
import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;
import io.github.fasset.fasset.model.files.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Implementation of the MessageQueue interface that allows queueing of messages related
 * to upload of files in the system.
 * <br> The implementation uses the {@link FileUploadService} in the push method to record
 * a file in the database.
 * <br> The lifecycle method is also called when the file is uploaded successfully but it is
 * only when the client calls the method {@link this#push(QueueMessage, OnCompletion)}
 * <br> The client could also call the method {@link this#push(QueueMessage, OnError, OnCompletion)} which will handle
 * both completion and an unexpected messageQueue runtime exception
 *
 * TODO: add validation methods here or create file validation service for the same in another
 * // Todo Add duplicate upload checks service, with hashing algorithms
 * interface
 */
public class FileUploadsQueue extends AbstractMessageQueue<FileUpload> {

    private final FileUploadService fileUploadService;
    private final FileValidationService<FileUpload> fileValidationService;

    @Autowired
    public FileUploadsQueue(@Qualifier("fileUploadService") FileUploadService fileUploadService) {
        super();
        this.fileUploadService = fileUploadService;
        this.fileValidationService = new FileUploadValidation(fileUploadService);
    }

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    @Override
    public void push(QueueMessage<FileUpload> queueMessage) {

        fileUploadService.recordFileUpload(fileValidationService.validate(queueMessage.message()));
    }

}
