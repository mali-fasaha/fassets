package io.github.fasset.fasset.kernel.batch.depreciation.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.YearMonth;
import java.util.Objects;

public class MonthlyAssetDepreciationDTO {

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationDTO.class);

    private Integer assetId;
    private Integer year;
    private Double jan;
    private Double feb;
    private Double mar;
    private Double apr;
    private Double may;
    private Double jun;
    private Double jul;
    private Double aug;
    private Double sep;
    private Double oct;
    private Double nov;
    private Double dec;

    public MonthlyAssetDepreciationDTO() {
    }

    public MonthlyAssetDepreciationDTO(Integer assetId, Integer year, Double jan, Double feb, Double mar, Double apr, Double may, Double jun, Double jul, Double aug, Double sep, Double oct, Double nov, Double dec) {
        this.assetId = assetId;
        this.year = year;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public MonthlyAssetDepreciationDTO setAssetId(Integer assetId) {
        this.assetId = assetId;
        return this;
    }

    public Integer getYear() {

        log.debug("Returning year : {}",year);
        return year==null ? 0 : year;
    }

    public MonthlyAssetDepreciationDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Double getJan() {
        log.debug("Returning jan : {}",jan);
        return jan == null ? 0.00 : jan;
    }

    public MonthlyAssetDepreciationDTO setJan(Double jan) {
        this.jan = jan;
        return this;
    }

    public Double getFeb() {
        log.debug("Returning feb : {}",feb);
        return feb == null ? 0.00 : feb;
    }

    public MonthlyAssetDepreciationDTO setFeb(Double feb) {
        this.feb = feb;
        return this;
    }

    public Double getMar() {
        log.debug("Returning mar : {}",mar);
        return mar == null ? 0.00 : mar;
    }

    public MonthlyAssetDepreciationDTO setMar(Double mar) {
        this.mar = mar;
        return this;
    }

    public Double getApr() {
        log.debug("Returning apr : {}",apr);
        return apr == null ? 0.00 : apr;
    }

    public MonthlyAssetDepreciationDTO setApr(Double apr) {
        this.apr = apr;
        return this;
    }

    public Double getMay() {
        log.debug("Returning may : {}",may);
        return may == null ? 0.00 : may;
    }

    public MonthlyAssetDepreciationDTO setMay(Double may) {
        this.may = may;
        return this;
    }

    public Double getJun() {

        log.debug("Returning jun : {}",jun);
        return jun == null ? 0.00 : jun;
    }

    public MonthlyAssetDepreciationDTO setJun(Double jun) {
        this.jun = jun;
        return this;
    }

    public Double getJul() {
        log.debug("Returning jul : {}",jul);
        return jul == null ? 0.00 : jul;
    }

    public MonthlyAssetDepreciationDTO setJul(Double jul) {
        this.jul = jul;
        return this;
    }

    public Double getAug() {
        log.debug("Returning aug : {}",aug);
        return aug == null ? 0.00 : aug;
    }

    public MonthlyAssetDepreciationDTO setAug(Double aug) {
        this.aug = aug;
        return this;
    }

    public Double getSep() {
        log.debug("Returning sep : {}",sep);
        return sep == null ? 0.00 : sep;
    }

    public MonthlyAssetDepreciationDTO setSep(Double sep) {
        this.sep = sep;
        return this;
    }

    public Double getOct() {

        log.debug("Returning oct : {}",oct);
        return oct == null ? 0.00 : oct;
    }

    public MonthlyAssetDepreciationDTO setOct(Double oct) {
        this.oct = oct;
        return this;
    }

    public Double getNov() {
        log.debug("Returning nov : {}",nov);
        return nov == null ? 0.00 : nov;
    }

    public MonthlyAssetDepreciationDTO setNov(Double nov) {
        this.nov = nov;
        return this;
    }

    public Double getDec() {
        log.debug("Returning dec : {}",dec);
        return dec == null ? 0.00 : dec;
    }

    public MonthlyAssetDepreciationDTO setDec(Double dec) {
        this.dec = dec;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyAssetDepreciationDTO that = (MonthlyAssetDepreciationDTO) o;
        return assetId == that.assetId &&
                year == that.year &&
                Double.compare(that.jan, jan) == 0 &&
                Double.compare(that.feb, feb) == 0 &&
                Double.compare(that.mar, mar) == 0 &&
                Double.compare(that.apr, apr) == 0 &&
                Double.compare(that.may, may) == 0 &&
                Double.compare(that.jun, jun) == 0 &&
                Double.compare(that.jul, jul) == 0 &&
                Double.compare(that.aug, aug) == 0 &&
                Double.compare(that.sep, sep) == 0 &&
                Double.compare(that.oct, oct) == 0 &&
                Double.compare(that.nov, nov) == 0 &&
                Double.compare(that.dec, dec) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MonthlyAssetDepreciationDTO{");
        sb.append("assetId=").append(assetId);
        sb.append(", year=").append(year);
        sb.append(", jan=").append(jan);
        sb.append(", feb=").append(feb);
        sb.append(", mar=").append(mar);
        sb.append(", apr=").append(apr);
        sb.append(", may=").append(may);
        sb.append(", jun=").append(jun);
        sb.append(", jul=").append(jul);
        sb.append(", aug=").append(aug);
        sb.append(", sep=").append(sep);
        sb.append(", oct=").append(oct);
        sb.append(", nov=").append(nov);
        sb.append(", dec=").append(dec);
        sb.append('}');
        return sb.toString();
    }
}
