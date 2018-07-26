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
package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.text.MessageFormat;
import java.time.YearMonth;
import java.util.Objects;

//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"fixed_asset_id", "month", "sol_id", "category"})})

/**
 * Record of the net book value for a gived fixed asset at a given month after previous month's depreciation
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Entity(name = "NetBookValue")
@Audited
public class NetBookValue extends DomainModel<String> {

    private static final Logger log = LoggerFactory.getLogger(NetBookValue.class);

    @Column(name = "fixed_asset_id")
    private int fixedAssetId;

    @Column(name = "month")
    private YearMonth month;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmount", parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "KES")})
    private Money netBookValue;

    @Column(name = "sol_id")
    private String solId;

    @Column(name = "category")
    private String category;

    /**
     * <p>Constructor for NetBookValue.</p>
     */
    public NetBookValue() {
    }

    /**
     * <p>Constructor for NetBookValue.</p>
     *
     * @param fixedAssetId a int.
     * @param month a {@link java.time.YearMonth} object.
     * @param netBookValue a {@link org.javamoney.moneta.Money} object.
     * @param solId a {@link java.lang.String} object.
     * @param category a {@link java.lang.String} object.
     */
    public NetBookValue(int fixedAssetId, YearMonth month, Money netBookValue, String solId, String category) {
        this.fixedAssetId = fixedAssetId;
        this.month = month;
        this.netBookValue = netBookValue;
        this.solId = solId;
        this.category = category;
    }

    /**
     * <p>Getter for the field <code>category</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCategory() {
        return category;
    }

    /**
     * <p>Setter for the field <code>category</code>.</p>
     *
     * @param category a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.NetBookValue} object.
     */
    public NetBookValue setCategory(String category) {
        log.trace("Setting fixedAssetId for NetBookValueId : {}, as = {}", getId(), fixedAssetId);
        this.category = category;
        return this;
    }

    /**
     * <p>Getter for the field <code>fixedAssetId</code>.</p>
     *
     * @return a int.
     */
    public int getFixedAssetId() {
        return fixedAssetId;
    }

    /**
     * <p>Setter for the field <code>fixedAssetId</code>.</p>
     *
     * @param fixedAssetId a int.
     * @return a {@link io.github.fasset.fasset.model.NetBookValue} object.
     */
    public NetBookValue setFixedAssetId(int fixedAssetId) {
        log.trace("Setting fixedAssetId for NetBookValueId : {}, as = {}", getId(), fixedAssetId);
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    /**
     * <p>Getter for the field <code>month</code>.</p>
     *
     * @return a {@link java.time.YearMonth} object.
     */
    public YearMonth getMonth() {
        return month;
    }

    /**
     * <p>Setter for the field <code>month</code>.</p>
     *
     * @param month a {@link java.time.YearMonth} object.
     * @return a {@link io.github.fasset.fasset.model.NetBookValue} object.
     */
    public NetBookValue setMonth(YearMonth month) {
        log.trace("Setting month for NetBookValueId : {}, as = {}", getId(), month);
        this.month = month;
        return this;
    }

    /**
     * <p>Getter for the field <code>netBookValue</code>.</p>
     *
     * @return a {@link org.javamoney.moneta.Money} object.
     */
    public Money getNetBookValue() {
        return netBookValue;
    }

    /**
     * <p>Setter for the field <code>netBookValue</code>.</p>
     *
     * @param netBookValue a {@link org.javamoney.moneta.Money} object.
     * @return a {@link io.github.fasset.fasset.model.NetBookValue} object.
     */
    public NetBookValue setNetBookValue(Money netBookValue) {

        log.trace("Setting NetBookValue for NetBookValueId : {} as = {}", getId(), netBookValue);
        this.netBookValue = netBookValue;
        return this;
    }

    /**
     * <p>Getter for the field <code>solId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSolId() {
        return solId;
    }

    /**
     * <p>Setter for the field <code>solId</code>.</p>
     *
     * @param solId a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.NetBookValue} object.
     */
    public NetBookValue setSolId(String solId) {

        log.trace("Setting SolId for NetBookValueId : {} as = {}", getId(), solId);
        this.solId = solId;
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
        if (!super.equals(o)) {
            return false;
        }
        NetBookValue that = (NetBookValue) o;
        return fixedAssetId == that.fixedAssetId && Objects.equals(that.netBookValue, netBookValue) && Objects.equals(month, that.month) && Objects.equals(solId, that.solId) &&
            Objects.equals(category, that.category);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixedAssetId, month, netBookValue, solId, category);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return MessageFormat.format("NetBookValue'{'fixedAssetId={0}, month={1}, netBookValue={2}, solId=''{3}'', category=''{4}'''}'", fixedAssetId, month, netBookValue, solId, category);
    }
}
