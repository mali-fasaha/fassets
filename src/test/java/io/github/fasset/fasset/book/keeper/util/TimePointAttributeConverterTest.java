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

package io.github.fasset.fasset.book.keeper.util;

import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.config.DateProperties;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimePointAttributeConverterTest {

    private TimePointAttributeConverter timePointAttributeConverter;

    @Before
    public void setUp() throws Exception {

        timePointAttributeConverter = new TimePointAttributeConverter(new DateProperties("dd/MM/yyyy"));
    }

    @Test
    public void convertToDatabaseColumn() {

        assertEquals("04/05/2013", timePointAttributeConverter.convertToDatabaseColumn(SimpleDate.on(2013,5,4)));
    }

    @Test
    public void convertToEntityAttribute() {

        assertEquals(SimpleDate.on(2016,11,3), timePointAttributeConverter.convertToEntityAttribute("03/11/2016"));
    }
}