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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.github.fasset.fasset.model.brief.CategoryBrief;

/**
 * DTO for {@code CategoryBrief} items to be displayed in the views
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class CategoryBriefResponseDto {

    private int id;

    /* name of the category*/
    private String designation;

    /* total costs of all items in this category*/
    private double purchaseCost;

    /* total NBVs of all items in this category*/
    private double netBookValue;

    /* Total accumulated depreciation for items in this category*/
    private double accruedDepreciation;

    /* total no. of items in this category*/
    private int poll;


    /**
     * <p>Constructor for CategoryBriefResponseDto.</p>
     *
     * @param categoryBrief a {@link io.github.fasset.fasset.model.brief.CategoryBrief} object.
     */
    public CategoryBriefResponseDto(CategoryBrief categoryBrief) {
        this.id = categoryBrief.getId();
        this.designation = categoryBrief.getDesignation();
        this.purchaseCost = categoryBrief.getPurchaseCost()
                                         .getNumber()
                                         .doubleValue();
        this.netBookValue = categoryBrief.getNetBookValue()
                                         .getNumber()
                                         .doubleValue();
        this.accruedDepreciation = categoryBrief.getAccruedDepreciation()
                                                .getNumber()
                                                .doubleValue();
        this.poll = categoryBrief.getPoll();
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
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    public CategoryBriefResponseDto setId(int id) {
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
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    public CategoryBriefResponseDto setDesignation(String designation) {
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
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    public CategoryBriefResponseDto setPurchaseCost(double purchaseCost) {
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
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    public CategoryBriefResponseDto setNetBookValue(double netBookValue) {
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
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    public CategoryBriefResponseDto setAccruedDepreciation(double accruedDepreciation) {
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
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    public CategoryBriefResponseDto setPoll(int poll) {
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
        CategoryBriefResponseDto that = (CategoryBriefResponseDto) o;
        return id == that.id && Double.compare(that.purchaseCost, purchaseCost) == 0 && Double.compare(that.netBookValue, netBookValue) == 0 &&
            Double.compare(that.accruedDepreciation, accruedDepreciation) == 0 && poll == that.poll && Objects.equal(designation, that.designation);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hashCode(id, designation, purchaseCost, netBookValue, accruedDepreciation, poll);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("nomenclature", id)
                          .add("designation", designation)
                          .add("purchaseCost", purchaseCost)
                          .add("netBookValue", netBookValue)
                          .add("accruedDepreciation", accruedDepreciation)
                          .add("poll", poll)
                          .toString();
    }
}
