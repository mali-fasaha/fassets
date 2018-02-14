package io.github.fasset.fasset.kernel.batch.depreciation.model;

import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Update;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;
import io.github.fasset.fasset.kernel.util.DepreciationUpdatesException;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.nil.NilFixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.YearMonth;
import java.util.Objects;

public class DepreciationUpdate implements Update<DepreciationUpdate> {

    private static final Logger log = LoggerFactory.getLogger(DepreciationUpdate.class);

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
    private boolean fixedAssetProcessed = false;
    private Object destination;


    @Override
    public DepreciationUpdate getPayload() {
        log.debug("Returning Depreciation update : {}",this);
        return this;
    }

    @Override
    public void load(DepreciationUpdate payload) {
        log.debug("Loading update with data : {}",payload);
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
        this.fixedAssetProcessed = payload.isFixedAssetProcessed();
        log.debug("Data loaded : {}",this);
    }

    public YearMonth getPeriod() {
        log.debug("Returning period : {}",period);
        return period == null ? YearMonth.now() : period;
    }

    public String getCategory() {
        log.debug("Returning category : {}",category);
        return category == null ? "" : category;
    }

    public String getSolId() {
        log.debug("Returing solId : {}",solId);
        return solId == null ? "998" : solId;
    }

    public int getFixedAssetId() {
        log.debug("Returning fixedAssetId : {}",fixedAsset);
        return fixedAssetId;
    }

    public double getAccruedDepreciation() {
        log.debug("Returning accruedDepreciation : {}",accruedDepreciation);
        return accruedDepreciation;
    }

    public double getNetBookValue() {
        log.debug("Returning the netBookValue : {}",netBookValue);
        return netBookValue;
    }

    public FixedAsset getFixedAsset() {
        log.debug("Returning fixedAsset item : {}",fixedAsset);
        return fixedAsset == null ? new NilFixedAsset() : fixedAsset;
    }

    public Colleague getSentBy() {
        log.debug("Returning sentBy : {}",sentBy);
        return sentBy;
    }

    public Colleague getReceivedBy() {
        log.debug("Returning receivedBy : {}",receivedBy);
        return receivedBy;
    }

    public boolean isNbvProcessed() {
        log.debug("Returning isNbvProcessed : {}",nbvProcessed);
        return nbvProcessed;
    }

    public boolean isAccruedDepreciationProcessed() {
        log.debug("Returning isAccruedDepreciationProcessed : {}",accruedDepreciation);
        return accruedDepreciationProcessed;
    }

    public boolean isFixedAssetProcessed() {
        log.debug("Returning is fixedAssetProcessed : {}", fixedAssetProcessed);
        return fixedAssetProcessed;
    }

    public DepreciationUpdate setPeriod(YearMonth period) {
        log.debug("Setting the period as: {}",period);
        this.period = period;
        return this;
    }

    public DepreciationUpdate setCategory(String category) {
        log.debug("Setting the category as : {}",category);
        this.category = category;
        return this;
    }

    public DepreciationUpdate setSolId(String solId) {
        log.debug("Setting the solId as : {}",solId);
        this.solId = solId;
        return this;
    }

    public DepreciationUpdate setFixedAssetId(int fixedAssetId) {
        log.debug("Setting the fixedAssetId as : {}",fixedAssetId);
        this.fixedAssetId = fixedAssetId;
        return this;
    }

    public DepreciationUpdate setAccruedDepreciation(double accruedDepreciation) {
        log.debug("Setting the accruedDepreciation as : {}",accruedDepreciation);
        this.accruedDepreciation = accruedDepreciation;
        return this;
    }

    public DepreciationUpdate setNetBookValue(double netBookValue) {
        log.debug("Setting the netBookValue as : {}",netBookValue);
        this.netBookValue = netBookValue;
        return this;
    }

    public DepreciationUpdate setFixedAsset(FixedAsset fixedAsset) {
        log.debug("Setting the fixedAsset item : {}",fixedAsset);
        this.fixedAsset = fixedAsset;
        return this;
    }

    public DepreciationUpdate setSentBy(Colleague sentBy) {
        log.debug("Setting sentBy as : {}",sentBy);
        this.sentBy = sentBy;
        return this;
    }

    public DepreciationUpdate setReceivedBy(Colleague receivedBy) {
        log.debug("Setting receivedBy as : {}",receivedBy);
        this.receivedBy = receivedBy;
        return this;
    }

    public DepreciationUpdate setNbvProcessed(boolean nbvProcessed) {
        log.debug("Setting isNbvProcessed as : {}",nbvProcessed);
        this.nbvProcessed = nbvProcessed;
        return this;
    }

