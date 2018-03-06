package io.github.fasset.fasset.kernel;

public interface Subject {

    // methods to register and unregister observers
    void register(Observer observer);
    void unregister(Observer observer);

    // method to notify observers of change
    void notifyObservers();

    // method to get updates from Subject
    Object getUpdate(Observer observer);
}
