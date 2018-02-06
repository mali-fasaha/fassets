package io.github.fasset.fasset.kernel.messaging.dto;

import io.github.fasset.fasset.model.NetBookValue;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class NetBookValueDto implements Message<NetBookValue> {

    private NetBookValue netBookValue;

    public NetBookValueDto(NetBookValue netBookValue) {
        this.netBookValue = netBookValue;
    }

    /**
     * Return the message payload.
     */
    @Override
    public NetBookValue getPayload() {
        return netBookValue;
    }

    /**
     * Return message headers for the message (never {@code null} but may be empty).
     */
    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
