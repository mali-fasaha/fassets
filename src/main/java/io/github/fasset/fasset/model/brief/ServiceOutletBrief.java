package io.github.fasset.fasset.model.brief;

import com.google.common.base.MoreObjects;
import io.github.fasset.fasset.DomainModel;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Comparator;
import java.util.Objects;

@Entity(name="ServiceOutletBrief")
@Audited
public class ServiceOutletBrief extends DomainModel<String> implements Comparable<ServiceOutletBrief>, Brief {

    /* id of the service outlet*/
    private String designation;

    /* total costs of all items in this category*/
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money purchaseCost;

    /* total NBVs of all items in this category*/
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money netBookValue;

    /* Total accumulated depreciaton for items in this category*/
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money accruedDepreciation;

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
    public Money getPurchaseCost() {
        return purchaseCost;
    }

    @Override
    public void setPurchaseCost(Money purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    @Override
    public Money getNetBookValue() {
        return netBookValue;
    }

    @Override
    public void setNetBookValue(Money netBookValue) {
        this.netBookValue = netBookValue;
    }

    @Override
    public Money getAccruedDepreciation() {
        return accruedDepreciation;
    }

    @Override
    public void setAccruedDepreciation(Money accruedDepreciation) {
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
        return poll == that.poll &&
                com.google.common.base.Objects.equal(designation, that.designation) &&
                com.google.common.base.Objects.equal(purchaseCost, that.purchaseCost) &&
                com.google.common.base.Objects.equal(netBookValue, that.netBookValue) &&
                com.google.common.base.Objects.equal(accruedDepreciation, that.accruedDepreciation);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(super.hashCode(), designation, purchaseCost, netBookValue, accruedDepreciation, poll);
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
    public int compareTo(ServiceOutletBrief o) {

        return Comparator.comparing(ServiceOutletBrief::getDesignation)
                .compare(this,o);
    }
}
