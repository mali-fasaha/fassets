/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.dto;

import io.github.fasset.fasset.model.FixedAsset;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class FixedAssetResponseDto implements Comparable<FixedAssetResponseDto> {

    private int id;

    private String solId;

    private String barcode;

    private String assetDescription;

    private LocalDate purchaseDate;

    private String category;

    private double purchaseCost;

    private double netBookValue;

    public FixedAssetResponseDto(FixedAsset fixedAsset) {

        this.id=fixedAsset.getId();
        this.solId=fixedAsset.getSolId();
        this.barcode=fixedAsset.getBarcode();
        this.assetDescription=fixedAsset.getAssetDescription();
        this.purchaseDate=fixedAsset.getPurchaseDate();
        this.category=fixedAsset.getCategory();
        this.purchaseCost=fixedAsset.getPurchaseCost().getNumber().doubleValue();
        this.netBookValue=fixedAsset.getNetBookValue().getNumber().doubleValue();
    }

    public FixedAssetResponseDto(int id,String solId, String barcode, String assetDescription, LocalDate purchaseDate, String category, double purchaseCost, double netBookValue) {
        this.id=id;
        this.solId = solId;
        this.barcode = barcode;
        this.assetDescription = assetDescription;
        this.purchaseDate = purchaseDate;
        this.category = category;
        this.purchaseCost = purchaseCost;
        this.netBookValue = netBookValue;
    }

    public FixedAssetResponseDto() {
    }

    public int getId() {
        return id;
    }

    public FixedAssetResponseDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getSolId() {
        return solId;
    }

    public FixedAssetResponseDto setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public FixedAssetResponseDto setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public FixedAssetResponseDto setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public FixedAssetResponseDto setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public FixedAssetResponseDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public FixedAssetResponseDto setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
        return this;
    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public FixedAssetResponseDto setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedAssetResponseDto that = (FixedAssetResponseDto) o;
        return id == that.id &&
                Double.compare(that.purchaseCost, purchaseCost) == 0 &&
                Double.compare(that.netBookValue, netBookValue) == 0 &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(barcode, that.barcode) &&
                Objects.equals(assetDescription, that.assetDescription) &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }

    @Override
    public String toString() {
        return "FixedAssetResponseDto{" +
                "id=" + id +
                ", solId='" + solId + '\'' +
                ", barcode='" + barcode + '\'' +
                ", assetDescription='" + assetDescription + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", category='" + category + '\'' +
                ", purchaseCost=" + purchaseCost +
                ", netBookValue=" + netBookValue +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
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
                .compare(this,o);
    }
}
