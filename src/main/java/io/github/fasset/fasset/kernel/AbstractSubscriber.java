package io.github.fasset.fasset.kernel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractSubscriber implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(AbstractSubscriber.class);

    private Object MUTEX = new Object();

    // name of subscriber
    private String name;

    private List<Subscription> subscriptions = new LinkedList<>();


    AbstractSubscriber(String name) {
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
    public void addSubscription(Subscription subscription) {

        synchronized (MUTEX) {
            this.subscriptions.add(subscription);
        }
    }

    private void checkSubscriptions(Subscription subscription) {

        Update update = subscription.getUpdate(this);

        if (update == null) {
            log.debug("{}:: No new update in subscription : {}", name, subscription);
        } else {

            log.debug("{}:: Consuming update::{}", name, update);

            consumeUpdate(update);

        }
    }

    @Override
    public void unSubscribe(Subscription subscription) {

        synchronized (MUTEX) {
            this.subscriptions.remove(subscription);
        }
    }
}
