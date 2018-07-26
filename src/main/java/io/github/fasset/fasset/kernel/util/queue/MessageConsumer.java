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
import io.reactivex.Observable;

import java.util.Optional;


/**
 * This interface gives the client the ability, to read a message from the Queue
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface MessageConsumer<T> {


    /**
     * This method checks for messages from the queue once it has been subscribed to as shown:
     * <pre>
     *         {@code
     *         T message = checkMessages(
     *             (e) -> log.error("could not retrieve message due to : {}",e),
     *             () -> log.debug("message has been retrieved from the queue"))
     *                  .subscribe() // Retrieve Optional from Queue
     *                  .getOrElse(new T) // retrieve QueueMessage from Optional
     *                  .message(); // retrieve message
     *         }
     * </pre>
     *
     * @param onError Callback to execute when there is an exceptional event
     * @param completion Callback to execute when the method has completed completion
     * @return Observable optional queue message. The client will have to subscribe to the item returned to read message from the Queue if any exists
     */
    Observable<Optional<QueueMessage<T>>> checkMessages(OnError onError, OnCompletion completion);
}
