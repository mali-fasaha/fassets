package io.github.fasset.fasset.kernel.messaging.dto;

import io.github.fasset.fasset.model.NetBookValue;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.time.YearMonth;
import java.util.Objects;

public class NetBookValueDto implements Message<NetBookValue> {

    private Integer fixedAssetId;

    private Integer month;
    private Integer year;

    private Double netBookValue;

    private String solId;

    private String category;

    public NetBookValueDto() {
    }

    public NetBookValueDto(Integer fixedAssetId, Integer month, Integer year, Double netBookValue, String solId, String category) {
        this.fixedAssetId = fixedAssetId;
        this.month = month;
        this.year = year;
        this.netBookValue = netBookValue;
        this.solId = solId;
        this.category = category;
    }

    public NetBookValueDto(NetBookValue netBookValue) {
        this.category = netBookValue.getCategory();
        this.fixedAssetId = netBookValue.getFixedAssetId();
        this.month = netBookValue.getMonth().getMonthValue();
        this.year = netBookValue.getMonth().getYear();
        this.netBookValue = netBookValue.getNetBookValue();
        this.solId = netBookValue.getSolId();

    }

    /**
     * Return the message payload.
     */
    @Override
    public NetBookValue getPayload() {

        return new NetBookValue(fixedAssetId,YearMonth.of(year,month),netBookValue,solId,category);
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
        NetBookValueDto that = (NetBookValueDto) o;
        return Objects.equals(fixedAssetId, that.fixedAssetId) &&
                Objects.equals(month, that.month) &&
                Objects.equals(year, that.year) &&
                Objects.equals(netBookValue, that.netBookValue) &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixedAssetId, month, year, netBookValue, solId, category);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NetBookValueDto{");
        sb.append("fixedAssetId=").append(fixedAssetId);
        sb.append(", month=").append(month);
        sb.append(", year=").append(year);
        sb.append(", netBookValue=").append(netBookValue);
        sb.append(", solId='").append(solId).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
