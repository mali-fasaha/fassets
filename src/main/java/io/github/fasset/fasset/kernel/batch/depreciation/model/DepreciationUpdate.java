package io.github.fasset.fasset.kernel.batch.depreciation.model;

import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Update;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.nil.NilFixedAsset;

import java.time.YearMonth;
import java.util.Objects;

public class DepreciationUpdate implements Update<DepreciationUpdate> {

    private YearMonth period;
    private String category;
    private String solId;
    private int fixedAssetId;
    private double accruedDepreciation;
    private double netBookValue;
    private FixedAsset fixedAsset;
    private Colleague sentBy;
    private Colleague receivedBy;
    private boolean nbvProcessed = false;
    private boolean accruedDepreciationProcessed = false;
    private boolean fixedAssetProcessedProcessed = false;


    @Override
    public DepreciationUpdate getPayload() {
        return this;
    }

    @Override
    public void load(DepreciationUpdate payload) {
        this.period=payload.getPeriod();
        this.category=payload.getCategory();
        this.solId=payload.getSolId();
        this.fixedAssetId=payload.getFixedAssetId();
        this.accruedDepreciation=payload.getAccruedDepreciation();
        this.netBookValue=payload.getNetBookValue();
        this.fixedAsset=payload.getFixedAsset();
        this.sentBy=payload.getSentBy();
        this.receivedBy=payload.getReceivedBy();
        this.nbvProcessed =payload.isNbvProcessed();
        this.accruedDepreciationProcessed=payload.isAccruedDepreciationProcessed();
        this.fixedAssetProcessedProcessed = payload.isFixedAssetProcessedProcessed();
    }

    public YearMonth getPeriod() {
        return period == null ? YearMonth.now() : period;
    }

    public String getCategory() {
        return category == null ? "" : category;
    }

    public String getSolId() {
        return solId == null ? "998" : solId;
    }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public double getAccruedDepreciation() {
        return accruedDepreciation;
    }

    public double getNetBookValue() {
        return netBookValue;
    }

    public FixedAsset getFixedAsset() {
        return fixedAsset == null ? new NilFixedAsset() : fixedAsset;
    }

    public Colleague getSentBy() {
        return sentBy;
    }

    public Colleague getReceivedBy() {
        return receivedBy;
    }

    public boolean isNbvProcessed() {
        return nbvProcessed;
    }

    public boolean isAccruedDepreciationProcessed() {
        return accruedDepreciationProcessed;
    }

    public boolean isFixedAssetProcessedProcessed() {
        return fixedAssetProcessedProcessed;
    }

    public DepreciationUpdate setPeriod(YearMonth period) {
        this.period = period;
        return this;
    }

    public DepreciationUpdate setCategory(String category) {
        this.category = category;
        return this;
    }

