package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Main representation of a fixed asset in the server
 *
 * @author edwin.njeru
 */
/*@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "sol_id","barcode","asset_description","purchase_date","category","purchase_cost"
        })
})*/
@Entity(name="FixedAsset")
@Audited
public class FixedAsset extends DomainModel<String> implements Serializable,Comparable<FixedAsset>{

    private static final Logger log = LoggerFactory.getLogger(FixedAsset.class);

    @Column(name="sol_id")
    private String solId;

    @Column(name="barcode")
    private String barcode;

    @Column(name="asset_description")
    private String assetDescription;

    @Column(name="purchase_date")
    private LocalDate purchaseDate;

    @Column(name="category")
    private String category;

    @Column(name="purchase_cost")
    private double purchaseCost;

    @Column(name="net_book_value")
    private double netBookValue;

    public FixedAsset() {
    }

    public String getSolId() {
        return solId;
    }

    public FixedAsset setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public FixedAsset setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public FixedAsset setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public FixedAsset setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public FixedAsset setCategory(String category) {
        this.category = category;
        return this;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public FixedAsset setPurchaseCost(double purchaseCost) {

        log.debug("Setting the purchaseCost for fixedAssetId : {} ,as = {}",getId(),purchaseCost);
        this.purchaseCost = purchaseCost;
        return this;
    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public FixedAsset setNetBookValue(double netBookValue) {

        log.debug("Setting NBV for assetId : {}, as = {}",getId(),netBookValue);
        this.netBookValue = netBookValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FixedAsset that = (FixedAsset) o;
        return Double.compare(that.purchaseCost, purchaseCost) == 0 &&
                Double.compare(that.netBookValue, netBookValue) == 0 &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(barcode, that.barcode) &&
                Objects.equals(assetDescription, that.assetDescription) &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), solId, barcode, assetDescription, purchaseDate, category, purchaseCost, netBookValue);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FixedAsset{");
        sb.append("solId='").append(solId).append('\'');
        sb.append(", barcode='").append(barcode).append('\'');
        sb.append(", assetDescription='").append(assetDescription).append('\'');
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", category='").append(category).append('\'');
        sb.append(", purchaseCost=").append(purchaseCost);
        sb.append(", netBookValue=").append(netBookValue);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(FixedAsset o) {

        return Comparator.comparing(FixedAsset::getSolId)
                .thenComparing(FixedAsset::getCategory)
                .thenComparing(FixedAsset::getPurchaseDate)
                .thenComparing(FixedAsset::getPurchaseCost)
                .compare(this,o);
    }

}
