package io.github.fasset.fasset.kernel.subscriber;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.subscriptions.SimpleSubscription;
import io.github.fasset.fasset.kernel.subscriptions.SubscriptionService;

import java.time.LocalDateTime;

/**
 * Provides storage service for file uploaded to the backend
 */
class FileSystemStorageService extends SimpleSubscription implements SubscriptionService {

    private FileStorageSubscriber subscriber;

    private FileStoragePersistenceSubscriber persistenceSubscriber;


    FileSystemStorageService() {
        super();
        this.subscriber = new FileStorageSubscriber("FileStorageSubscriber1");
        persistenceSubscriber = new FileStoragePersistenceSubscriber("PersistenceSubscriber1");
    }

    @SuppressWarnings("all")
    void store(String fileName){

        subscriber.addSubscription(this);
        persistenceSubscriber.addSubscription(this);
        registerSubscriber(subscriber);
        registerSubscriber(persistenceSubscriber);

        postUpdate(() -> new FileUploadNotification(fileName,"Mar 2018", LocalDateTime.now().toString()));
    }
}
