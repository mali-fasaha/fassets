/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.util.queue.util;

import io.github.fasset.fasset.kernel.util.queue.MQException;
import io.github.fasset.fasset.kernel.util.queue.MessageQueue;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;

/**
 * This is a functional interface allowing producers an option to handle an error as they deem fit. <br> The {@code FileSystemStorageService} stores a file into the system using the {@code
 * FileSystemStorageService#store(file)} method. The file having been stored in the file system the service then calls the
 * {@link io.github.fasset.fasset.kernel.util.queue.MessageQueue#push(QueueMessage, * OnError, OnCompletion)} method to notify the system of this event for future detached asynchronous processing.
 * <br> however an error occurs during the enqueuing process this despite the fact the
 * file has already been uploaded to the file system <br> To handle this anomaly the Service will try to call the {@code FileSystemStorageService#store(file)} method in the following fashion: (take
 * note of the second parameter of the push method) <br> <br>
 * <pre>
 *  {@code fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName()), () -> {
 *                 store(file);
 *             }, () -> {
 *                 fileUpload.setTimeUploaded(LocalDateTime.now());
 *                 log.debug("The file {} has been uploaded", fileUpload.getFileName());
 *             });
 *  }
 *  </pre>
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@FunctionalInterface
public interface OnError {

    /**
     * This is a lifecycle method called when enqueueing a message produces an error
     *
     * @param e exception to be handled by the Queuing system or the producer
     */
    void handleError(MQException e);
}
