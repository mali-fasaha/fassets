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
package io.github.fasset.fasset.accounts.nomenclature;

import io.github.fasset.fasset.accounts.nomenclature.properties.AcquisitionAccountIdService;
import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountIdConfigurationPropertiesServiceTest {


    private AccountIdService configurationService = new AcquisitionAccountIdService("account-id", "account-label");

    @Test
    public void acquisitionGlId() {

        assertEquals("013", configurationService.debitAccountPlaceHolder("COMPUTERS"));
        assertEquals("017", configurationService.debitAccountPlaceHolder("COMPUTER SOFTWARE"));
        assertEquals("015", configurationService.debitAccountPlaceHolder("FURNITURE & FITTINGS"));
        assertEquals("014", configurationService.debitAccountPlaceHolder("ELECTRONICS"));
        assertEquals("016", configurationService.debitAccountPlaceHolder("MOTOR VEHICLES"));
    }

    @Test
    public void acquisitionGlCode() {

        assertEquals("00150", configurationService.debitGeneralLedgerCode("COMPUTERS"));
        assertEquals("00155", configurationService.debitGeneralLedgerCode("COMPUTER SOFTWARE"));
        assertEquals("00153", configurationService.debitGeneralLedgerCode("FURNITURE & FITTINGS"));
        assertEquals("00151", configurationService.debitGeneralLedgerCode("ELECTRONICS"));
        assertEquals("00154", configurationService.debitGeneralLedgerCode("MOTOR VEHICLES"));
    }

    @Test
    public void getCurrencyCode() {

        assertEquals("00", configurationService.getCurrencyCode("KES"));
        assertEquals("01", configurationService.getCurrencyCode("USD"));
        assertEquals("02", configurationService.getCurrencyCode("GBP"));
        assertEquals("03", configurationService.getCurrencyCode("EUR"));
        assertEquals("04", configurationService.getCurrencyCode("INR"));
        assertEquals("05", configurationService.getCurrencyCode("CAD"));
        assertEquals("06", configurationService.getCurrencyCode("CHF"));
        assertEquals("07", configurationService.getCurrencyCode("ZAR"));
    }

    @Test
    public void getAcquisitionCreditGlCode() {

        assertEquals("10051", configurationService.creditGeneralLedgerCode());
    }

    @Test
    public void getAcquisitionCreditGlId() {

        assertEquals("001", configurationService.accountPlaceHolder());
    }
}