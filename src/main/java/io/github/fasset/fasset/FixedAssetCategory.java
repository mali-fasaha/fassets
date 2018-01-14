package io.github.fasset.fasset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * This is a representative model of the fixed asset's category for purposes of depreciation, that is its
 * name, its depreciation rate and its depreciation logic
 */
@Entity
public class FixedAssetCategory extends DomainModel {

    @Column(name="category")
    private String category;

    @OneToOne
    private DepreciationLogic depreciationLogic;

    @OneToOne
    private DepreciationRate depreciationRate;

    public FixedAssetCategory() {
    }

    public String getCategory() {
        return category;
    }

    public FixedAssetCategory setCategory(String category) {
        this.category = category;
        return this;
    }

    public DepreciationLogic getDepreciationLogic() {
        return depreciationLogic;
    }

    public FixedAssetCategory setDepreciationLogic(DepreciationLogic depreciationLogic) {
        this.depreciationLogic = depreciationLogic;
        return this;
    }

    public DepreciationRate getDepreciationRate() {
        return depreciationRate;
    }

    public FixedAssetCategory setDepreciationRate(DepreciationRate depreciationRate) {
        this.depreciationRate = depreciationRate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FixedAssetCategory that = (FixedAssetCategory) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(depreciationLogic, that.depreciationLogic) &&
                Objects.equals(depreciationRate, that.depreciationRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, depreciationLogic, depreciationRate);
    }

    @Override
    public String toString() {
        return "FixedAssetCategory{" +
                "category='" + category + '\'' +
                ", depreciationLogic=" + depreciationLogic +
                ", depreciationRate=" + depreciationRate +
                '}';
    }
}
