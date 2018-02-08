package io.github.fasset.fasset.kernel.batch.depreciation;

import org.springframework.messaging.Message;

public interface UpdatePoller<U> {

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    void pollUpdates(Message<U> updateMessage);
}
