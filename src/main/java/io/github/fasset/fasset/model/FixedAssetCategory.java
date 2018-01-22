package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

/**
 * This is a representative model of the fixed asset's category for purposes of depreciation, that is its
 * name, its depreciation rate and its depreciation logic
 */
@Entity(name="FixedAssetCategory")
@Audited
public class FixedAssetCategory extends DomainModel<String> {

    @Column(name="category")
    private String designation;

    /**
     * The name of the depreciation logic
     */
    @Column(name="depreciation_logic")
    private String depreciationLogic;

    /**
     * This is the item on which the depreciation rate is applied, as in either the cost
     * or the net book value
     */
    @Column(name="deprecant")
    private String deprecant;

    @Column(name="depreciation_rate")
    private double depreciationRate;

    public FixedAssetCategory() {
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepreciationLogic() {
        return depreciationLogic;
    }

    public void setDepreciationLogic(String depreciationLogic) {
        this.depreciationLogic = depreciationLogic;
    }

    public String getDeprecant() {
        return deprecant;
    }

    public void setDeprecant(String deprecant) {
        this.deprecant = deprecant;
    }

    public double getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FixedAssetCategory that = (FixedAssetCategory) o;
        return Double.compare(that.depreciationRate, depreciationRate) == 0 &&
                Objects.equals(designation, that.designation) &&
                Objects.equals(depreciationLogic, that.depreciationLogic) &&
                Objects.equals(deprecant, that.deprecant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), designation, depreciationLogic, deprecant, depreciationRate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FixedAssetCategory{");
        sb.append("designation='").append(designation).append('\'');
        sb.append(", depreciationLogic='").append(depreciationLogic).append('\'');
        sb.append(", deprecant='").append(deprecant).append('\'');
        sb.append(", depreciationRate=").append(depreciationRate);
        sb.append('}');
        return sb.toString();
    }
}
