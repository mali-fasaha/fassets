package io.github.fasset.fasset.kernel.subscriptions;

/**
 * This is a thin wrapper for data to be passed around the {@link Subscriber} implementations
 * The object being transported can be loaded through lambda semantics, then unloaded
 * by the {@link Subscriber} using {@link this#getPayload()} method.
 * Rather than use generics which would lead to unnecessary complications, the "unloaded" value
 * will need to be casted into their respective types. This approach of "dumb update - smart subscriber"
 * assumes the {@link Subscriber} will take care while unloading the update, and also enables the
 * {@link SubscriptionService} to quickly upload any type of data through.
 * {@link Subscriber} be aware...
 *
 * @author edwin.njeru
 */
@FunctionalInterface
public interface Update {

    /**
     *
     * @return Object wrapped in this update
     */
    Object getPayload();

}
