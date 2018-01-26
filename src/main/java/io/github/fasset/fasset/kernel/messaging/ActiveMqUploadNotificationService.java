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

    private final FileUploadService fileUploadService;


    @Autowired
    public ActiveMqUploadNotificationService(JmsTemplate jmsTemplate, @Qualifier("fileUploadService") FileUploadService fileUploadService) {
        this.jmsTemplate = jmsTemplate;
        this.fileUploadService = fileUploadService;
    }

    /**
     * Sends the message
     *
     * @param notification
     */
    @Override
    public void sendNotification(FileUpload notification) {

        if (!fileUploadService.theFileIsAlreadyUploaded(notification))
            jmsTemplate.convertAndSend("fileUploads", notification);
        else
            log.info("The file : {} is already uploaded and will not be uploaded again", notification.getFileName());
    }
}
