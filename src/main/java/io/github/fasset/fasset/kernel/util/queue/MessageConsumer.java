package io.github.fasset.fasset.kernel.util.queue;

import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;
import io.reactivex.Observable;

import java.util.Optional;


/**
 * This interface gives the client the ability, to read a message from the Queue
 */
public interface MessageConsumer<T> {


    /**
     * This method checks for messages from the queue once it has been subscribed to as
     * shown:
     * <br>
     *     <pre>
     *         {@code
     *         T message = checkMessages(
     *             (e) -> log.error("could not retrieve message due to : {}",e),
     *             () -> log.debug("message has been retrieved from the queue"))
     *                  .subscribe() // Retrieve Optional from Queue
     *                  .getOrElse(new T) // retrieve QueueMessage from Optional
     *                  .message(); // retrieve message
     *         }
     *     </pre>
     *
     * @param onError
     * @param completion
     * @return Observable optional queue message. The client will have to subscribe to the
     * item returned to read message from the Queue if any exists
     */
    Observable<Optional<QueueMessage<T>>> checkMessages(OnError onError, OnCompletion completion);
}
