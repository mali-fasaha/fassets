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
 * This class provides a common framework for how the completion and handle error methods should be seen. <br> The client being a MessageQueue 'producer' needs not bother with who to 'arrange'
 * handling errors and handling completion. It only needs to provide an implementation of the {@link MessageQueue#push(QueueMessage)} method. <br> This example shows how the {@code FileUploadsQueue}
 * 'producers' has implemented the {@link MessageQueue} interface by implementing the {@link MessageQueue#push(QueueMessage)} method. The {@link MessageQueue#push(QueueMessage, OnError)} method and
 * the {@link MessageQueue#push(QueueMessage, OnError, OnCompletion)} method become available to the {@code FileUploadQueue} simply by extending this class. Should the client desire a different
 * approach for the lifecycle methods, nothing's wrong with it skipping this class and implementing the whole interface by itself. <br>
 * <br> <pre>
 *     {@code
 *     @Override
 *     public void push(QueueMessage<FileUpload> queueMessage) {
 * <p>
 *         fileUploadService.recordFileUpload(queueMessage.message());
 *     }
 *     }
 *    </pre>
 * <p>
 * <br> Note: <br> The client above simply implements the queue method push by recording the message to the {@code FileUploadService}
 */
public abstract class AbstractMessageQueue<T> implements MessageQueue<T> {

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    @Override
    public abstract void push(QueueMessage<T> queueMessage);

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     * @param onCompletion Completion lifecycle action
     */
    @Override
    public void push(QueueMessage<T> queueMessage, OnCompletion onCompletion) {

        push(queueMessage);

        // call the lifecycle method
        onCompletion.handleCompletion();
    }

    /**
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param onError      this is a functional interface design to allow producers to have a custom way of handling errors
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
     * Adds a message to the queue, and provides a method to allow the producer to handle error
     *
     * @param queueMessage Item to be added to the queue
     * @param onError      This is a functional interface for handling errors when they occur
     * @param onCompletion This is a functional interface for handling task completion tasks
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
