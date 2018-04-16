/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.kernel.util.convert;

import io.github.fasset.fasset.kernel.util.ConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Converts java.time.LocalDate to java.time.YearMonth
 */
@Component("localDateToYearMonthConverter")
public class LocalDateToYearMonthConverter implements Converter<LocalDate, YearMonth> {

    private static final Logger log = LoggerFactory.getLogger(LocalDateToYearMonthConverter.class);

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Nullable
    @Override
    public YearMonth convert(LocalDate source) {

        YearMonth convertedMonth = null;

        log.debug("Converting {} to YearMonth", source.toString());

        try {

            convertedMonth = YearMonth.from(source);

        } catch (Throwable e) {
            if (source == null) {
                throw new ConverterException("The date provided is null, kindly review the source data again...", e);
            } else {
                throw new ConverterException(String.format("Exception thrown while converting %s to YearMonth", source), e);
            }
        }

        return convertedMonth;
    }
}
