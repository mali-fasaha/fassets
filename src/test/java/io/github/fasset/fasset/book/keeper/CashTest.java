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
package io.github.fasset.fasset.book.keeper;

import io.github.fasset.fasset.book.keeper.unit.money.Cash;
import io.github.fasset.fasset.book.keeper.unit.money.HardCash;
import org.joda.money.CurrencyMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.math.RoundingMode;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CashTest {

    private Cash cash;

    @Before
    public void setUp() throws Exception {

        cash = HardCash.shilling(106.50);
    }

    @Test
    public void isMoreThan() throws Exception {

        assertTrue(cash.isMoreThan(HardCash.shilling(100)));
    }

    @Test
    public void isMoreThan1() throws Exception {

        assertTrue(cash.isMoreThan(HardCash.of(106.49,"KES")));
    }

    @Test(expected = CurrencyMismatchException.class)
    public void cannotCashWithDifferentCurrencies() throws Exception {

        assertTrue(cash.isMoreThan(HardCash.of(106.49,"USD")));
    }

    @Test
    public void isLessThan() throws Exception {

        assertTrue(cash.isLessThan(HardCash.of(106.51,"KES")));
    }

    @Test
    public void currencyIsCorrectlyDenominated() throws Exception {

        assertTrue(cash.getCurrency().equals(Currency.getInstance("KES")));
        assertTrue(HardCash.dollar(120).getCurrency().getCurrencyCode().equals("USD"));
        assertTrue(HardCash.euro(120).getCurrency().getCurrencyCode().equals("EUR"));
        assertTrue(HardCash.shilling(120).getCurrency().getCurrencyCode().equals("KES"));
        assertTrue(HardCash.sterling(120).getCurrency().getCurrencyCode().equals("GBP"));
    }


    @Test
    public void plus() throws Exception {

        assertEquals(HardCash.shilling(206.53),cash.plus(HardCash.shilling(100.03)));
    }

    @Test
    public void minus() throws Exception {

        assertEquals(HardCash.shilling(105.00),cash.minus(HardCash.of(1.5,"KES")));
    }

    @Test
    public void multiply() throws Exception {

        assertEquals(HardCash.shilling(319.50),cash.multiply(3));
        assertEquals(HardCash.shilling(319.50),cash.multiply(3, RoundingMode.HALF_EVEN));
        assertEquals(HardCash.shilling(319.50),cash.multiply(3, RoundingMode.HALF_DOWN));
    }

    @Test
    public void divide() throws Exception {

        assertEquals(HardCash.shilling(32.08),cash.divide(3.32));
        assertEquals(HardCash.shilling(32.08),cash.divide(3.32,RoundingMode.HALF_DOWN));
        assertEquals(HardCash.shilling(32.27),cash.divide(3.3,RoundingMode.HALF_EVEN));
    }

    @Test
    public void ABSworks() {
        assertEquals(HardCash.dollar(32.08),HardCash.dollar(-32.08).abs());
    }
}