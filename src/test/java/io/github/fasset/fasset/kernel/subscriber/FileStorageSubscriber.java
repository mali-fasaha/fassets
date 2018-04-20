package io.github.fasset.fasset.kernel.subscriber;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.subscriptions.AbstractSubscriber;
import io.github.fasset.fasset.kernel.subscriptions.Subscriber;
import io.github.fasset.fasset.kernel.subscriptions.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorageSubscriber extends AbstractSubscriber implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(FileStorageSubscriber.class);

    public FileStorageSubscriber(String name) {
        super(name);
    }

    @Override
    protected void consumeUpdate(Update update) {

        FileUploadNotification notification = (FileUploadNotification) update.getPayload();

        log.info("{} has received the file : {} for processing",this,notification);
    }
}
