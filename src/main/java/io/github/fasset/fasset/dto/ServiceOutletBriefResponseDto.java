package io.github.fasset.fasset.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

public class ServiceOutletBriefResponseDto {

    private int id;

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



    public ServiceOutletBriefResponseDto(ServiceOutletBrief serviceOutletBrief) {
        this.id=serviceOutletBrief.getId();
        this.designation=serviceOutletBrief.getDesignation();
        this.purchaseCost=serviceOutletBrief.getPurchaseCost().getNumber().doubleValue();
        this.netBookValue=serviceOutletBrief.getNetBookValue().getNumber().doubleValue();
        this.accruedDepreciation=serviceOutletBrief.getAccruedDepreciation().getNumber().doubleValue();
        this.poll=serviceOutletBrief.getPoll();
    }

    public String getDesignation() {
        return designation;
    }

    public ServiceOutletBriefResponseDto setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public ServiceOutletBriefResponseDto setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
        return this;
    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public ServiceOutletBriefResponseDto setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
        return this;
    }

    public double getAccruedDepreciation() {
        return accruedDepreciation;
    }

    public ServiceOutletBriefResponseDto setAccruedDepreciation(double accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
        return this;
    }

    public int getPoll() {
        return poll;
    }

    public ServiceOutletBriefResponseDto setPoll(int poll) {
        this.poll = poll;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOutletBriefResponseDto that = (ServiceOutletBriefResponseDto) o;
        return Double.compare(that.purchaseCost, purchaseCost) == 0 &&
                Double.compare(that.netBookValue, netBookValue) == 0 &&
                Double.compare(that.accruedDepreciation, accruedDepreciation) == 0 &&
                poll == that.poll &&
                Objects.equal(designation, that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(designation, purchaseCost, netBookValue, accruedDepreciation, poll);
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
}
