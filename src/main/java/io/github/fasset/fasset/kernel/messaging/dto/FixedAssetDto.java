package io.github.fasset.fasset.kernel.messaging.dto;

import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class FixedAssetDto implements Message<FixedAsset> {

    private FixedAsset fixedAsset;

    public FixedAssetDto(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    /**
     * Return the message payload.
     */
    @Override
    public FixedAsset getPayload() {
        return fixedAsset;
    }

    /**
     * Return message headers for the message (never {@code null} but may be empty).
     */
    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
