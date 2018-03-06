package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;

import java.time.YearMonth;
import java.util.Objects;

public class UnModifiedNetBookValue {

    private NetBookValue netBookValue;


    public UnModifiedNetBookValue(FixedAsset asset,YearMonth month) {

        netBookValue = new NetBookValue();

        netBookValue
                .setCategory(asset.getCategory())
                .setNetBookValue(asset.getNetBookValue())
                .setMonth(month)
                .setSolId(asset.getSolId())
                .setFixedAssetId(asset.getId());
    }

    public NetBookValue getNetBookValue() {
        return netBookValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnModifiedNetBookValue that = (UnModifiedNetBookValue) o;
        return Objects.equals(netBookValue, that.netBookValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netBookValue);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UnModifiedNetBookValue{");
        sb.append("netBookValue=").append(netBookValue);
        sb.append('}');
        return sb.toString();
    }
}
