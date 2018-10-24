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
package io.github.fasset.fasset.kernel.book.keeper.util;

import io.github.fasset.fasset.config.DateProperties;
import io.github.ghacupha.time.point.ReadableDate;
import io.github.ghacupha.time.point.ReadableTime;
import io.github.ghacupha.time.point.TimePoint;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Used to convert TimePoint state to database column representation and back again
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Converter(autoApply = true)
public class TimePointAttributeConverter implements AttributeConverter<TimePoint, String> {

    private DateProperties dateProperties;

    /**
     * <p>Constructor for TimePointAttributeConverter.</p>
     */
    public TimePointAttributeConverter() {

        dateProperties = new DateProperties();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Converts the value stored in the entity attribute into the data representation to be stored in the database.
     */
    @Override
    public String convertToDatabaseColumn(TimePoint attribute) {

        ReadableTime readableTime = new ReadableDate(dateProperties.getDatePattern(), attribute);

        return readableTime.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Converts the data stored in the database column into the value to be stored in the entity attribute. Note that it is the responsibility of the converter writer to specify the correct dbData
     * type for the corresponding column for use by the JDBC driver: i.e., persistence providers are not expected to do such type conversion.
     */
    @Override
    public TimePoint convertToEntityAttribute(String dbData) {

        return TimePointUtils.parseString(dbData, dateProperties.getDatePattern());
    }
}
