package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth; /**
 * Container for agents through which request is passed
 */
public interface DepreciationAgentsHandler {



    Depreciation sendRequest(FixedAsset asset, YearMonth month);
}
