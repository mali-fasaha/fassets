package io.github.fasset.fasset.kernel.batch.depreciation.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MonthlySolDepreciationDTO {

    private static final Logger log = LoggerFactory.getLogger(MonthlySolDepreciationDTO.class);

    private String solId;
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

    public MonthlySolDepreciationDTO() {
    }

    public MonthlySolDepreciationDTO(String solId, Integer year, Double jan, Double feb, Double mar, Double apr, Double may, Double jun, Double jul, Double aug, Double sep, Double oct, Double nov, Double dec) {
        this.solId = solId;
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

    public String getSolId() {

        log.trace("Returning solId : {}",solId);
        return solId == null ? "" : solId;
    }

    public MonthlySolDepreciationDTO setSolId(String solId) {

        log.trace("Setting solId: {}",solId);
        this.solId = solId;
        return this;
    }

    public Integer getYear() {

        log.trace("Returning year : {}",year);
        return year == null ? 0 : year;
    }

    public MonthlySolDepreciationDTO setYear(Integer year) {
        log.trace("Setting year : {}",year);
        this.year = year;
        return this;
    }

    public Double getJan() {
        log.trace("Returning jan : {}",jan);
        return jan == null ? 0 : jan;
    }

    public MonthlySolDepreciationDTO setJan(Double jan) {
        log.trace("Setting jan : {}",jan);
        this.jan = jan;
        return this;
    }

    public Double getFeb() {
        log.trace("Returning feb : {}",feb);
        return feb == null ? 0 : feb;
    }

    public MonthlySolDepreciationDTO setFeb(Double feb) {
        log.trace("Setting feb : {}",feb);
        this.feb = feb;
        return this;
    }

    public Double getMar() {
        log.trace("Returning mar : {}",mar);
        return mar == null ? 0 : mar;
    }

    public MonthlySolDepreciationDTO setMar(Double mar) {
        log.trace("Setting mar : {}",mar);
        this.mar = mar;
        return this;
    }

    public Double getApr() {
        log.trace("Returning apr : {}",apr);
        return apr == null ? 0 : apr;
    }

    public MonthlySolDepreciationDTO setApr(Double apr) {
        log.trace("Setting apr : {}",apr);
        this.apr = apr;
        return this;
    }

    public Double getMay() {
        log.trace("Returning may : {}",may);
        return may;
    }

    public MonthlySolDepreciationDTO setMay(Double may) {
        log.trace("Setting may : {}",may);
        this.may = may;
        return this;
    }

    public Double getJun() {
        log.trace("Returning jun : {}",jun);
        return jun == null ? 0 : jun;
    }

    public MonthlySolDepreciationDTO setJun(Double jun) {
        log.trace("Setting jun : {}",jun);
        this.jun = jun;
        return this;
    }

    public Double getJul() {
        log.trace("Returning jul : {}",jul);
        return jul == null ? 0 : jul;
    }

    public MonthlySolDepreciationDTO setJul(Double jul) {
        log.trace("Setting jul : {}",jul);
        this.jul = jul;
        return this;
    }

    public Double getAug() {
        log.trace("Returning aug : {}",aug);
        return aug == null ? 0 : aug;
    }

    public MonthlySolDepreciationDTO setAug(Double aug) {
        log.trace("Setting aug : {}",aug);
        this.aug = aug;
        return this;
    }

    public Double getSep() {
        log.trace("Returning sep : {}",sep);
        return sep == null ? 0 : sep;
    }

    public MonthlySolDepreciationDTO setSep(Double sep) {
        log.trace("Setting sep : {}",sep);
        this.sep = sep;
        return this;
    }

    public Double getOct() {
        log.trace("Returning oct : {}",oct);
        return oct == null ? 0 : oct;
    }

    public MonthlySolDepreciationDTO setOct(Double oct) {
        log.trace("Setting oct : {}",oct);
        this.oct = oct;
        return this;
    }

    public Double getNov() {
        log.trace("Returning nov : {}",nov);
        return nov == null ? 0 : nov;
    }

    public MonthlySolDepreciationDTO setNov(Double nov) {
        log.trace("Setting nov : {}",nov);
        this.nov = nov;
        return this;
    }

    public Double getDec() {
        log.trace("Returning dec : {}",dec);
        return dec == null ? 0 : dec;
    }

    public MonthlySolDepreciationDTO setDec(Double dec) {
        log.trace("Setting dec : {}",dec);
        this.dec = dec;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlySolDepreciationDTO that = (MonthlySolDepreciationDTO) o;
        return solId == that.solId &&
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
        return Objects.hash(solId, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MonthlySolDepreciationDTO{");
        sb.append("solId=").append(solId);
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
