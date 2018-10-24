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

import io.github.fasset.fasset.model.FixedAsset;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * This is a DTO used to list assets from the persistent sink into simpler data types suitable for viewing over the Restful template
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class FixedAssetResponseDto implements Comparable<FixedAssetResponseDto> {

    private int id;

    private String solId;

    private String barcode;

    private String assetDescription;

    private LocalDate purchaseDate;

    private String category;

    private double purchaseCost;

    private double netBookValue;

    /**
     * <p>Constructor for FixedAssetResponseDto.</p>
     *
     * @param fixedAsset a {@link io.github.fasset.fasset.model.FixedAsset} object.
     */
    public FixedAssetResponseDto(FixedAsset fixedAsset) {

        this.id = fixedAsset.getId();
        this.solId = fixedAsset.getSolId();
        this.barcode = fixedAsset.getBarcode();
        this.assetDescription = fixedAsset.getAssetDescription();
        this.purchaseDate = fixedAsset.getPurchaseDate();
        this.category = fixedAsset.getCategory();
        this.purchaseCost = fixedAsset.getPurchaseCost().getNumber().doubleValue();
        this.netBookValue = fixedAsset.getNetBookValue().getNumber().doubleValue();
    }

    /**
     * <p>Constructor for FixedAssetResponseDto.</p>
     *
     * @param id               a int.
     * @param solId            a {@link java.lang.String} object.
     * @param barcode          a {@link java.lang.String} object.
     * @param assetDescription a {@link java.lang.String} object.
     * @param purchaseDate     a {@link java.time.LocalDate} object.
     * @param category         a {@link java.lang.String} object.
     * @param purchaseCost     a double.
     * @param netBookValue     a double.
     */
    public FixedAssetResponseDto(int id, String solId, String barcode, String assetDescription, LocalDate purchaseDate, String category, double purchaseCost, double netBookValue) {
        this.id = id;
        this.solId = solId;
        this.barcode = barcode;
        this.assetDescription = assetDescription;
        this.purchaseDate = purchaseDate;
        this.category = category;
        this.purchaseCost = purchaseCost;
        this.netBookValue = netBookValue;
    }

    /**
     * <p>Constructor for FixedAssetResponseDto.</p>
     */
    public FixedAssetResponseDto() {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setId(int id) {
        this.id = id;
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setSolId(String solId) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setBarcode(String barcode) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setAssetDescription(String assetDescription) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setPurchaseDate(LocalDate purchaseDate) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setCategory(String category) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setPurchaseCost(double purchaseCost) {
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
     * @return a {@link io.github.fasset.fasset.dto.FixedAssetResponseDto} object.
     */
    public FixedAssetResponseDto setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FixedAssetResponseDto that = (FixedAssetResponseDto) o;
        return id == that.id && Double.compare(that.purchaseCost, purchaseCost) == 0 && Double.compare(that.netBookValue, netBookValue) == 0 && Objects.equals(solId, that.solId) &&
            Objects.equals(barcode, that.barcode) && Objects.equals(assetDescription, that.assetDescription) && Objects.equals(purchaseDate, that.purchaseDate) &&
            Objects.equals(category, that.category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FixedAssetResponseDto{" + "nomenclature=" + id + ", solId='" + solId + '\'' + ", barcode='" + barcode + '\'' + ", assetDescription='" + assetDescription + '\'' + ", purchaseDate=" +
            purchaseDate + ", category='" + category + '\'' + ", purchaseCost=" + purchaseCost + ", netBookValue=" + netBookValue + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(FixedAssetResponseDto o) {

        return Comparator.comparing(FixedAssetResponseDto::getId)
                         .thenComparing(FixedAssetResponseDto::getSolId)
                         .thenComparing(FixedAssetResponseDto::getBarcode)
                         .thenComparing(FixedAssetResponseDto::getAssetDescription)
                         .thenComparing(FixedAssetResponseDto::getPurchaseDate)
                         .thenComparing(FixedAssetResponseDto::getCategory)
                         .thenComparing(FixedAssetResponseDto::getPurchaseCost)
                         .thenComparing(FixedAssetResponseDto::getNetBookValue)
                         .compare(this, o);
    }
}
