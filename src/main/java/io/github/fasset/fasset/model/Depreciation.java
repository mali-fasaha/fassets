package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.YearMonth;
import java.util.Objects;

/**
 * This is a record of a depreciation for a given asset for a given month
 *
 * @author edwin.njeru
 */
@Entity(name="Depreciation")
public class Depreciation extends DomainModel {

    private static final Logger log = LoggerFactory.getLogger(Depreciation.class);

    @Column(name="depreciation_period")
    private YearMonth depreciationPeriod;

    @Column(name="fixed_asset_id")
    private int fixedAssetId;

    @Column(name="category")
    private String category;

    @Column(name="sol_id")
    private String solId;

    @Column(name="depreciation")
    private double depreciation;

    public Depreciation() {
    }

    public YearMonth getDepreciationPeriod() {
        return depreciationPeriod;
    }

    public Depreciation setDepreciationPeriod(YearMonth depreciationPeriod) {

        log.debug("Setting depreciation period for depreciationId : {}, as = {}",getId(),depreciationPeriod);
        this.depreciationPeriod = depreciationPeriod;
        return this;
    }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public Depreciation setFixedAssetId(int fixedAssetId) {

        log.debug("Setting fixedAssetId for depreciationId : {}, as = {}",getId(),fixedAssetId);
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Depreciation setCategory(String category) {

        log.debug("Setting the category for depreciationId : {}, as = {}",getId(),category);
        this.category = category;
        return this;
    }

    public String getSolId() {
        return solId;
    }

    public Depreciation setSolId(String solId) {

        log.debug("Setting the sol id for depreciationId : {}, as ={}",getId(),solId);
        this.solId = solId;
        return this;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public Depreciation setDepreciation(double depreciation) {

        log.debug("Setting the depreciation for depreciationId : {}, as = {}",getId(),depreciation);
        this.depreciation = depreciation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Depreciation that = (Depreciation) o;
        return fixedAssetId == that.fixedAssetId &&
                Double.compare(that.depreciation, depreciation) == 0 &&
                Objects.equals(depreciationPeriod, that.depreciationPeriod) &&
                Objects.equals(category, that.category) &&
                Objects.equals(solId, that.solId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), depreciationPeriod, fixedAssetId, category, solId, depreciation);
    }

    @Override
    public String toString() {
        return "Depreciation{" +
                "depreciationPeriod=" + depreciationPeriod +
                ", fixedAssetId=" + fixedAssetId +
                ", category='" + category + '\'' +
                ", solId='" + solId + '\'' +
                ", depreciation=" + depreciation +
                '}';
    }
}
