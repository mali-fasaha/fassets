package io.github.fasset.fasset.kernel.messaging.dto;

import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Objects;

public class FixedAssetDto implements Message<FixedAsset> {

    private FixedAsset payload;

    public FixedAssetDto() {
    }

    public FixedAssetDto(FixedAsset fixedAsset) {
        this.payload = fixedAsset;
    }

    /**
     * Return the message payload.
     */
    @Override
    public FixedAsset getPayload() {
        return payload;
    }

    /**
     * Return message headers for the message (never {@code null} but may be empty).
     */
    @Override
    public MessageHeaders getHeaders() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedAssetDto that = (FixedAssetDto) o;
        return Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FixedAssetDto{");
        sb.append("payload=").append(payload);
        sb.append('}');
        return sb.toString();
    }
}
