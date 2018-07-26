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
import io.github.fasset.fasset.kernel.util.convert.DoubleToMoneyConverter;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.LocalDate;

/**
 * This is a DTO used by the Additions workflow to transfer user input from the front end
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class FixedAssetFormDto {

    private DoubleToMoneyConverter doubleToMoneyConverter;

    private String solId;

    private String barcode;

    private String assetDescription;

    private LocalDate purchaseDate;

    private String category;

    private double purchaseCost;

    private double netBookValue;

    /**
     * <p>Constructor for FixedAssetFormDto.</p>
     *
     * @param doubleToMoneyConverter a {@link io.github.fasset.fasset.kernel.util.convert.DoubleToMoneyConverter} object.
     */
    public FixedAssetFormDto(DoubleToMoneyConverter doubleToMoneyConverter) {
        this.doubleToMoneyConverter = doubleToMoneyConverter;
    }

    /**
     * <p>getFixedAsset.</p>
     *
     * @return a {@link io.github.fasset.fasset.model.FixedAsset} object.
     */
    public FixedAsset getFixedAsset() {

        return new FixedAsset().setSolId(solId)
                               .setBarcode(barcode)
                               .setAssetDescription(assetDescription)
                               .setPurchaseDate(purchaseDate)
                               .setCategory(category)
                               .setPurchaseCost(doubleToMoneyConverter.convert(purchaseCost))
                               .setNetBookValue(doubleToMoneyConverter.convert(netBookValue));

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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    /**
     * <p>Getter for the field <code>barcode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * <p>Setter for the field <code>barcode</code>.</p>
     *
     * @param barcode a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    /**
     * <p>Getter for the field <code>assetDescription</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAssetDescription() {
        return assetDescription;
    }

    /**
     * <p>Setter for the field <code>assetDescription</code>.</p>
     *
     * @param assetDescription a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }

    /**
     * <p>Getter for the field <code>purchaseDate</code>.</p>
     *
     * @return a {@link java.time.LocalDate} object.
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * <p>Setter for the field <code>purchaseDate</code>.</p>
     *
     * @param purchaseDate a {@link java.time.LocalDate} object.
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setCategory(String category) {
        this.category = category;
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setPurchaseCost(double purchaseCost) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetFormDto} object.
     */
    public FixedAssetFormDto setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
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
        FixedAssetFormDto that = (FixedAssetFormDto) o;
        return Double.compare(that.purchaseCost, purchaseCost) == 0 && Double.compare(that.netBookValue, netBookValue) == 0 && Objects.equal(doubleToMoneyConverter, that.doubleToMoneyConverter) &&
            Objects.equal(solId, that.solId) && Objects.equal(barcode, that.barcode) && Objects.equal(assetDescription, that.assetDescription) && Objects.equal(purchaseDate, that.purchaseDate) &&
            Objects.equal(category, that.category);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hashCode(doubleToMoneyConverter, solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("solId", solId)
                          .add("barcode", barcode)
                          .add("assetDescription", assetDescription)
                          .add("purchaseDate", purchaseDate)
                          .add("category", category)
                          .add("purchaseCost", purchaseCost)
                          .add("netBookValue", netBookValue)
                          .toString();
    }
}
