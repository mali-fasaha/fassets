package io.github.fasset.fasset.kernel.messaging.dto;

import io.github.fasset.fasset.model.AccruedDepreciation;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class AccruedDepreciationDto implements Message<AccruedDepreciation> {

    private AccruedDepreciation accruedDepreciation;

    public AccruedDepreciationDto(AccruedDepreciation accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
    }

    /**
     * Return the message payload.
     */
    @Override
    public AccruedDepreciation getPayload() {
        return accruedDepreciation;
    }

    /**
     * Return message headers for the message (never {@code null} but may be empty).
     */
    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
