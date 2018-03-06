package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.YearMonth;
import java.util.Objects;

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "fixed_asset_id","month","accrued_depreciation","sol_id","category"
        })
})
@Audited
@Entity(name="AccruedDepreciation")
public class AccruedDepreciation extends DomainModel<String> {

    @Column(name="fixed_asset_id")
    private int fixedAssetId;

    @Column(name="month")
    private YearMonth month;

    @Column(name="sol_id")
    private String solId;

    @Column(name="category")
    private String category;

    @Column(name="accrued_depreciation")
    private double accruedDepreciation;

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public AccruedDepreciation setFixedAssetId(int fixedAssetId) {
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    public YearMonth getMonth() {
        return month;
    }

    public AccruedDepreciation setMonth(YearMonth month) {
        this.month = month;
        return this;
    }

    public String getSolId() {
        return solId;
    }

    public AccruedDepreciation setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public AccruedDepreciation setCategory(String category) {
        this.category = category;
        return this;
    }

    public double getAccruedDepreciation() {
        return accruedDepreciation;
    }

    public AccruedDepreciation setAccruedDepreciation(double accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccruedDepreciation that = (AccruedDepreciation) o;
        return fixedAssetId == that.fixedAssetId &&
                Double.compare(that.accruedDepreciation, accruedDepreciation) == 0 &&
                Objects.equals(month, that.month) &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixedAssetId, month, solId, category, accruedDepreciation);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AccruedDepreciation{");
        sb.append("fixedAssetId=").append(fixedAssetId);
        sb.append(", month=").append(month);
        sb.append(", solId='").append(solId).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", accruedDepreciation=").append(accruedDepreciation);
        sb.append('}');
        return sb.toString();
    }
}
