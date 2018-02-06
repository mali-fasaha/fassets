package io.github.fasset.fasset.model;

import io.github.fasset.fasset.DomainModel;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.YearMonth;
import java.util.Map;
import java.util.Objects;

/**
 * Record of the net book value for a gived fixed asset at a given month after previous month's depreciation
 *
 * @author edwin.njeru
 */
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "fixed_asset_id","month","net_book_value","sol_id","category"
        })
})
@Entity(name="NetBookValue")
@Audited
public class NetBookValue extends DomainModel<String>{

    private static final Logger log = LoggerFactory.getLogger(NetBookValue.class);

    @Column(name="fixed_asset_id")
    private int fixedAssetId;

    @Column(name="month")
    private YearMonth month;

    @Column(name="net_book_value")
    private double netBookValue;

    @Column(name="sol_id")
    private String solId;

    @Column(name="category")
    private String category;

    public NetBookValue() {
    }

    public String getCategory() {
        return category;
    }

    public NetBookValue setCategory(String category) {
        log.trace("Setting fixedAssetId for NetBookValueId : {}, as = {}",getId(),fixedAssetId);
        this.category = category;
        return this;
    }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public NetBookValue setFixedAssetId(int fixedAssetId) {
        log.trace("Setting fixedAssetId for NetBookValueId : {}, as = {}",getId(),fixedAssetId);
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    public YearMonth getMonth() {
        return month;
    }

    public NetBookValue setMonth(YearMonth month) {
        log.trace("Setting month for NetBookValueId : {}, as = {}",getId(),month);
        this.month = month;
        return this;
    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public NetBookValue setNetBookValue(double netBookValue) {

        log.trace("Setting NetBookValue for NetBookValueId : {} as = {}",getId(),netBookValue);
        this.netBookValue = netBookValue;
        return this;
    }

    public String getSolId() {
        return solId;
    }

    public NetBookValue setSolId(String solId) {

        log.trace("Setting SolId for NetBookValueId : {} as = {}",getId(),solId);
        this.solId = solId;
        return this;
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
                Objects.equals(solId, that.solId) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixedAssetId, month, netBookValue, solId, category);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NetBookValue{");
        sb.append("fixedAssetId=").append(fixedAssetId);
        sb.append(", month=").append(month);
        sb.append(", netBookValue=").append(netBookValue);
        sb.append(", solId='").append(solId).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
