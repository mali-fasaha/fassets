package io.github.fasset.fasset.kernel.batch.depreciation.model;

import java.util.Objects;

public class MonthlySolDepreciationDTO {

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
        return solId;
    }

    public MonthlySolDepreciationDTO setSolId(String solId) {
        this.solId = solId;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public MonthlySolDepreciationDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Double getJan() {
        return jan;
    }

    public MonthlySolDepreciationDTO setJan(Double jan) {
        this.jan = jan;
        return this;
    }

    public Double getFeb() {
        return feb;
    }

    public MonthlySolDepreciationDTO setFeb(Double feb) {
        this.feb = feb;
        return this;
    }

    public Double getMar() {
        return mar;
    }

    public MonthlySolDepreciationDTO setMar(Double mar) {
        this.mar = mar;
        return this;
    }

    public Double getApr() {
        return apr;
    }

    public MonthlySolDepreciationDTO setApr(Double apr) {
        this.apr = apr;
        return this;
    }

    public Double getMay() {
        return may;
    }

    public MonthlySolDepreciationDTO setMay(Double may) {
        this.may = may;
        return this;
    }

    public Double getJun() {
        return jun;
    }

    public MonthlySolDepreciationDTO setJun(Double jun) {
        this.jun = jun;
        return this;
    }

    public Double getJul() {
        return jul;
    }

    public MonthlySolDepreciationDTO setJul(Double jul) {
        this.jul = jul;
        return this;
    }

    public Double getAug() {
        return aug;
    }

    public MonthlySolDepreciationDTO setAug(Double aug) {
        this.aug = aug;
        return this;
    }

    public Double getSep() {
        return sep;
    }

    public MonthlySolDepreciationDTO setSep(Double sep) {
        this.sep = sep;
        return this;
    }

    public Double getOct() {
        return oct;
    }

    public MonthlySolDepreciationDTO setOct(Double oct) {
        this.oct = oct;
        return this;
    }

    public Double getNov() {
        return nov;
    }

    public MonthlySolDepreciationDTO setNov(Double nov) {
        this.nov = nov;
        return this;
    }

    public Double getDec() {
        return dec;
    }

    public MonthlySolDepreciationDTO setDec(Double dec) {
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