    public DepreciationUpdate setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    public DepreciationUpdate setFixedAssetId(int fixedAssetId) {
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    public DepreciationUpdate setAccruedDepreciation(double accruedDepreciation) {
        this.accruedDepreciation = accruedDepreciation;
        return this;
    }

    public DepreciationUpdate setNetBookValue(double netBookValue) {
        this.netBookValue = netBookValue;
        return this;
    }

    public DepreciationUpdate setFixedAsset(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
        return this;
    }

    public DepreciationUpdate setSentBy(Colleague sentBy) {
        this.sentBy = sentBy;
        return this;
    }

    public DepreciationUpdate setReceivedBy(Colleague receivedBy) {
        this.receivedBy = receivedBy;
        return this;
    }

    public DepreciationUpdate setNbvProcessed(boolean nbvProcessed) {
        this.nbvProcessed = nbvProcessed;
        return this;
    }

    public DepreciationUpdate setAccruedDepreciationProcessed(boolean accruedDepreciationProcessed) {
        this.accruedDepreciationProcessed = accruedDepreciationProcessed;
        return this;
    }

    public DepreciationUpdate setFixedAssetProcessedProcessed(boolean fixedAssetProcessedProcessed) {
        this.fixedAssetProcessedProcessed = fixedAssetProcessedProcessed;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepreciationUpdate that = (DepreciationUpdate) o;
        return fixedAssetId == that.fixedAssetId &&
                Double.compare(that.accruedDepreciation, accruedDepreciation) == 0 &&
                Double.compare(that.netBookValue, netBookValue) == 0 &&
                nbvProcessed == that.nbvProcessed &&
                accruedDepreciationProcessed == that.accruedDepreciationProcessed &&
                fixedAssetProcessedProcessed == that.fixedAssetProcessedProcessed &&
                Objects.equals(period, that.period) &&
                Objects.equals(category, that.category) &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(fixedAsset, that.fixedAsset) &&
                Objects.equals(sentBy, that.sentBy) &&
                Objects.equals(receivedBy, that.receivedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, category, solId, fixedAssetId, accruedDepreciation, netBookValue, fixedAsset, sentBy, receivedBy, nbvProcessed, accruedDepreciationProcessed, fixedAssetProcessedProcessed);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DepreciationUpdate{");
        sb.append("period=").append(period);
        sb.append(", category='").append(category).append('\'');
        sb.append(", solId='").append(solId).append('\'');
        sb.append(", fixedAssetId=").append(fixedAssetId);
        sb.append(", accruedDepreciation=").append(accruedDepreciation);
        sb.append(", netBookValue=").append(netBookValue);
        sb.append(", fixedAsset=").append(fixedAsset);
        sb.append(", sentBy=").append(sentBy);
        sb.append(", receivedBy=").append(receivedBy);
        sb.append(", nbvProcessed=").append(nbvProcessed);
        sb.append(", accruedDepreciationProcessed=").append(accruedDepreciationProcessed);
        sb.append(", fixedAssetProcessedProcessed=").append(fixedAssetProcessedProcessed);
        sb.append('}');
        return sb.toString();
    }

    public DepreciationUpdate from(NetBookValueDto netBookValueDto) {
        this.period=netBookValueDto.getPayload().getMonth();
        this.category=netBookValueDto.getPayload().getCategory();
        this.solId=netBookValueDto.getPayload().getSolId();
        this.fixedAssetId=netBookValueDto.getPayload().getFixedAssetId();
        this.accruedDepreciation=0.00;
        this.netBookValue=netBookValueDto.getPayload().getNetBookValue();
        this.fixedAsset=null;
        this.sentBy=null;
        this.receivedBy=null;
        this.nbvProcessed =false;
        this.accruedDepreciationProcessed=false;
        this.fixedAssetProcessedProcessed = false;

        return this;
    }

    public static class from extends DepreciationUpdate implements Update<DepreciationUpdate> {

        private YearMonth period;
        private String category;
        private String solId;
        private int fixedAssetId;
        private double accruedDepreciation;
        private double netBookValue;
        private FixedAsset fixedAsset;
        private Colleague sentBy;
        private Colleague receivedBy;
        private boolean nbvProcessed = false;
        private boolean accruedDepreciationProcessed = false;
        private boolean fixedAssetProcessedProcessed = false;

        public from(FixedAssetDto fixedAssetDto) {
            super.period=null;
            super.category=fixedAssetDto.getPayload().getCategory();
            super.solId=fixedAssetDto.getPayload().getSolId();
            super.fixedAssetId=fixedAssetDto.getPayload().getId();
            super.accruedDepreciation=0.00;
            super.netBookValue=fixedAssetDto.getPayload().getNetBookValue();
            super.fixedAsset=fixedAssetDto.getPayload();
            super.sentBy=null;
            super.receivedBy=null;
            super.nbvProcessed =false;
            super.accruedDepreciationProcessed=false;
            super.fixedAssetProcessedProcessed = false;
        }

        public from(AccruedDepreciationDto accruedDepreciationDto) {
            super.period=accruedDepreciationDto.getPayload().getMonth();
            super.category=accruedDepreciationDto.getPayload().getCategory();
            super.solId=accruedDepreciationDto.getPayload().getSolId();
            super.fixedAssetId=accruedDepreciationDto.getPayload().getFixedAssetId();
            super.accruedDepreciation=accruedDepreciationDto.getPayload().getAccruedDepreciation();
            super.netBookValue=0.00;
            super.fixedAsset=null;
            super.sentBy=null;
            super.receivedBy=null;
            super.nbvProcessed =false;
            super.accruedDepreciationProcessed=false;
            super.fixedAssetProcessedProcessed = false;
        }

        public from(NetBookValueDto netBookValueDto) {
            super.period=netBookValueDto.getPayload().getMonth();
            super.category=netBookValueDto.getPayload().getCategory();
            super.solId=netBookValueDto.getPayload().getSolId();
            super.fixedAssetId=netBookValueDto.getPayload().getFixedAssetId();
            super.accruedDepreciation=0.00;
            super.netBookValue=netBookValueDto.getPayload().getNetBookValue();
            super.fixedAsset=null;
            super.sentBy=null;
            super.receivedBy=null;
            super.nbvProcessed =false;
            super.accruedDepreciationProcessed=false;
            super.fixedAssetProcessedProcessed = false;
        }

        @Override
        public DepreciationUpdate getPayload() {
            return super.getPayload();
        }

        @Override
        public void load(DepreciationUpdate payload) {
            super.load(payload);
        }
    }
}
