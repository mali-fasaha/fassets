package io.github.fasset.fasset.kernel.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component("notificationService")
public class FileUploadNotificationService implements UploadNotificationService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadNotificationService.class);

    private JmsTemplate jmsTemplate;

    private MessageConverter jacksonJmsMessageConverter;


    @Autowired
    public FileUploadNotificationService(JmsTemplate jmsTemplate) {
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
