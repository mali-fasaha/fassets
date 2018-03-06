package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.Depreciation;

@FunctionalInterface
public interface DepreciationListener{

    void receiveProcessUpdate(DepreciationProceeds depreciationProceeds);
}
