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
package io.github.fasset.fasset.kernel.util.convert;

import io.github.fasset.fasset.kernel.util.ConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.YearMonth;
import java.util.Date;
import java.util.Objects;

/**
 * Takes {@link java.util.Date} converting it to {@link java.time.YearMonth}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("dateToYearMonthConverter")
public class DateToYearMonthConverter implements Converter<Date, YearMonth> {

    private static final Logger log = LoggerFactory.getLogger(DateToYearMonthConverter.class);

    private DateToLocalDateConverter dateToLocalDateConverter;

    private LocalDateToYearMonthConverter localDateToYearMonthConverter;


    /**
     * <p>Constructor for DateToYearMonthConverter.</p>
     *
     * @param dateToLocalDateConverter a {@link io.github.fasset.fasset.kernel.util.convert.DateToLocalDateConverter} object.
     */
    public DateToYearMonthConverter(DateToLocalDateConverter dateToLocalDateConverter) {
        this.dateToLocalDateConverter = dateToLocalDateConverter;
    }

    /**
     * <p>Constructor for DateToYearMonthConverter.</p>
     *
     * @param localDateToYearMonthConverter a {@link io.github.fasset.fasset.kernel.util.convert.LocalDateToYearMonthConverter} object.
     * @param dateToLocalDateConverter a {@link io.github.fasset.fasset.kernel.util.convert.DateToLocalDateConverter} object.
     */
    @Autowired
    public DateToYearMonthConverter(@Qualifier("localDateToYearMonthConverter") LocalDateToYearMonthConverter localDateToYearMonthConverter,
                                    @Qualifier("dateToLocalDateConverter") DateToLocalDateConverter dateToLocalDateConverter) {
        this.localDateToYearMonthConverter = localDateToYearMonthConverter;
        this.dateToLocalDateConverter = dateToLocalDateConverter;
    }


    /**
     * <p>Constructor for DateToYearMonthConverter.</p>
     */
    public DateToYearMonthConverter() {
    }

    /**
     * <p>Getter for the field <code>dateToLocalDateConverter</code>.</p>
     *
     * @return a {@link io.github.fasset.fasset.kernel.util.convert.DateToLocalDateConverter} object.
     */
    public DateToLocalDateConverter getDateToLocalDateConverter() {
        return dateToLocalDateConverter;
    }

    /**
     * <p>Setter for the field <code>dateToLocalDateConverter</code>.</p>
     *
     * @param dateToLocalDateConverter a {@link io.github.fasset.fasset.kernel.util.convert.DateToLocalDateConverter} object.
     * @return a {@link io.github.fasset.fasset.kernel.util.convert.DateToYearMonthConverter} object.
     */
    public DateToYearMonthConverter setDateToLocalDateConverter(DateToLocalDateConverter dateToLocalDateConverter) {
        this.dateToLocalDateConverter = dateToLocalDateConverter;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * Convert the source object of type {@code S} to target type {@code T}.
     */
    @Override
    public YearMonth convert(Date source) {

        YearMonth convertedMonth;

        log.debug("Converting {} to YearMonth", source.toString());

        Date convertFrom = nullDateReassignment(source);

        try {

            convertedMonth = localDateToYearMonthConverter.convert(Objects.requireNonNull(dateToLocalDateConverter.convert(convertFrom)));

        } catch (Throwable e) {
            if (dateToLocalDateConverter == null) {
                throw new ConverterException("The dateToLocalDateConverter is null", e);
            } else {
                throw new ConverterException(String.format("Exception thrown while converting %s to YearMonth", source), e);
            }
        }

        return convertedMonth;
    }

    private Date nullDateReassignment(Date source) {

        if (source == null) {

            Date reassignedDate = Date.from(Instant.now());

            log.error("The source data is null.. reassigning to current time : {}", reassignedDate);

            return reassignedDate;
        }

        return source;
    }
}
