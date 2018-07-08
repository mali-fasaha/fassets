package io.github.fasset.fasset.kernel.util.queue.util;

import io.github.fasset.fasset.kernel.util.queue.MQException;
import io.github.fasset.fasset.kernel.util.queue.MessageQueue;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;

/**
 * This is a functional interface allowing producers an option to handle an error as they deem fit.
 * <br> The {@code FileSystemStorageService} stores a file into the system using the
 * {@code FileSystemStorageService#store(file)} method. The file having been stored in the file system the
 * service then calls the {@link MessageQueue#push(QueueMessage, MQError, MCompletion)} method to notify
 * the system of this event for future detached asynchronous processing.
 * <br> however an error occurs during the enqueuing process this despite the fact the file has already been
 * uploaded to the file system
 * <br> To handle this anomaly the Service will try to call the {@code FileSystemStorageService#store(file)}
 * method in the following fashion: (take note of the second parameter of the push method)
 * <br>
 * <br>
 *  <pre>
 *  {@code fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName()), () -> {
 *                 store(file);
 *             }, () -> {
 *                 fileUpload.setTimeUploaded(LocalDateTime.now());
 *                 log.debug("The file {} has been uploaded", fileUpload.getFileName());
 *             });
 *  }
 *  </pre>
 */
@FunctionalInterface
public interface MQError {

    /**
     * This is a lifecycle method called when enqueueing a message produces an error
     *
     * @param e exception to be handled by the Queuing system or the producer
     */
    void handleError(MQException e);
}
