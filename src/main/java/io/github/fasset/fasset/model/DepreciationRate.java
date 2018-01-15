package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * This is a record of the depreciation rate to be accorded to each fixed asset category
 *
 * @author edwin.njeru
 */
@Entity(name="DepreciationRate")
public class DepreciationRate extends DomainModel {

    @Column(name="category")
    private String category;

    @Column(name="depreciation_rate")
    private double depreciationRate;

    @OneToOne
    private FixedAssetCategory fixedAssetCategory;

    public DepreciationRate() {
    }

    public FixedAssetCategory getFixedAssetCategory() {
        return fixedAssetCategory;
    }

    public DepreciationRate setFixedAssetCategory(FixedAssetCategory fixedAssetCategory) {
        this.fixedAssetCategory = fixedAssetCategory;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public DepreciationRate setCategory(String category) {
        this.category = category;
        return this;
    }

    public double getDepreciationRate() {
        return depreciationRate;
    }

    public DepreciationRate setDepreciationRate(double depreciationRate) {
        this.depreciationRate = depreciationRate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepreciationRate that = (DepreciationRate) o;
        return Double.compare(that.depreciationRate, depreciationRate) == 0 &&
                Objects.equals(category, that.category) &&
                Objects.equals(fixedAssetCategory, that.fixedAssetCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, depreciationRate, fixedAssetCategory);
    }

    @Override
    public String toString() {
        return "DepreciationRate{" +
                "category='" + category + '\'' +
                ", depreciationRate=" + depreciationRate +
                ", fixedAssetCategory=" + fixedAssetCategory +
                '}';
    }
}
