package io.github.fasset.fasset.kernel.messaging;

import javax.jms.Message;

public interface MessageService {

    /**
     * Sends the message
     * @param message
     */
    void sendMessage(org.springframework.messaging.Message<Object> message);

    /**
     * Listens and handles incoming messages
     * @param message
     */
    void listenForMessages(Message message);
}
