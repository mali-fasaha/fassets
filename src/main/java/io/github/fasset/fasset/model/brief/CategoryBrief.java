package io.github.fasset.fasset.model.brief;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Entity;
import java.util.Comparator;
import java.util.Objects;

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
    private double accruedDepreciation;

    /* total no. of items in this category*/
    private int poll;

    public CategoryBrief(String designation, Money purchaseCost, Money netBookValue, double accruedDepreciation, int poll) {
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
    public double getNetBookValue() {
        return netBookValue;
    }

    @Override
    public double getAccruedDepreciation() {
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
    public void setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
    }

    @Override
    public void setAccruedDepreciation(double accruedDepreciation) {
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
        return Double.compare(that.purchaseCost, purchaseCost) == 0 &&
                Double.compare(that.netBookValue, netBookValue) == 0 &&
                Double.compare(that.accruedDepreciation, accruedDepreciation) == 0 &&
                poll == that.poll &&
                Objects.equals(designation, that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), designation, purchaseCost, netBookValue, accruedDepreciation, poll);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CategoryBrief{");
        sb.append("designation='").append(designation).append('\'');
        sb.append(", purchaseCost=").append(purchaseCost);
        sb.append(", netBookValue=").append(netBookValue);
        sb.append(", accruedDepreciation=").append(accruedDepreciation);
        sb.append(", poll=").append(poll);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo(CategoryBrief o) {

        return Comparator.comparing(CategoryBrief::getDesignation)
                .compare(this,o);
    }
}
