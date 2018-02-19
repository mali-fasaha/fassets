package io.github.fasset.fasset.kernel.batch.depreciation.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MonthlyCategoryDepreciationDTO {

    private static final Logger log = LoggerFactory.getLogger(MonthlyCategoryDepreciationDTO.class);

    private String categoryName;
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

    public MonthlyCategoryDepreciationDTO(String categoryName, Integer year, Double jan, Double feb, Double mar, Double apr, Double may, Double jun, Double jul, Double aug, Double sep, Double oct, Double nov, Double dec) {
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        log.trace("Returning categoryName : {}",categoryName);
        return categoryName;
    }

    public MonthlyCategoryDepreciationDTO setCategoryName(String categoryName) {
        log.trace("Setting categoryName : {}",categoryName);
        this.categoryName = categoryName;
        return this;
    }

    public Integer getYear() {

        log.trace("Returning year : {}",year);
        return year == null ? 0 : year;
    }

    public MonthlyCategoryDepreciationDTO setYear(Integer year) {
        log.trace("Setting year : {}",year);
        this.year = year;
        return this;
    }

    public Double getJan() {
        log.trace("Returning jan : {}",jan);
        return jan == null ? 0 : jan;
    }

    public MonthlyCategoryDepreciationDTO setJan(Double jan) {
        log.trace("Setting jan : {}",jan);
        this.jan = jan;
        return this;
    }

    public Double getFeb() {
        log.trace("Returning feb : {}",feb);
        return feb == null ? 0 : feb;
    }

    public MonthlyCategoryDepreciationDTO setFeb(Double feb) {
        log.trace("Setting feb : {}",feb);
        this.feb = feb;
        return this;
    }

    public Double getMar() {
        log.trace("Returning mar : {}",mar);
        return mar == null ? 0 : mar;
    }

    public MonthlyCategoryDepreciationDTO setMar(Double mar) {
        log.trace("Setting mar : {}",mar);
        this.mar = mar;
        return this;
    }

    public Double getApr() {
        log.trace("Returning apr : {}",apr);
        return apr == null ? 0 : apr;
    }

    public MonthlyCategoryDepreciationDTO setApr(Double apr) {
        log.trace("Setting apr : {}",apr);
        this.apr = apr;
        return this;
    }

    public Double getMay() {
        log.trace("Returning may : {}",may);
        return may;
    }

    public MonthlyCategoryDepreciationDTO setMay(Double may) {
        log.trace("Setting may : {}",may);
        this.may = may;
        return this;
    }

    public Double getJun() {
        log.trace("Returning jun : {}",jun);
        return jun == null ? 0 : jun;
    }

    public MonthlyCategoryDepreciationDTO setJun(Double jun) {
        log.trace("Setting jun : {}",jun);
        this.jun = jun;
        return this;
    }

    public Double getJul() {
        log.trace("Returning jul : {}",jul);
        return jul == null ? 0 : jul;
    }

    public MonthlyCategoryDepreciationDTO setJul(Double jul) {
        log.trace("Setting jul : {}",jul);
        this.jul = jul;
        return this;
    }

    public Double getAug() {
        log.trace("Returning aug : {}",aug);
        return aug == null ? 0 : aug;
    }

    public MonthlyCategoryDepreciationDTO setAug(Double aug) {
        log.trace("Setting aug : {}",aug);
        this.aug = aug;
        return this;
    }

    public Double getSep() {
        log.trace("Returning sep : {}",sep);
        return sep == null ? 0 : sep;
    }

    public MonthlyCategoryDepreciationDTO setSep(Double sep) {
        log.trace("Setting sep : {}",sep);
        this.sep = sep;
        return this;
    }

    public Double getOct() {
        log.trace("Returning oct : {}",oct);
        return oct == null ? 0 : oct;
    }

    public MonthlyCategoryDepreciationDTO setOct(Double oct) {
        log.trace("Setting oct : {}",oct);
        this.oct = oct;
        return this;
    }

    public Double getNov() {
        log.trace("Returning nov : {}",nov);
        return nov == null ? 0 : nov;
    }

    public MonthlyCategoryDepreciationDTO setNov(Double nov) {
        log.trace("Setting nov : {}",nov);
        this.nov = nov;
        return this;
    }

    public Double getDec() {
        log.trace("Returning dec : {}",dec);
        return dec == null ? 0 : dec;
    }

    public MonthlyCategoryDepreciationDTO setDec(Double dec) {
        log.trace("Setting dec : {}", dec);
        this.dec = dec;
        return this;
    }

}
