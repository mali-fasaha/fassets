package io.github.fasset.fasset.kernel.subscriptions;

/**
 * This interface allows a client to be able to publish an {@link Update} to each {@link Subscriber}
 * currently subscribed.
 * The method {@link this#postUpdate(Update)} once triggered will change the state of the client
 * and then call {@link this#notifyObservers()}, effectively publishing the {@link Update} to
 * each {@link Subscriber}
 *
 * @author edwin.njeru
 */
public interface SubscriptionService {

    // methods to registerSubscriber and deregisterSubscriber observers
    void registerSubscriber(Subscriber subscriber);
    void deregisterSubscriber(Subscriber observer);

    // method to notify observers of change
    void notifyObservers();

    // method to get updates from Subject
    Update getUpdate(Subscriber subscriber);

    void postUpdate(Update update);
}
