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
package io.github.fasset.fasset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This object simply configures the default of the program's default date parsing pattern
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component
@ConfigurationProperties(prefix = "date-parsing")
public class DateProperties {

    private String datePattern = "dd/MM/yyyy";

    /**
     * <p>Constructor for DateProperties.</p>
     */
    public DateProperties() {
    }

    /**
     * <p>Constructor for DateProperties.</p>
     *
     * @param datePattern a {@link java.lang.String} object.
     */
    public DateProperties(String datePattern) {
        this.datePattern = datePattern;
    }

    /**
     * <p>Getter for the field <code>datePattern</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDatePattern() {
        return datePattern;
    }

    /**
     * <p>Setter for the field <code>datePattern</code>.</p>
     *
     * @param datePattern a {@link java.lang.String} object.
     */
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "DateProperties{" + "datePattern='" + datePattern + '\'' + '}';
    }
}


