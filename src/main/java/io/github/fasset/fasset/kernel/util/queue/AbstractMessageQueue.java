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
package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;

/**
 * This class provides a common framework for how the completion and handle error methods should be seen.
 * <br>
 * The client being a MessageQueue 'producer' needs not bother with who to 'arrange' handling errors and handling completion. It only needs to provide an implementation of the {@link
 * io.github.fasset.fasset.kernel.util.queue.MessageQueue#push(QueueMessage)} method. <br> This example shows how the {@code FileUploadsQueue} 'producers' has implemented the {@link
 * io.github.fasset.fasset.kernel.util.queue.MessageQueue} interface by implementing the {@link io.github.fasset.fasset.kernel.util.queue.MessageQueue#push(QueueMessage)} method. The {@link
 * io.github.fasset.fasset.kernel.util.queue.MessageQueue#push(QueueMessage, OnError)} method and the {@link io.github.fasset.fasset.kernel.util.queue.MessageQueue#push(QueueMessage, OnError, *
 * OnCompletion)} method become available to the {@code FileUploadQueue} simply by extending this class. Should the client desire a different approach for the lifecycle methods, nothing's wrong with
 * it skipping this class and implementing the whole interface by itself. <br>
 * <pre>
 *     {@code
 *     public void push(QueueMessage<FileUpload> queueMessage) {
 *         fileUploadService.recordFileUpload(queueMessage.message());
 *      }
 *     }
 * </pre>
 * Please note the client above simply implements the queue method push by recording the message to the {@code FileUploadService}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public abstract class AbstractMessageQueue<T> implements MessageQueue<T> {

    /**
     * {@inheritDoc}
     * <p>
     * Adds a message to the queue
     */
    @Override
    public abstract void push(QueueMessage<T> queueMessage);

    /**
     * {@inheritDoc}
     * <p>
     * Adds a message to the queue
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnCompletion onCompletion) {

        push(queueMessage);

        // call the lifecycle method
        onCompletion.handleCompletion();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnError onError) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            onError.handleError(e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnError onError, OnCompletion onCompletion) {

        try {

            push(queueMessage);

        } catch (MQException e) {

            onError.handleError(e);
        }

        onCompletion.handleCompletion();
    }
}
