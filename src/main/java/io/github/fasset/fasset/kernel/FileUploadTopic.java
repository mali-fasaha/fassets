package io.github.fasset.fasset.kernel;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Implements the Subject interface which is being observed by the registered observers.
 * This is made possible through the register and the unregister methods.
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
public class FileUploadTopic implements Subject{

    private static final Logger log = LoggerFactory.getLogger(FileUploadTopic.class);

    private List<Observer> observers = new ArrayList<>();

    private Queue<String> fileNamesQueue = new ConcurrentLinkedQueue<>();

    private boolean uploaded;

    private final Object mutex = new Object();

    @Override
    public void register(Observer observer) {
        if(observer==null) throw new NullPointerException("Null observer");

        synchronized (mutex){
            if(!observers.contains(observer))
                observers.add(observer);
        }

    }

    @Override
    public void unregister(Observer observer) {

        synchronized (mutex){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {

        List<Observer> observersLocal = null;

        // this synch block ensures that observer registered after file
        // has been uploaded are not notified
        synchronized (mutex){
            if(!uploaded)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.uploaded=false;
        }

        observersLocal.forEach(Observer::update);

        // Now to remove the first item after everyone has peeked
        this.fileNamesQueue.poll();
    }

    @Override
    public Object getUpdate(Observer observer) {

        //TODO poll off after everyone has peeked
        return this.fileNamesQueue.peek();
    }

    // Method to post file to the topic
    public void postFile(String fileName){

        log.debug("File : {} has been uploaded to the fileUpload topic",fileName);

        this.fileNamesQueue.add(fileName);
        this.uploaded = true;

        notifyObservers();

    }
}
