/**
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.book.keeper.unit.time;

import io.github.fasset.fasset.book.keeper.util.TimePointUtils;

import java.time.format.DateTimeFormatter;

/**
 * Adds datePattern and mechanisms for adding datePatterns to TimePoint interface which enables us to
 * read dates from string using specified patterns
 */
public class ReadableDate extends SimpleDateDecorator implements ReadableTime {

    private String datePattern;

    /*public ReadableDate(String datePattern, TimePoint timePoint) {
        super(timePoint);

    }*/

    public ReadableDate(String datePattern, TimePoint attribute) {
        super(attribute);
        this.datePattern = datePattern;
    }

    /**
     * Reads dateString and return equivalent {@link TimePoint} object
     *
     * @param dateString String from which we are extracting the date
     * @return Equivalent TimePoint for the string
     */
    @Override
    public TimePoint parseString(String dateString) {

        return parseString(dateString, datePattern);
    }

    /**
     * Reads dateString and return equivalent {@link TimePoint} object
     *
     * @param dateString  String from which we are extracting the date
     * @param datePattern Date pattern to be used in parsing the date string argument
     * @return Equivalent TimePoint for the string
     */
    @Override
    public TimePoint parseString(String dateString, String datePattern) {

        return TimePointUtils.parseString(dateString, datePattern);
    }

    @Override
    public String toString() {

        return getDay(this).format(DateTimeFormatter.ofPattern(datePattern));
    }
}
