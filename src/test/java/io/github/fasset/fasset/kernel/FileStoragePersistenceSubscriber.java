package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.subscriptions.AbstractSubscriber;
import io.github.fasset.fasset.kernel.subscriptions.Subscriber;
import io.github.fasset.fasset.kernel.subscriptions.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStoragePersistenceSubscriber extends AbstractSubscriber implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(FileStoragePersistenceSubscriber.class);

    public FileStoragePersistenceSubscriber(String persistenceSubscriber1) {
        super(persistenceSubscriber1);
    }

    @Override
    protected void consumeUpdate(Update update) {

        FileUploadNotification fileUploadNotification = (FileUploadNotification) update.getPayload();

        log.info("File : {} has been received and recorded in the database",fileUploadNotification);
    }
}
