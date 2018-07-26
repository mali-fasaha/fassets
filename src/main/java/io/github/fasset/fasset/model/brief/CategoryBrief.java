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
package io.github.fasset.fasset.model.brief;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.github.fasset.fasset.DomainModel;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Comparator;

/**
 * Back-end representation of a record of a category and its summary in terms of total purchase costs, net book value etc etc
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Entity(name = "CategoryBrief")
@Audited
public class CategoryBrief extends DomainModel<String> implements Comparable<CategoryBrief>, Brief {

    /* name of the category*/
    private String designation;

    /* total costs of all items in this category*/
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount", parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money purchaseCost;

    /* total NBVs of all items in this category*/
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount", parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money netBookValue;

    /* Total accumulated depreciation for items in this category*/
    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount", parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money accruedDepreciation;

    /* total no. of items in this category*/
    private int poll;

    /**
     * <p>Constructor for CategoryBrief.</p>
     *
     * @param designation a {@link java.lang.String} object.
     * @param purchaseCost a {@link org.javamoney.moneta.Money} object.
     * @param netBookValue a {@link org.javamoney.moneta.Money} object.
     * @param accruedDepreciation a {@link org.javamoney.moneta.Money} object.
     * @param poll a int.
     */
    public CategoryBrief(String designation, Money purchaseCost, Money netBookValue, Money accruedDepreciation, int poll) {
        this.designation = designation;
        this.purchaseCost = purchaseCost;
        this.netBookValue = netBookValue;
        this.accruedDepreciation = accruedDepreciation;
        this.poll = poll;
    }

    /**
     * <p>Constructor for CategoryBrief.</p>
     */
    public CategoryBrief() {
    }

    /**
     * {@inheritDoc}
     *
     * Adds the number of items in the brief
     */
    @Override
    public void addPoll() {
        ++poll;
    }

    /** {@inheritDoc} */
    @Override
    public String getDesignation() {
        return designation;
    }

    /** {@inheritDoc} */
    @Override
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /** {@inheritDoc} */
    @Override
    public Money getPurchaseCost() {
        return purchaseCost;
    }

    /** {@inheritDoc} */
    @Override
    public void setPurchaseCost(Money purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    /** {@inheritDoc} */
    @Override
    public Money getNetBookValue() {
        return netBookValue;
    }

    /** {@inheritDoc} */
    @Override
    public void setNetBookValue(Money netBookValue) {
        this.netBookValue = netBookValue;
    }

    /** {@inheritDoc} */
    @Override
    public Money getAccruedDepreciation() {
        return accruedDepreciation;
    }

    /** {@inheritDoc} */
    @Override
    public void setAccruedDepreciation(Money accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
    }

    /** {@inheritDoc} */
    @Override
    public int getPoll() {
        return poll;
    }

    /** {@inheritDoc} */
    @Override
    public void setPoll(int poll) {
        this.poll = poll;
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
        if (!super.equals(o)) {
            return false;
        }
        CategoryBrief that = (CategoryBrief) o;
        return poll == that.poll && Objects.equal(designation, that.designation) && Objects.equal(purchaseCost, that.purchaseCost) && Objects.equal(netBookValue, that.netBookValue) &&
            Objects.equal(accruedDepreciation, that.accruedDepreciation);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), designation, purchaseCost, netBookValue, accruedDepreciation, poll);
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public int compareTo(CategoryBrief o) {

        return Comparator.comparing(CategoryBrief::getDesignation)
                         .compare(this, o);
    }
}
