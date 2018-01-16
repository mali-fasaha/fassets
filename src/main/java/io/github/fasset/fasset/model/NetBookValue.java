package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.YearMonth;
import java.util.Objects;

/**
 * Record of the net book value for a gived fixed asset at a given month after previous month's depreciation
 *
 * @author edwin.njeru
 */
@Entity(name="NetBookValue")
@Audited
public class NetBookValue extends DomainModel<String> {

    private static final Logger log = LoggerFactory.getLogger(NetBookValue.class);

    @Column(name="fixed_asset_id")
    private int fixedAssetId;

    @Column(name="month")
    private YearMonth month;

    @Column(name="net_book_value")
    private double netBookValue;

    @Column(name="sol_id")
    private String solId;

    public NetBookValue() {
    }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public NetBookValue setFixedAssetId(int fixedAssetId) {
        log.debug("Setting fixedAssetId for NetBookValueId : {}, as = {}",getId(),fixedAssetId);
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    public YearMonth getMonth() {
        return month;
    }

    public NetBookValue setMonth(YearMonth month) {
        log.debug("Setting month for NetBookValueId : {}, as = {}",getId(),month);
        this.month = month;
        return this;
    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public NetBookValue setNetBookValue(double netBookValue) {

        log.debug("Setting NetBookValue for NetBookValueId : {} as = {}",getId(),netBookValue);
        this.netBookValue = netBookValue;
        return this;
    }

    public String getSolId() {
        return solId;
    }

    public NetBookValue setSolId(String solId) {

        log.debug("Setting SolId for NetBookValueId : {} as = {}",getId(),solId);
        this.solId = solId;
        return this;
    }

    @Override
    public String toString() {
        return "NetBookValue{" +
                "fixedAssetId=" + fixedAssetId +
                ", month=" + month +
                ", netBookValue=" + netBookValue +
                ", solId='" + solId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NetBookValue that = (NetBookValue) o;
        return fixedAssetId == that.fixedAssetId &&
                Double.compare(that.netBookValue, netBookValue) == 0 &&
                Objects.equals(month, that.month) &&
                Objects.equals(solId, that.solId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixedAssetId, month, netBookValue, solId);
    }
}
