package io.github.fasset.fasset.kernel;

public interface Subscription {

    // methods to registerSubscriber and deregisterSubscriber observers
    void registerSubscriber(Subscriber subscriber);
    void deregisterSubscriber(Subscriber observer);

    // method to notify observers of change
    void notifyObservers();

    // method to get updates from Subject
    Update getUpdate(Subscriber subscriber);

    void postUpdate(Update update);
}
