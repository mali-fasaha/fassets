package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

/**
 * This is a record of the depreciation logic to apply to the fixed asset category, recording simply its
 * name and its depricant
 *
 * @author edwin.njeru
 */
@Entity(name="DepreciationLogic")
public class DepreciationLogic extends DomainModel {

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

    /**
     * The fixed asset category to which this depreciation logic belongs
     */
    @OneToOne
    private FixedAssetCategory fixedAssetCategory;

    public DepreciationLogic() {
    }

    public FixedAssetCategory getFixedAssetCategory() {
        return fixedAssetCategory;
    }

    public DepreciationLogic setFixedAssetCategory(FixedAssetCategory fixedAssetCategory) {
        this.fixedAssetCategory = fixedAssetCategory;
        return this;
    }

    public String getDepreciationLogic() {
        return depreciationLogic;
    }

    public DepreciationLogic setDepreciationLogic(String depreciationLogic) {
        this.depreciationLogic = depreciationLogic;
        return this;
    }

    public String getDeprecant() {
        return deprecant;
    }

    public DepreciationLogic setDeprecant(String deprecant) {
        this.deprecant = deprecant;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepreciationLogic that = (DepreciationLogic) o;
        return Objects.equals(depreciationLogic, that.depreciationLogic) &&
                Objects.equals(deprecant, that.deprecant) &&
                Objects.equals(fixedAssetCategory, that.fixedAssetCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), depreciationLogic, deprecant, fixedAssetCategory);
    }

    @Override
    public String toString() {
        return "DepreciationLogic{" +
                "depreciationLogic='" + depreciationLogic + '\'' +
                ", deprecant='" + deprecant + '\'' +
                ", fixedAssetCategory=" + fixedAssetCategory +
                '}';
    }
}
