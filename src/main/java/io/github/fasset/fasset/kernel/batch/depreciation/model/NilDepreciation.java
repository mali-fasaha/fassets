package io.github.fasset.fasset.kernel.batch.depreciation.model;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.YearMonth;
import java.util.Objects;

/**
 * Object represents the concept of when no depreciation has occurred
 *
 * @author edwin.njeru
 */
public class NilDepreciation {

    private static final Logger log = LoggerFactory.getLogger(NilDepreciation.class);

    private int year;

    private int month;

    private YearMonth depreciationPeriod;

    private int fixedAssetId;

    private String category;

    private String solId;

    private double depreciation;

    public NilDepreciation(FixedAsset asset, YearMonth depreciationPeriod) {
        this.month=depreciationPeriod.getMonthValue();
        this.year=depreciationPeriod.getYear();
        this.depreciationPeriod=depreciationPeriod;
        this.category=asset.getCategory();
        this.solId=asset.getSolId();
        this.fixedAssetId=asset.getId();
        this.depreciation=0.00;
    }

    /**
     * Creates a depreciation object whose depreciation is Zero relative to the
     * fixedAsset item given and the depreciation period
     *
     * @return
     */
    public Depreciation getNilDepreciation(){

        log.trace("Returning nil depreciation item relative to asset : {} for the" +
                " depreciation period : {}",fixedAssetId,depreciationPeriod);
        return new Depreciation()
                .setMonth(month)
                .setYear(year)
                .setDepreciationPeriod(depreciationPeriod)
                .setCategory(category)
                .setSolId(solId)
                .setFixedAssetId(fixedAssetId)
                .setDepreciation(0.00);
    }

    public NilDepreciation() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public YearMonth getDepreciationPeriod() {
        return depreciationPeriod;
    }

    public void setDepreciationPeriod(YearMonth depreciationPeriod) {
        this.depreciationPeriod = depreciationPeriod;
    }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public void setFixedAssetId(int fixedAssetId) {
        this.fixedAssetId = fixedAssetId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(double depreciation) {
        this.depreciation = depreciation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NilDepreciation that = (NilDepreciation) o;
        return year == that.year &&
                month == that.month &&
                fixedAssetId == that.fixedAssetId &&
                Double.compare(that.depreciation, depreciation) == 0 &&
                Objects.equals(depreciationPeriod, that.depreciationPeriod) &&
                Objects.equals(category, that.category) &&
                Objects.equals(solId, that.solId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, depreciationPeriod, fixedAssetId, category, solId, depreciation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NilDepreciation{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", depreciationPeriod=").append(depreciationPeriod);
        sb.append(", fixedAssetId=").append(fixedAssetId);
        sb.append(", category='").append(category).append('\'');
        sb.append(", solId='").append(solId).append('\'');
        sb.append(", depreciation=").append(depreciation);
        sb.append('}');
        return sb.toString();
    }
}
