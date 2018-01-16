package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.YearMonth;
import java.util.Objects;

/**
 * This objects records the accrued depreciation for a given fixed asset at a given point in time being the end of a
 * given {@link java.time.YearMonth}
 *
 * @author edwin.njeru
 */
@Entity(name="AccruedDepreciation")
@Audited
public class AccruedDepreciation extends DomainModel<String> {

    private static final Logger log = LoggerFactory.getLogger("AccruedDepreciation");

    /**
     * The period the end of which we are recording this accrued depreciation
     */
    @Column(name="month")
    private YearMonth month;

    @Column(name="sol_id")
    private String solId;

    @Column(name="category")
    private String category;

    @Column(name="fixed_asset_id")
    private int fixedAssetId;

    @Column(name="accrued_depreciation")
    private double accruedDepreciation;

    public AccruedDepreciation() {
    }

    public double getAccruedDepreciation() {
        return accruedDepreciation;
    }

    public AccruedDepreciation setAccruedDepreciation(double accruedDepreciation) {

        log.debug("Seeting accruedDepreciation for AccruedDepreciationId : {} as = {}",getId(),accruedDepreciation);
        this.accruedDepreciation = accruedDepreciation;
        return this;
    }

    public YearMonth getMonth() {
        return month;
    }

    public AccruedDepreciation setMonth(YearMonth month) {

        log.debug("Setting month for AccruedDepreciationId : {} as = {}",getId(),month);
        this.month = month;
        return this;
    }

    public String getSolId() {
        return solId;
    }

    public AccruedDepreciation setSolId(String solId) {

        log.debug("Setting solId for AccruedDepreciationId : {} as = {}",getId(),solId);
        this.solId = solId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public AccruedDepreciation setCategory(String category) {

        log.debug("Setting category for AccruedDepreciationId : {} as = {}",getId(),category);
        this.category = category;
        return this;
    }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public AccruedDepreciation setFixedAssetId(int fixedAssetId) {

        log.debug("Setting fixedAssetId for AccruedDepreciationId : {} as = {}",getId(),fixedAssetId);
        this.fixedAssetId = fixedAssetId;
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
        return Objects.hash(super.hashCode(), month, solId, category, fixedAssetId, accruedDepreciation);
    }

    @Override
    public String toString() {
        return "AccruedDepreciation{" +
                "month=" + month +
                ", solId='" + solId + '\'' +
                ", category='" + category + '\'' +
                ", fixedAssetId=" + fixedAssetId +
                ", accruedDepreciation=" + accruedDepreciation +
                '}';
    }
}
