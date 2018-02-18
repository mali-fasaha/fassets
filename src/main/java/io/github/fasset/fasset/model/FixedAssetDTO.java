package io.github.fasset.fasset.model;

import com.poiji.annotation.ExcelCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

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

    public FixedAssetDTO() {
    }

    public String getSolId() {
        log.trace("Retval : {}", solId);
        return solId;
    }

    public String getBarcode() {
        log.trace("Retval : {}", barcode);
        return barcode;
    }

    public String getAssetDescription() {
        log.trace("Retval : {}", assetDescription);
        return assetDescription;
    }

    public Date getPurchaseDate() {
        log.trace("Retval : {}", purchaseDate);
        return purchaseDate;
    }

    public String getCategory() {
        log.trace("Retval : {}", category);
        return category;
    }

    public String getPurchaseCost() {
        log.trace("Retval : {}", purchaseCost);
        return purchaseCost;
    }

    public String getNetBookValue() {
        log.trace("Retval : {}", netBookValue);
        return netBookValue;
    }

    public FixedAssetDTO setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    public FixedAssetDTO setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public FixedAssetDTO setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }

    public FixedAssetDTO setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public FixedAssetDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    public FixedAssetDTO setPurchaseCost(String purchaseCost) {
        this.purchaseCost = purchaseCost;
        return this;
    }

    public FixedAssetDTO setNetBookValue(String netBookValue) {
        this.netBookValue = netBookValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixedAssetDTO that = (FixedAssetDTO) o;
        return Objects.equals(solId, that.solId) &&
                Objects.equals(barcode, that.barcode) &&
                Objects.equals(assetDescription, that.assetDescription) &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(category, that.category) &&
                Objects.equals(purchaseCost, that.purchaseCost) &&
                Objects.equals(netBookValue, that.netBookValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FixedAssetDTO{");
        sb.append("solId='").append(solId).append('\'');
        sb.append(", barcode='").append(barcode).append('\'');
        sb.append(", assetDescription='").append(assetDescription).append('\'');
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", category='").append(category).append('\'');
        sb.append(", purchaseCost='").append(purchaseCost).append('\'');
        sb.append(", netBookValue='").append(netBookValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
