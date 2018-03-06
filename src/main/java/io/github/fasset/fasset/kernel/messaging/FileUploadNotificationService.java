package io.github.fasset.fasset.kernel.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("notificationService")
public class FileUploadNotificationService implements UploadNotificationService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadNotificationService.class);

    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("jmsTemplate")
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Sends the message
     *
     * @param notification FileUploadNotification to be sent through the ListenerContainer
     */
    @Override
    public void sendNotification(FileUploadNotification notification) {

        log.debug("Sending notification : {}", notification);

        jmsTemplate.convertAndSend("fileUploads", notification);
    }
}
