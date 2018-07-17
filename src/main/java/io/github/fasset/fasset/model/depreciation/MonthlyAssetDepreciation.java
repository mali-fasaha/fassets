/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.model.depreciation;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * This object represents a record of an Asset's depreciation for all the months of a year
 */
@Entity
@Audited
public class MonthlyAssetDepreciation extends DomainModel<String> {

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

    public MonthlyAssetDepreciation(int assetId, int year, double jan, double feb, double mar, double apr, double may, double jun, double jul, double aug, double sep, double oct, double nov,
                                    double dec) {
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

    public MonthlyAssetDepreciation() {
    }

    public int getAssetId() {
        return assetId;
    }

    public MonthlyAssetDepreciation setAssetId(int assetId) {
        this.assetId = assetId;
        return this;
    }

    public int getYear() {
        return year;
    }

    public MonthlyAssetDepreciation setYear(int year) {
        this.year = year;
        return this;
    }

    public double getJan() {
        return jan;
    }

    public MonthlyAssetDepreciation setJan(double jan) {
        this.jan = jan;
        return this;
    }

    public double getFeb() {
        return feb;
    }

    public MonthlyAssetDepreciation setFeb(double feb) {
        this.feb = feb;
        return this;
    }

    public double getMar() {
        return mar;
    }

    public MonthlyAssetDepreciation setMar(double mar) {
        this.mar = mar;
        return this;
    }

    public double getApr() {
        return apr;
    }

    public MonthlyAssetDepreciation setApr(double apr) {
        this.apr = apr;
        return this;
    }

    public double getMay() {
        return may;
    }

    public MonthlyAssetDepreciation setMay(double may) {
        this.may = may;
        return this;
    }

    public double getJun() {
        return jun;
    }

    public MonthlyAssetDepreciation setJun(double jun) {
        this.jun = jun;
        return this;
    }

    public double getJul() {
        return jul;
    }

    public MonthlyAssetDepreciation setJul(double jul) {
        this.jul = jul;
        return this;
    }

    public double getAug() {
        return aug;
    }

    public MonthlyAssetDepreciation setAug(double aug) {
        this.aug = aug;
        return this;
    }

    public double getSep() {
        return sep;
    }

    public MonthlyAssetDepreciation setSep(double sep) {
        this.sep = sep;
        return this;
    }

    public double getOct() {
        return oct;
    }

    public MonthlyAssetDepreciation setOct(double oct) {
        this.oct = oct;
        return this;
    }

    public double getNov() {
        return nov;
    }

    public MonthlyAssetDepreciation setNov(double nov) {
        this.nov = nov;
        return this;
    }

    public double getDec() {
        return dec;
    }

    public MonthlyAssetDepreciation setDec(double dec) {
        this.dec = dec;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MonthlyAssetDepreciation that = (MonthlyAssetDepreciation) o;
        return assetId == that.assetId && year == that.year && Double.compare(that.jan, jan) == 0 && Double.compare(that.feb, feb) == 0 && Double.compare(that.mar, mar) == 0 &&
            Double.compare(that.apr, apr) == 0 && Double.compare(that.may, may) == 0 && Double.compare(that.jun, jun) == 0 && Double.compare(that.jul, jul) == 0 &&
            Double.compare(that.aug, aug) == 0 && Double.compare(that.sep, sep) == 0 && Double.compare(that.oct, oct) == 0 && Double.compare(that.nov, nov) == 0 && Double.compare(that.dec, dec) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assetId, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }

    @Override
    public String toString() {
        return MessageFormat.format("MonthlyAssetDepreciation'{'assetId={0}, year={1}, jan={2}, feb={3}, mar={4}, apr={5}, may={6}, jun={7}, jul={8}, aug={9}, sep={10}, oct={11}, nov={12}, dec={13}'}'",
                                assetId, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }
}
