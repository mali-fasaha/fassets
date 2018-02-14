package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;

/**
 * Generic interface for wrapping items to be sent through the DepreciationUpdatesDispatcher which
 * is really just an implementation of the Mediator pattern, and the object being sent is referred
 * here to as the payload of Type U
 * @param <U> The type of payload
 */
public interface Update<U> {

    U getPayload();

    void load(U payload);

    Update setSentBy(Colleague originator);

    Update<DepreciationUpdate> setReceivedBy(Colleague<DepreciationUpdate> colleague);

    Object getDestination();
}
