package io.github.fasset.fasset.kernel;

public interface Observer {

    // method to update the observer, used by the subject
    void update();

    // attach with subject to observe
    void setSubject(Subject subject);
}
