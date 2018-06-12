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

import org.junit.Before;
import org.junit.Test;

public class YearMonthAttributeConverterTest {

    private YearMonthAttributeConverter yearMonthAttributeConverter;

    @Before
    public void setUp() throws Exception {

        yearMonthAttributeConverter = new YearMonthAttributeConverter();
    }

    @Test
    public void convertToDatabaseColumn() throws Exception {

        /*assertEquals(Date.from(LocalDate.of(2018,5,1).),
        yearMonthAttributeConverter.convertToDatabaseColumn(YearMonth.of(LocalDate.today().getYear(),LocalDate.today().getMonthValue())));*/
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
    }

}