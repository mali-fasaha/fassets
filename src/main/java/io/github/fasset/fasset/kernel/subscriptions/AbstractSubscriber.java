package io.github.fasset.fasset.kernel.subscriptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Provides the {@link AbstractSubscriber#consumeUpdate(Update)} method used by the runtime client
 * to consume updates dynamically.
 * As much as possible but without guarantee care has been taken to make this implementation as thread-safe
 * as possible.
 *
 * @author edwin.njeru
 */
public abstract class AbstractSubscriber implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(AbstractSubscriber.class);

    private Object MUTEX = new Object();

    // name of subscriber
    private String name;

    private List<SubscriptionService> subscriptions = new LinkedList<>();


    protected AbstractSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {

        synchronized (MUTEX) {
            subscriptions.forEach(this::checkSubscriptions);
        }
    }

    protected abstract void consumeUpdate(Update update);


    @Override
    public void addSubscription(SubscriptionService subscription) {

        synchronized (MUTEX) {
            this.subscriptions.add(subscription);
        }
    }

    private void checkSubscriptions(SubscriptionService subscription) {

        Update update = subscription.getUpdate(this);

        if (update == null) {
            log.debug("{}:: No new update in subscription : {}", name, subscription);
        } else {

            log.debug("{}:: Consuming update::{}", name, update);

            consumeUpdate(update);

        }
    }

    @Override
    public void unSubscribe(SubscriptionService subscription) {

        synchronized (MUTEX) {
            this.subscriptions.remove(subscription);
        }
    }
}
