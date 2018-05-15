/**
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
package io.github.fasset.fasset.book.keeper.util;

import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashAttributeConverterTest {

    private CashAttributeConverter attributeConverter = new CashAttributeConverter();

    @Test
    public void convertToDatabaseColumn() {

        assertEquals("GBP 56894.32",attributeConverter.convertToDatabaseColumn(HardCash.sterling(56894.32)));
    }

    @Test
    public void convertToEntityAttribute() {

        assertEquals(attributeConverter.convertToDatabaseColumn(HardCash.euro(58659123.23)),"EUR 58659123.23");
    }
}