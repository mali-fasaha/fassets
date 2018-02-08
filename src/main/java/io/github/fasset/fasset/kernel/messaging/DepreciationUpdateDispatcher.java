package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;

/**
 * Sends the proceeds of depreciation update for further processing in other batches
 *
 * @author edwin.njeru
 */
public interface DepreciationUpdateDispatcher {

    /**
     * Send netBookValue to messaging service
     * @param netBookValueMessage
     */
    void sendNetBookValue(NetBookValueDto netBookValueMessage);

    /**
     * Send accruedDepreciation to messaging service
     * @param accruedDepreciationMessage
     */
    void sendAccruedDepreciation(AccruedDepreciationDto accruedDepreciationMessage);

    /**
     * Send fixedAsset item for further processing
     * @param fixedAsset
     */
    void sendFixedAssetItem(FixedAssetDto fixedAsset);
}
