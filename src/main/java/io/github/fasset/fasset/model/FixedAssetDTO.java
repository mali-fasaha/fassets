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

import com.poiji.annotation.ExcelCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * This model represents a row of data in the excel file that is soon to be deserialized and persisted into the repository
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class FixedAssetDTO {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetDTO.class);

    @ExcelCell(1)
    private String solId;

    @ExcelCell(2)
    private String barcode;

    @ExcelCell(3)
    private String assetDescription;

    @ExcelCell(4)
    private Date purchaseDate;

    @ExcelCell(5)
    private String category;

    @ExcelCell(6)
    private String purchaseCost;

    @ExcelCell(7)
    private String netBookValue;

    /**
     * <p>Constructor for FixedAssetDTO.</p>
     */
    public FixedAssetDTO() {
    }

    /**
     * <p>Getter for the field <code>solId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSolId() {
        log.trace("Retval : {}", solId);
        return solId;
    }

    /**
     * <p>Setter for the field <code>solId</code>.</p>
     *
     * @param solId a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.FixedAssetDTO} object.
     */
    public FixedAssetDTO setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    /**
     * <p>Getter for the field <code>barcode</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBarcode() {
        log.trace("Retval : {}", barcode);
        return barcode;
    }

    /**
     * <p>Setter for the field <code>barcode</code>.</p>
     *
     * @param barcode a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.FixedAssetDTO} object.
     */
    public FixedAssetDTO setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    /**
     * <p>Getter for the field <code>assetDescription</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAssetDescription() {
        log.trace("Retval : {}", assetDescription);
        return assetDescription;
    }

    /**
     * <p>Setter for the field <code>assetDescription</code>.</p>
     *
     * @param assetDescription a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.FixedAssetDTO} object.
     */
    public FixedAssetDTO setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }

    /**
     * <p>Getter for the field <code>purchaseDate</code>.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getPurchaseDate() {
        log.trace("Retval : {}", purchaseDate);
        // trying to protect internal variables which hopefully remain invariant
        //return Date.from(purchaseDate.toInstant());

        return new Date(purchaseDate.getTime());
    }

    /*public FixedAssetDTO setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }*/

    /**
     * <p>Getter for the field <code>category</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCategory() {
        log.trace("Retval : {}", category);
        return category;
    }

    /**
     * <p>Setter for the field <code>category</code>.</p>
     *
     * @param category a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.FixedAssetDTO} object.
     */
    public FixedAssetDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    /**
     * <p>Getter for the field <code>purchaseCost</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPurchaseCost() {
        log.trace("Retval : {}", purchaseCost);
        return purchaseCost;
    }

    /**
     * <p>Setter for the field <code>purchaseCost</code>.</p>
     *
     * @param purchaseCost a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.FixedAssetDTO} object.
     */
    public FixedAssetDTO setPurchaseCost(String purchaseCost) {
        this.purchaseCost = purchaseCost;
        return this;
    }

    /**
     * <p>Getter for the field <code>netBookValue</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getNetBookValue() {
        log.trace("Retval : {}", netBookValue);
        return netBookValue;
    }

    /**
     * <p>Setter for the field <code>netBookValue</code>.</p>
     *
     * @param netBookValue a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.FixedAssetDTO} object.
     */
    public FixedAssetDTO setNetBookValue(String netBookValue) {
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
        FixedAssetDTO that = (FixedAssetDTO) o;
        return Objects.equals(solId, that.solId) && Objects.equals(barcode, that.barcode) && Objects.equals(assetDescription, that.assetDescription) &&
            Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(category, that.category) && Objects.equals(purchaseCost, that.purchaseCost) &&
            Objects.equals(netBookValue, that.netBookValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MessageFormat.format("FixedAssetDTO'{'solId=''{0}'', barcode=''{1}'', assetDescription=''{2}'', purchaseDate={3}, category=''{4}'', purchaseCost=''{5}'', netBookValue=''{6}'''}'",
                                    solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }
}
