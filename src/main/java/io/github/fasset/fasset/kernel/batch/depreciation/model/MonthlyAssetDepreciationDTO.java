package io.github.fasset.fasset.kernel.batch.depreciation.model;

import java.time.YearMonth;
import java.util.Objects;

public class MonthlyAssetDepreciationDTO {

    private int assetId;
    private int year;
    private double jan;
    private double feb;
    private double mar;
    private double apr;
    private double may;
    private double jun;
    private double jul;
    private double aug;
    private double sep;
    private double oct;
    private double nov;
    private double dec;

    public MonthlyAssetDepreciationDTO(int assetId, int year, double jan, double feb, double mar, double apr, double may, double jun, double jul, double aug, double sep, double oct, double nov, double dec) {
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

    public int getAssetId() {
        return assetId;
    }

    public MonthlyAssetDepreciationDTO setAssetId(int assetId) {
        this.assetId = assetId;
        return this;
    }

    public int getYear() {
        return year;
    }

    public MonthlyAssetDepreciationDTO setYear(int year) {
        this.year = year;
        return this;
    }

    public double getJan() {
        return jan;
    }

    public MonthlyAssetDepreciationDTO setJan(double jan) {
        this.jan = jan;
        return this;
    }

    public double getFeb() {
        return feb;
    }

    public MonthlyAssetDepreciationDTO setFeb(double feb) {
        this.feb = feb;
        return this;
    }

    public double getMar() {
        return mar;
    }

    public MonthlyAssetDepreciationDTO setMar(double mar) {
        this.mar = mar;
        return this;
    }

    public double getApr() {
        return apr;
    }

    public MonthlyAssetDepreciationDTO setApr(double apr) {
        this.apr = apr;
        return this;
    }

    public double getMay() {
        return may;
    }

    public MonthlyAssetDepreciationDTO setMay(double may) {
        this.may = may;
        return this;
    }

    public double getJun() {
        return jun;
    }

    public MonthlyAssetDepreciationDTO setJun(double jun) {
        this.jun = jun;
        return this;
    }

    public double getJul() {
        return jul;
    }

    public MonthlyAssetDepreciationDTO setJul(double jul) {
        this.jul = jul;
        return this;
    }

    public double getAug() {
        return aug;
    }

    public MonthlyAssetDepreciationDTO setAug(double aug) {
        this.aug = aug;
        return this;
    }

    public double getSep() {
        return sep;
    }

    public MonthlyAssetDepreciationDTO setSep(double sep) {
        this.sep = sep;
        return this;
    }

    public double getOct() {
        return oct;
    }

    public MonthlyAssetDepreciationDTO setOct(double oct) {
        this.oct = oct;
        return this;
    }

    public double getNov() {
        return nov;
    }

    public MonthlyAssetDepreciationDTO setNov(double nov) {
        this.nov = nov;
        return this;
    }

    public double getDec() {
        return dec;
    }

    public MonthlyAssetDepreciationDTO setDec(double dec) {
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
