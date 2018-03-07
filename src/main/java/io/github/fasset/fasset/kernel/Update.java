package io.github.fasset.fasset.kernel;

/**
 * This is a thin wrapper for data to be passed around the subscriber pattern
 */
@FunctionalInterface
public interface Update {

    /**
     *
     * @return Object wrapped in this update
     */
    Object getPayload();

}
