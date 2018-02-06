package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;
import org.springframework.messaging.Message;

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
    void sendNetBookValue(Message<NetBookValue> netBookValueMessage);

    /**
     * Send accruedDepreciation to messaging service
     * @param accruedDepreciationMessage
     */
    void sendAccruedDepreciation(Message<AccruedDepreciation> accruedDepreciationMessage);

    /**
     * Send fixedAsset item for further processing
     * @param fixedAsset
     */
    void sendFixedAssetItem(Message<FixedAsset> fixedAsset);
}
