package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.model.AccruedDepreciation;

@FunctionalInterface
public interface UpdateProvider<T> {

    T get();

}
