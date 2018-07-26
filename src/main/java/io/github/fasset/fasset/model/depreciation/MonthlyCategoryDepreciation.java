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
 * This object represents a record of a Category's depreciation for all the months of a year
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Entity
@Audited
public class MonthlyCategoryDepreciation extends DomainModel<String> {

    private String categoryName;
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

    /**
     * <p>Constructor for MonthlyCategoryDepreciation.</p>
     *
     * @param categoryName a {@link java.lang.String} object.
     * @param year a int.
     * @param jan a double.
     * @param feb a double.
     * @param mar a double.
     * @param apr a double.
     * @param may a double.
     * @param jun a double.
     * @param jul a double.
     * @param aug a double.
     * @param sep a double.
     * @param oct a double.
     * @param nov a double.
     * @param dec a double.
     */
    public MonthlyCategoryDepreciation(String categoryName, int year, double jan, double feb, double mar, double apr, double may, double jun, double jul, double aug, double sep, double oct,
                                       double nov, double dec) {
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

    /**
     * <p>Constructor for MonthlyCategoryDepreciation.</p>
     */
    public MonthlyCategoryDepreciation() {
    }

    /**
     * <p>Getter for the field <code>categoryName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCategoryName() {
        return categoryName == null ? "Category" : categoryName;
    }

    /**
     * <p>Setter for the field <code>categoryName</code>.</p>
     *
     * @param categoryName a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    /**
     * <p>Getter for the field <code>year</code>.</p>
     *
     * @return a int.
     */
    public int getYear() {
        return year;
    }

    /**
     * <p>Setter for the field <code>year</code>.</p>
     *
     * @param year a int.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * <p>Getter for the field <code>jan</code>.</p>
     *
     * @return a double.
     */
    public double getJan() {
        return jan;
    }

    /**
     * <p>Setter for the field <code>jan</code>.</p>
     *
     * @param jan a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setJan(double jan) {
        this.jan = jan;
        return this;
    }

    /**
     * <p>Getter for the field <code>feb</code>.</p>
     *
     * @return a double.
     */
    public double getFeb() {
        return feb;
    }

    /**
     * <p>Setter for the field <code>feb</code>.</p>
     *
     * @param feb a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setFeb(double feb) {
        this.feb = feb;
        return this;
    }

    /**
     * <p>Getter for the field <code>mar</code>.</p>
     *
     * @return a double.
     */
    public double getMar() {
        return mar;
    }

    /**
     * <p>Setter for the field <code>mar</code>.</p>
     *
     * @param mar a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setMar(double mar) {
        this.mar = mar;
        return this;
    }

    /**
     * <p>Getter for the field <code>apr</code>.</p>
     *
     * @return a double.
     */
    public double getApr() {
        return apr;
    }

    /**
     * <p>Setter for the field <code>apr</code>.</p>
     *
     * @param apr a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setApr(double apr) {
        this.apr = apr;
        return this;
    }

    /**
     * <p>Getter for the field <code>may</code>.</p>
     *
     * @return a double.
     */
    public double getMay() {
        return may;
    }

    /**
     * <p>Setter for the field <code>may</code>.</p>
     *
     * @param may a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setMay(double may) {
        this.may = may;
        return this;
    }

    /**
     * <p>Getter for the field <code>jun</code>.</p>
     *
     * @return a double.
     */
    public double getJun() {
        return jun;
    }

    /**
     * <p>Setter for the field <code>jun</code>.</p>
     *
     * @param jun a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setJun(double jun) {
        this.jun = jun;
        return this;
    }

    /**
     * <p>Getter for the field <code>jul</code>.</p>
     *
     * @return a double.
     */
    public double getJul() {
        return jul;
    }

    /**
     * <p>Setter for the field <code>jul</code>.</p>
     *
     * @param jul a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setJul(double jul) {
        this.jul = jul;
        return this;
    }

    /**
     * <p>Getter for the field <code>aug</code>.</p>
     *
     * @return a double.
     */
    public double getAug() {
        return aug;
    }

    /**
     * <p>Setter for the field <code>aug</code>.</p>
     *
     * @param aug a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setAug(double aug) {
        this.aug = aug;
        return this;
    }

    /**
     * <p>Getter for the field <code>sep</code>.</p>
     *
     * @return a double.
     */
    public double getSep() {
        return sep;
    }

    /**
     * <p>Setter for the field <code>sep</code>.</p>
     *
     * @param sep a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setSep(double sep) {
        this.sep = sep;
        return this;
    }

    /**
     * <p>Getter for the field <code>oct</code>.</p>
     *
     * @return a double.
     */
    public double getOct() {
        return oct;
    }

    /**
     * <p>Setter for the field <code>oct</code>.</p>
     *
     * @param oct a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setOct(double oct) {
        this.oct = oct;
        return this;
    }

    /**
     * <p>Getter for the field <code>nov</code>.</p>
     *
     * @return a double.
     */
    public double getNov() {
        return nov;
    }

    /**
     * <p>Setter for the field <code>nov</code>.</p>
     *
     * @param nov a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setNov(double nov) {
        this.nov = nov;
        return this;
    }

    /**
     * <p>Getter for the field <code>dec</code>.</p>
     *
     * @return a double.
     */
    public double getDec() {
        return dec;
    }

    /**
     * <p>Setter for the field <code>dec</code>.</p>
     *
     * @param dec a double.
     * @return a {@link io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation} object.
     */
    public MonthlyCategoryDepreciation setDec(double dec) {
        this.dec = dec;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MonthlyCategoryDepreciation that = (MonthlyCategoryDepreciation) o;
        return year == that.year && Double.compare(that.jan, jan) == 0 && Double.compare(that.feb, feb) == 0 && Double.compare(that.mar, mar) == 0 && Double.compare(that.apr, apr) == 0 &&
            Double.compare(that.may, may) == 0 && Double.compare(that.jun, jun) == 0 && Double.compare(that.jul, jul) == 0 && Double.compare(that.aug, aug) == 0 &&
            Double.compare(that.sep, sep) == 0 && Double.compare(that.oct, oct) == 0 && Double.compare(that.nov, nov) == 0 && Double.compare(that.dec, dec) == 0 &&
            Objects.equals(categoryName, that.categoryName);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(categoryName, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return MessageFormat.format(
            "MonthlyCategoryDepreciation'{'categoryName=''{0}'', year={1}, jan={2}, feb={3}, mar={4}, apr={5}, may={6}, jun={7}, jul={8}, aug={9}, sep={10}, oct={11}, nov={12}, dec={13}'}'",
            categoryName, year, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec);
    }
}
