package io.github.fasset.fasset.kernel.notifications;

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
