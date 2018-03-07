package io.github.fasset.fasset.kernel;

/**
 *
 */
public interface Subscriber {

    // method to update the observer, used by the subject
    void update();

    // attach with subject to observe
    void addSubscription(Subscription subscription);

    void unSubscribe(Subscription subscription);
}
