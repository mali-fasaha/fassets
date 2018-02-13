package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

/**
 * Generic interface for wrapping items to be sent through the DepreciationUpdatesDispatcher which
 * is really just an implementation of the Mediator pattern, and the object being sent is referred
 * here to as the payload of Type U
 * @param <U> The type of payload
 */
public interface Update<U> {

    U getPayload();

    void load(U payload);
}