    public DepreciationUpdate setAccruedDepreciationProcessed(boolean accruedDepreciationProcessed) {
        log.debug("Setting isAccruedDepreciationProcessed as : {}",accruedDepreciationProcessed);
        this.accruedDepreciationProcessed = accruedDepreciationProcessed;
        return this;
    }

    public DepreciationUpdate setFixedAssetProcessed(boolean fixedAssetProcessed) {
        log.debug("Setting isFixedAssetProcessed as : {}",fixedAssetProcessed);
        this.fixedAssetProcessed = fixedAssetProcessed;
        return this;
    }

    public AccruedDepreciationDto getAccruedDepreciationItem() {

        log.debug("Deriving accruedDepreciationItem from : {}",this);

        AccruedDepreciationDto accruedDepreciationDto = null;

        try {
            accruedDepreciationDto = new AccruedDepreciationDto(period.getMonthValue(),period.getYear(),solId,category,fixedAssetId,accruedDepreciation);
        } catch (Throwable e) {
            String errorMessage = String.format("Exception encountered while deriving accruedDepreciation item from : %s",this);
            throw new DepreciationUpdatesException(errorMessage,e);
        }

        log.debug("Returning accruedDepreciationDto : {}",accruedDepreciationDto);

        return accruedDepreciationDto;
    }

    public FixedAssetDto getFixedAssetItem() {

        log.debug("Deriving fixedAssetDto from : {}",this);
        FixedAssetDto fixedAssetDto = null;

        try {
            fixedAssetDto = new FixedAssetDto(fixedAsset);
        } catch (Throwable e) {
            String errorMessage = String.format("Exception encountered while deriving fixedAssetDto item from %s",this);
            throw new DepreciationUpdatesException(errorMessage,e);
        }
        log.debug("Returning fixedAssetDto : {}",fixedAssetDto);

        return fixedAssetDto;
    }

    public NetBookValueDto getNetBookValueItem() {

        log.debug("Deriving netBookValue item from : {}",this);
        NetBookValueDto netBookValueDto = null;

        try {
            netBookValueDto = new NetBookValueDto(fixedAssetId,period.getMonthValue(),period.getYear(),netBookValue,solId,category);
        } catch (Throwable e) {
           String errorMessage = String.format("Exception encountered while deriving netBookValueDto item from : %s",this);
           throw new DepreciationUpdatesException(errorMessage,e);
        }

        log.debug("Returning netBookValueDto : {}",netBookValueDto);

        return netBookValueDto;
    }

    @Override
    public Object getDestination() {
        return destination;
    }

    public DepreciationUpdate setDestination(Class destination) {
        this.destination = destination;
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
                fixedAssetProcessed == that.fixedAssetProcessed &&
                Objects.equals(period, that.period) &&
                Objects.equals(category, that.category) &&
                Objects.equals(solId, that.solId) &&
                Objects.equals(fixedAsset, that.fixedAsset) &&
                Objects.equals(sentBy, that.sentBy) &&
                Objects.equals(receivedBy, that.receivedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, category, solId, fixedAssetId, accruedDepreciation, netBookValue, fixedAsset, sentBy, receivedBy, nbvProcessed, accruedDepreciationProcessed, fixedAssetProcessed);
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
        sb.append(", fixedAssetProcessed=").append(fixedAssetProcessed);
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
        this.fixedAssetProcessed = false;

        return this;
    }

    public static class from extends DepreciationUpdate implements Update<DepreciationUpdate> {

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
            super.fixedAssetProcessed = false;
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
            super.fixedAssetProcessed = false;
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
            super.fixedAssetProcessed = false;
        }

        public from(FixedAsset fixedAsset) {
            super.period=null;
            super.category=fixedAsset.getCategory();
            super.solId=fixedAsset.getSolId();
            super.fixedAssetId=fixedAsset.getId();
            super.accruedDepreciation=0.00;
            super.netBookValue=fixedAsset.getNetBookValue();
            super.fixedAsset=fixedAsset;
            super.sentBy=null;
            super.receivedBy=null;
            super.nbvProcessed =false;
            super.accruedDepreciationProcessed=false;
            super.fixedAssetProcessed = false;
        }

        @Override
        public DepreciationUpdate getPayload() {
            return super.getPayload();
        }

        @Override
        public void load(DepreciationUpdate payload) {
            super.load(payload);
        }

        public DepreciationUpdate setSentBy(Colleague sentBy) {
            super.sentBy = sentBy;
            return super.getPayload();
        }
    }
}
