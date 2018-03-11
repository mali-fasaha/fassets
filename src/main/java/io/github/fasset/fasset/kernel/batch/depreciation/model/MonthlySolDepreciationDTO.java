package io.github.fasset.fasset.kernel.batch.depreciation.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.money.MonetaryAmount;

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

    public MonthlySolDepreciationDTO(String solId, Integer year, MonetaryAmount jan,  MonetaryAmount feb,  MonetaryAmount mar,  MonetaryAmount apr,  MonetaryAmount may,  MonetaryAmount jun,  MonetaryAmount jul,  MonetaryAmount aug,  MonetaryAmount sep, MonetaryAmount oct, MonetaryAmount nov, MonetaryAmount dec) {
        this.solId = solId;
        this.year = year;
        this.jan = jan.getNumber().doubleValue();
        this.feb = feb.getNumber().doubleValue();
        this.mar = mar.getNumber().doubleValue();
        this.apr = apr.getNumber().doubleValue();
        this.may = may.getNumber().doubleValue();
        this.jun = jun.getNumber().doubleValue();
        this.jul = jul.getNumber().doubleValue();
        this.aug = aug.getNumber().doubleValue();
        this.sep = sep.getNumber().doubleValue();
        this.oct = oct.getNumber().doubleValue();
        this.nov = nov.getNumber().doubleValue();
        this.dec = dec.getNumber().doubleValue();
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

    public double getJan() {
        log.trace("Returning jan : {}",jan);
        return jan == null ? 0 : jan;
    }

    public MonthlySolDepreciationDTO setJan(double jan) {
        log.trace("Setting jan : {}",jan);
        this.jan = jan;
        return this;
    }

    public double getFeb() {
        log.trace("Returning feb : {}",feb);
        return feb == null ? 0 : feb;
    }

    public MonthlySolDepreciationDTO setFeb(double feb) {
        log.trace("Setting feb : {}",feb);
        this.feb = feb;
        return this;
    }

    public double getMar() {
        log.trace("Returning mar : {}",mar);
        return mar == null ? 0 : mar;
    }

    public MonthlySolDepreciationDTO setMar(double mar) {
        log.trace("Setting mar : {}",mar);
        this.mar = mar;
        return this;
    }

    public double getApr() {
        log.trace("Returning apr : {}",apr);
        return apr == null ? 0 : apr;
    }

    public MonthlySolDepreciationDTO setApr(double apr) {
        log.trace("Setting apr : {}",apr);
        this.apr = apr;
        return this;
    }

    public double getMay() {
        log.trace("Returning may : {}",may);
        return may;
    }

    public MonthlySolDepreciationDTO setMay(double may) {
        log.trace("Setting may : {}",may);
        this.may = may;
        return this;
    }

    public double getJun() {
        log.trace("Returning jun : {}",jun);
        return jun == null ? 0 : jun;
    }

    public MonthlySolDepreciationDTO setJun(double jun) {
        log.trace("Setting jun : {}",jun);
        this.jun = jun;
        return this;
    }

    public double getJul() {
        log.trace("Returning jul : {}",jul);
        return jul == null ? 0 : jul;
    }

    public MonthlySolDepreciationDTO setJul(double jul) {
        log.trace("Setting jul : {}",jul);
        this.jul = jul;
        return this;
    }

    public double getAug() {
        log.trace("Returning aug : {}",aug);
        return aug == null ? 0 : aug;
    }

    public MonthlySolDepreciationDTO setAug(double aug) {
        log.trace("Setting aug : {}",aug);
        this.aug = aug;
        return this;
    }

    public double getSep() {
        log.trace("Returning sep : {}",sep);
        return sep == null ? 0 : sep;
    }

    public MonthlySolDepreciationDTO setSep(double sep) {
        log.trace("Setting sep : {}",sep);
        this.sep = sep;
        return this;
    }

    public double getOct() {
        log.trace("Returning oct : {}",oct);
        return oct == null ? 0 : oct;
    }

    public MonthlySolDepreciationDTO setOct(double oct) {
        log.trace("Setting oct : {}",oct);
        this.oct = oct;
        return this;
    }

    public double getNov() {
        log.trace("Returning nov : {}",nov);
        return nov == null ? 0 : nov;
    }

    public MonthlySolDepreciationDTO setNov(double nov) {
        log.trace("Setting nov : {}",nov);
        this.nov = nov;
        return this;
    }

    public double getDec() {
        log.trace("Returning dec : {}",dec);
        return dec == null ? 0 : dec;
    }

    public MonthlySolDepreciationDTO setDec(double dec) {
        log.trace("Setting dec : {}",dec);
        this.dec = dec;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlySolDepreciationDTO that = (MonthlySolDepreciationDTO) o;
        return Objects.equal(solId, that.solId) &&
                Objects.equal(year, that.year) &&
                Objects.equal(jan, that.jan) &&
                Objects.equal(feb, that.feb) &&
                Objects.equal(mar, that.mar) &&
                Objects.equal(apr, that.apr) &&
                Objects.equal(may, that.may) &&
                Objects.equal(jun, that.jun) &&
                Objects.equal(jul, that.jul) &&
                Objects.equal(aug, that.aug) &&
                Objects.equal(sep, that.sep) &&
                Objects.equal(oct, that.oct) &&
                Objects.equal(nov, that.nov) &&
                Objects.equal(dec, that.dec);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(solId, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("solId", solId)
                .add("year", year)
                .add("jan", jan)
                .add("feb", feb)
                .add("mar", mar)
                .add("apr", apr)
                .add("may", may)
                .add("jun", jun)
                .add("jul", jul)
                .add("aug", aug)
                .add("sep", sep)
                .add("oct", oct)
                .add("nov", nov)
                .add("dec", dec)
                .toString();
    }
}
