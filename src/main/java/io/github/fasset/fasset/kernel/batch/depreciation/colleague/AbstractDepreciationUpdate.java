package io.github.fasset.fasset.kernel.batch.depreciation.colleague;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;

public abstract class AbstractDepreciationUpdate<T> implements Update<DepreciationUpdate> {

    @Override
    public abstract DepreciationUpdate getPayload();
}
