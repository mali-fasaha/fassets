package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.messaging.model.FileUploadNotification;

/**
 * Notifies that a file has been uploaded, giving both the month and the file path
 */
public interface UploadNotificationService {

    /**
     * Sends the message
     * @param notification
     */
    void sendNotification(FileUploadNotification notification);

}
