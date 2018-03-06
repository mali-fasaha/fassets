package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;

import java.time.YearMonth;
import java.util.Objects;

public class UnModifiedAccruedDepreciation {

    private AccruedDepreciation accruedDepreciation;

    public UnModifiedAccruedDepreciation(FixedAsset asset, YearMonth month) {

        accruedDepreciation = new AccruedDepreciation()
                .setAccruedDepreciation(asset.getPurchaseCost()-asset.getNetBookValue())
                .setCategory(asset.getCategory())
                .setFixedAssetId(asset.getId())
                .setMonth(month)
                .setSolId(asset.getSolId());
    }

    public AccruedDepreciation getAccruedDepreciation() {
        return accruedDepreciation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnModifiedAccruedDepreciation that = (UnModifiedAccruedDepreciation) o;
        return Objects.equals(accruedDepreciation, that.accruedDepreciation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accruedDepreciation);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UnModifiedAccruedDepreciation{");
        sb.append("accruedDepreciation=").append(accruedDepreciation);
        sb.append('}');
        return sb.toString();
    }
}
