package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;

/**
 * General representation of an object that given the {@link FixedAsset} and the {@YearMonth} will
 * return the desired object to the caller
 *
 * @author edwin.njeru
 */
public interface Agent<T> {

    T invoke(FixedAsset asset, YearMonth month);
}
