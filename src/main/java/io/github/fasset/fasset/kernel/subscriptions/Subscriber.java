package io.github.fasset.fasset.kernel.subscriptions;

/**
 * Interface allows a client to subscribe and unSubscribe to a service as well as to receive
 * updates from the {@link SubscriptionService} through the update method.
 *
 * @author edwin.njeru
 */
public interface Subscriber {

    // method to update the observer, used by the subject
    void update();

    // attach with subject to observe
    void addSubscription(SubscriptionService subscription);

    void unSubscribe(SubscriptionService subscription);
}
