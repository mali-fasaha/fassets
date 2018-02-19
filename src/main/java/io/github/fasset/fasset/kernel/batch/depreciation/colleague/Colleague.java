package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.messaging.DepreciationUpdateDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This interface is a player in the mediator pattern being orchestrated by the
 * DepreciationUpdatesDispatcher which is mediating messages from the DepreciationExecutor
 * to this interface.
 * The implementations of this interface will then see to  various processes on the
 * items sent
 *
 * @param <T> Type of update to receive as dictated by the name of the implementation
 */
public abstract class Colleague<T> {

    private static final Logger log = LoggerFactory.getLogger(Colleague.class);

    private DepreciationUpdateDispatcher depreciationUpdateDispatcher;

    public Colleague setDepreciationUpdateDispatcher(DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
        this.depreciationUpdateDispatcher = depreciationUpdateDispatcher;
        return this;
    }

    public Colleague(DepreciationUpdateDispatcher depreciationUpdateDispatcher) {
        this.depreciationUpdateDispatcher = depreciationUpdateDispatcher;
    }

    /**
     * This method listens for message sent to a queue
     * containing the Object of type U and formulates appropriate
     * response
     *
     * @param updateMessage
     */
    public abstract void receive(Update<T> updateMessage);

    protected void send(Update<DepreciationUpdate> updateMessage){

        log.debug("Sending update message : {}",updateMessage);

        depreciationUpdateDispatcher.send(updateMessage.setSentBy(this),this);
    }

    public DepreciationUpdateDispatcher getDepreciationUpdateDispatcher() {
        return depreciationUpdateDispatcher;
    }
}
