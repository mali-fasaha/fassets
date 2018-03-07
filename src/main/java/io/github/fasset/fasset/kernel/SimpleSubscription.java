package io.github.fasset.fasset.kernel;

import com.google.common.collect.ImmutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Implements the Subject interface which is being observed by the registered observers.
 * This is made possible through the registerSubscriber and the deregisterSubscriber methods.
 * The boolean uploaded keeps track of whether or nor a file has been uploaded and is
 * used to notify observers. Therefore if there is no update and someone calls notifyObservers
 * it doesn't send false notifications
 * Synchronization is also used to make sure that the notification is only sent to the observers
 * registered before the fileName( message) is published to the topic
 *
 * adapted from https://www.journaldev.com/1739/observer-design-pattern-in-java
 * posted on AUGUST 2, 2016
 * @author Pankaj
 *
 * modified by
 * @author edwin.njeru
 */
public class SimpleSubscription implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SimpleSubscription.class);

    private List<Subscriber> observers = FastList.newList();

    private Queue<Update> updateQueue = new ConcurrentLinkedQueue<>();

    private boolean uploaded;

    private final Object MUTEX = new Object();

    @Override
    public void registerSubscriber(Subscriber subscriber) {
        if(subscriber ==null) throw new NullPointerException("Null observer");

        synchronized (MUTEX){
            if(!observers.contains(subscriber))
                observers.add(subscriber);
        }

    }

    @Override
    public void deregisterSubscriber(Subscriber observer) {

        synchronized (MUTEX){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {

        List<Subscriber> observersLocal = null;

        // this sync block ensures that observer registered after file
        // has been uploaded are not notified
        synchronized (MUTEX){
            if(!uploaded)
                return;
            observersLocal = ImmutableList.copyOf(observers);

            this.uploaded=false;
        }

        observersLocal.forEach(Subscriber::update);

        // Now to remove the first item after everyone has peeked
        this.updateQueue.poll();
    }

    @Override
    public Update getUpdate(Subscriber subscriber) {

        return this.updateQueue.peek();
    }

    // Method to post file to the topic
    @Override
    public void postUpdate(Update update){

        log.debug("Update : {} has been uploaded to the topic {}",update,this);

        this.updateQueue.offer(update);
        this.uploaded = true;

        notifyObservers();

    }
}
