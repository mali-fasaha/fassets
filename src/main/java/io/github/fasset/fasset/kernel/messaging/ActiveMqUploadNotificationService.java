package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.messaging.model.FileUploadNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("notificationService")
public class ActiveMqUploadNotificationService implements UploadNotificationService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMqUploadNotificationService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Sends the message
     *
     * @param notification
     */
    @Override
    public void sendNotification(FileUploadNotification notification) {

        jmsTemplate.convertAndSend("fileUploads", notification);
    }
}
