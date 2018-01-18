package io.github.fasset.fasset.model.brief;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import java.util.Comparator;
import java.util.Objects;

@Entity(name="ServiceOutletBrief")
@Audited
public class ServiceOutletBrief extends DomainModel<String> implements Comparable<ServiceOutletBrief>, Brief {

    /* id of the service outlet*/
    private String designation;

    /* total costs of all items in this category*/
    private double purchaseCost;

    /* total NBVs of all items in this category*/
    private double netBookValue;

    /* Total accumulated depreciaton for items in this category*/
    private double accruedDepreciation;

    /* total no. of items in this category*/
    private int poll;

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
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public double getPurchaseCost() {
        return purchaseCost;
    }

    @Override
    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    @Override
    public double getNetBookValue() {
        return netBookValue;
    }

    @Override
    public void setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
    }

    @Override
    public double getAccruedDepreciation() {
        return accruedDepreciation;
    }

    @Override
    public void setAccruedDepreciation(double accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
    }

    @Override
    public int getPoll() {
        return poll;
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
        ServiceOutletBrief that = (ServiceOutletBrief) o;
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
        final StringBuilder sb = new StringBuilder("ServiceOutletBrief{");
        sb.append("designation='").append(designation).append('\'');
        sb.append(", purchaseCost=").append(purchaseCost);
        sb.append(", netBookValue=").append(netBookValue);
        sb.append(", accruedDepreciation=").append(accruedDepreciation);
        sb.append(", poll=").append(poll);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo(ServiceOutletBrief o) {

        return Comparator.comparing(ServiceOutletBrief::getDesignation)
                .compare(this,o);
    }
}
