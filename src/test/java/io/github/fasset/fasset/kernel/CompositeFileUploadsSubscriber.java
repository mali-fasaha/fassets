package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositeFileUploadsSubscriber extends AbstractSubscriber implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(CompositeFileUploadsSubscriber.class);

    public CompositeFileUploadsSubscriber(String name) {
        super(name);
    }

    @Override
    protected void consumeUpdate(Update update) {

        FileUploadNotification notification = (FileUploadNotification) update.getPayload();

        log.info("FileUploadNotification : {} has been received for processing",notification);
    }
}
