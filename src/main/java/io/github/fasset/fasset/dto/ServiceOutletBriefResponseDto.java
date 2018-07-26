/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.dto;

import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

/**
 * This is a DTO for {@link io.github.fasset.fasset.model.brief.ServiceOutletBrief} items containing simpler data types for viewing on the front end
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class ServiceOutletBriefResponseDto {

    private int id;

    /* nomenclature of the service outlet*/
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
     * <p>Constructor for ServiceOutletBriefResponseDto.</p>
     *
     * @param serviceOutletBrief a {@link io.github.fasset.fasset.model.brief.ServiceOutletBrief} object.
     */
    public ServiceOutletBriefResponseDto(ServiceOutletBrief serviceOutletBrief) {
        this.id = serviceOutletBrief.getId();
        this.designation = serviceOutletBrief.getDesignation();
        this.purchaseCost = serviceOutletBrief.getPurchaseCost()
                                              .getNumber()
                                              .doubleValue();
        this.netBookValue = serviceOutletBrief.getNetBookValue()
                                              .getNumber()
                                              .doubleValue();
        this.accruedDepreciation = serviceOutletBrief.getAccruedDepreciation()
                                                     .getNumber()
                                                     .doubleValue();
        this.poll = serviceOutletBrief.getPoll();
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id a int.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    public ServiceOutletBriefResponseDto setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * <p>Getter for the field <code>designation</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * <p>Setter for the field <code>designation</code>.</p>
     *
     * @param designation a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    public ServiceOutletBriefResponseDto setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    /**
     * <p>Getter for the field <code>purchaseCost</code>.</p>
     *
     * @return a double.
     */
    public double getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * <p>Setter for the field <code>purchaseCost</code>.</p>
     *
     * @param purchaseCost a double.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    public ServiceOutletBriefResponseDto setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
        return this;
    }

    /**
     * <p>Getter for the field <code>netBookValue</code>.</p>
     *
     * @return a double.
     */
    public double getNetBookValue() {
        return netBookValue;
    }

    /**
     * <p>Setter for the field <code>netBookValue</code>.</p>
     *
     * @param netBookValue a double.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    public ServiceOutletBriefResponseDto setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
        return this;
    }

    /**
     * <p>Getter for the field <code>accruedDepreciation</code>.</p>
     *
     * @return a double.
     */
    public double getAccruedDepreciation() {
        return accruedDepreciation;
    }

    /**
     * <p>Setter for the field <code>accruedDepreciation</code>.</p>
     *
     * @param accruedDepreciation a double.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    public ServiceOutletBriefResponseDto setAccruedDepreciation(double accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
        return this;
    }

    /**
     * <p>Getter for the field <code>poll</code>.</p>
     *
     * @return a int.
     */
    public int getPoll() {
        return poll;
    }

    /**
     * <p>Setter for the field <code>poll</code>.</p>
     *
     * @param poll a int.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    public ServiceOutletBriefResponseDto setPoll(int poll) {
        this.poll = poll;
        return this;
    }


    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceOutletBriefResponseDto that = (ServiceOutletBriefResponseDto) o;

        if (id != that.id) {
            return false;
        }
        if (Double.compare(that.purchaseCost, purchaseCost) != 0) {
            return false;
        }
        if (Double.compare(that.netBookValue, netBookValue) != 0) {
            return false;
        }
        if (Double.compare(that.accruedDepreciation, accruedDepreciation) != 0) {
            return false;
        }
        if (poll != that.poll) {
            return false;
        }
        return designation != null ? designation.equals(that.designation) : that.designation == null;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        temp = Double.doubleToLongBits(purchaseCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(netBookValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(accruedDepreciation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + poll;
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ServiceOutletBriefResponseDto{");
        sb.append("nomenclature=")
          .append(id);
        sb.append(", designation='")
          .append(designation)
          .append('\'');
        sb.append(", purchaseCost=")
          .append(purchaseCost);
        sb.append(", netBookValue=")
          .append(netBookValue);
        sb.append(", accruedDepreciation=")
          .append(accruedDepreciation);
        sb.append(", poll=")
          .append(poll);
        sb.append('}');
        return sb.toString();
    }
}
