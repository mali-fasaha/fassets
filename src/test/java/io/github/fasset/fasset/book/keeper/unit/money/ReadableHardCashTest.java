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

package io.github.fasset.fasset.book.keeper.unit.money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadableHardCashTest {

    private ReadableCash readableCash;
    private Cash commaSeparatedCash;

    @Before
    public void setUp() throws Exception {

        readableCash = new ReadableHardCash(HardCash.shilling(300.52));
        commaSeparatedCash = new ReadableHardCash(HardCash.shilling(10250.58));
    }

    @Test
    public void getString() {

        assertEquals("KES 300.52", readableCash.toString());
        assertEquals("KES 10250.58", commaSeparatedCash.toString());
    }

    @Test
    public void parseString() {

        assertEquals(HardCash.shilling(10358.31), readableCash.parseString("KES 10358.31"));
    }
}