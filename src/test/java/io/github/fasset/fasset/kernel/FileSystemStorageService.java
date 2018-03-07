package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.subscriptions.SimpleSubscription;
import io.github.fasset.fasset.kernel.subscriptions.Subscriber;
import io.github.fasset.fasset.kernel.subscriptions.SubscriptionService;
import io.github.fasset.fasset.kernel.subscriptions.Update;

import java.time.LocalDateTime;

public class FileSystemStorageService extends SimpleSubscription implements SubscriptionService {

    FileStorageSubscriber subscriber;

    FileStoragePersistenceSubscriber persistenceSubscriber;


    public FileSystemStorageService() {
        super();
        this.subscriber = new FileStorageSubscriber("FileStorageSubscriber1");
        persistenceSubscriber = new FileStoragePersistenceSubscriber("PersistenceSubscriber1");
    }

    public void store(String fileName){

        subscriber.addSubscription(this);
        persistenceSubscriber.addSubscription(this);
        registerSubscriber(subscriber);
        registerSubscriber(persistenceSubscriber);

        postUpdate(() -> new FileUploadNotification(fileName,"Mar 2018", LocalDateTime.now().toString()));
    }
}
