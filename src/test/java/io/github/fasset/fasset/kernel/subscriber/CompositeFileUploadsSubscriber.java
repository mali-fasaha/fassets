package io.github.fasset.fasset.kernel.subscriber;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.subscriptions.AbstractSubscriber;
import io.github.fasset.fasset.kernel.subscriptions.Subscriber;
import io.github.fasset.fasset.kernel.subscriptions.Update;
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
