package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.kernel.util.queue.files.FileUploadService;

/**
 * Message item being added to the queue. The architecture of this queueing framework assumes the message enqueued
 * is dumb as to whether or not it has been processed. The client has to form its own way of 'knowing' whether or
 * not a message has been consumed. SO we take the dumb-framework-clever-consumers approach.
 * <br> The specifics of what's in the message are expected to be set up by 'producers' through functional implementation
 * such as shown below:
 * <br> <pre>{@code fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName())}</pre>
 * <br>
 * <br> In the above example the {@code FIleUploadQueue} is an implementation of the {@code MessageQueue<FileUpload>}
 * interface that is supposed to enqueue messages about an uploaded file. The message here is therefore of the type
 * {@code FileUploadNotification}, which has details of a file that the system needs to know about.
 * <br> Using the lambda will allow the producer to create and simultaneously enqueue an object of any type.
 * <br> The 'consumer' on the other hand has only to call the {@link QueueMessage#message()} method to obtain the message
 * sent by the producer.
 * <br> The consumer for the {@link FileUploadService} consumption of the message might look as follows:
 * <br> <pre>{@code fileUploadService.recordFileUpload(queueMessage.message());}</pre>
 *
 */
@FunctionalInterface
public interface QueueMessage<T> {

    /**
     * This is the item being enqueued
     * @return The message from the queue
     */
    T message();
}
