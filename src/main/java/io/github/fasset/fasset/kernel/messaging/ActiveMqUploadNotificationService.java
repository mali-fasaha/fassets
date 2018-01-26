package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.model.files.FileUpload;
import io.github.fasset.fasset.service.FileUploadService;
import io.github.fasset.fasset.service.impl.FileUploadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("notificationService")
public class ActiveMqUploadNotificationService implements UploadNotificationService {

    private static final Logger log = LoggerFactory.getLogger(ActiveMqUploadNotificationService.class);

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
    public void sendNotification(FileUpload notification) {

            jmsTemplate.convertAndSend("fileUploads", notification);
    }
}
