package io.github.fasset.fasset.kernel.messaging.dto;

import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Update;
import io.github.fasset.fasset.model.AccruedDepreciation;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.persistence.Column;
import java.time.YearMonth;
import java.util.Objects;

public class AccruedDepreciationDto implements Message<AccruedDepreciation>,Update<AccruedDepreciation> {

    private Integer month;

    private Integer year;

    private String solId;

    private String category;

    private Integer fixedAssetId;

    private Double accruedDepreciation;

    public AccruedDepreciationDto(Integer month, Integer year, String solId, String category, Integer fixedAssetId, Double accruedDepreciation) {
        this.month = month;
        this.year = year;
        this.solId = solId;
        this.category = category;
        this.fixedAssetId = fixedAssetId;
        this.accruedDepreciation = accruedDepreciation;
    }

    public AccruedDepreciationDto() {
    }

    public AccruedDepreciationDto(AccruedDepreciation accruedDepreciation) {
        this.month=accruedDepreciation.getMonth().getMonthValue();
        this.year=accruedDepreciation.getMonth().getYear();
        this.solId=accruedDepreciation.getSolId();
        this.category=accruedDepreciation.getCategory();
        this.fixedAssetId=accruedDepreciation.getFixedAssetId();
        this.accruedDepreciation=accruedDepreciation.getAccruedDepreciation();
    }

    /**
     * Return the message payload.
     */
    @Override
    public AccruedDepreciation getPayload() {
        return new AccruedDepreciation(YearMonth.of(year,month),solId,category,fixedAssetId,accruedDepreciation);
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
        AccruedDepreciationDto that = (AccruedDepreciationDto) o;
        return Objects.equals(month, that.month) &&
                Objects.equals(year, that.year) &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(category, that.category) &&
                Objects.equals(fixedAssetId, that.fixedAssetId) &&
                Objects.equals(accruedDepreciation, that.accruedDepreciation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year, solId, category, fixedAssetId, accruedDepreciation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccruedDepreciationDto{");
        sb.append("month=").append(month);
        sb.append(", year=").append(year);
        sb.append(", solId='").append(solId).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", fixedAssetId=").append(fixedAssetId);
        sb.append(", accruedDepreciation=").append(accruedDepreciation);
        sb.append('}');
        return sb.toString();
    }

    public static AccruedDepreciationDto from(AccruedDepreciation acc) {

        return new AccruedDepreciationDto(acc.getMonth().getMonthValue(),acc.getMonth().getYear(),acc.getSolId(),acc.getCategory(),acc.getFixedAssetId(),acc.getAccruedDepreciation());
    }
}
