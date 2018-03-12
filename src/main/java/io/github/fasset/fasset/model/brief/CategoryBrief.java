package io.github.fasset.fasset.model.brief;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import java.util.Comparator;

/**
 * Back-end representation of a record of a category and its summary in terms of total
 * purchase costs, net book value etc etc
 *
 * @author edwin.njeru
 */
@Entity(name="CategoryBrief")
@Audited
public class CategoryBrief extends DomainModel<String> implements Comparable<CategoryBrief>, Brief{

    /* name of the category*/
    private String designation;

    /* total costs of all items in this category*/
    private Money purchaseCost;

    /* total NBVs of all items in this category*/
    private Money netBookValue;

    /* Total accumulated depreciation for items in this category*/
    private Money accruedDepreciation;

    /* total no. of items in this category*/
    private int poll;

    public CategoryBrief(String designation, Money purchaseCost, Money netBookValue, Money accruedDepreciation, int poll) {
        this.designation = designation;
        this.purchaseCost = purchaseCost;
        this.netBookValue = netBookValue;
        this.accruedDepreciation = accruedDepreciation;
        this.poll = poll;
    }

    public CategoryBrief() {
    }

    /**
     * Adds the number of items in the brief
     */
    @Override
    public void addPoll() {
        ++poll;
    }

    @Override
    public String getDesignation() {
        return designation;
    }

    @Override
    public Money getPurchaseCost() {
        return purchaseCost;
    }

    @Override
    public Money getNetBookValue() {
        return netBookValue;
    }

    @Override
    public Money getAccruedDepreciation() {
        return accruedDepreciation;
    }

    @Override
    public int getPoll() {
        return poll;
    }

    @Override
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public void setPurchaseCost(Money purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    @Override
    public void setNetBookValue(Money netBookValue) {
        this.netBookValue = netBookValue;
    }

    @Override
    public void setAccruedDepreciation(Money accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
    }

    @Override
    public void setPoll(int poll) {
        this.poll = poll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CategoryBrief that = (CategoryBrief) o;
        return poll == that.poll &&
                Objects.equal(designation, that.designation) &&
                Objects.equal(purchaseCost, that.purchaseCost) &&
                Objects.equal(netBookValue, that.netBookValue) &&
                Objects.equal(accruedDepreciation, that.accruedDepreciation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), designation, purchaseCost, netBookValue, accruedDepreciation, poll);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("designation", designation)
                .add("purchaseCost", purchaseCost)
                .add("netBookValue", netBookValue)
                .add("accruedDepreciation", accruedDepreciation)
                .add("poll", poll)
                .toString();
    }

    @Override
    public int compareTo(CategoryBrief o) {

        return Comparator.comparing(CategoryBrief::getDesignation)
                .compare(this,o);
    }
}
