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

import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdConfigurationPropertiesService;
import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdConfigurationService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountIdConfigurationPropertiesServiceTest {


    private AccountIdConfigurationService configurationService = new AccountIdConfigurationPropertiesService("account-nomenclature-config");

    @Test
    public void acquisitionGlId() {

        assertEquals("013", configurationService.acquisitionGlId("COMPUTERS"));
        assertEquals("017", configurationService.acquisitionGlId("COMPUTER SOFTWARE"));
        assertEquals("015", configurationService.acquisitionGlId("FURNITURE & FITTINGS"));
        assertEquals("014", configurationService.acquisitionGlId("ELECTRONICS"));
        assertEquals("016", configurationService.acquisitionGlId("MOTOR VEHICLES"));
    }

    @Test
    public void acquisitionGlCode() {

        assertEquals("00150", configurationService.acquisitionGlCode("COMPUTERS"));
        assertEquals("00155", configurationService.acquisitionGlCode("COMPUTER SOFTWARE"));
        assertEquals("00153", configurationService.acquisitionGlCode("FURNITURE & FITTINGS"));
        assertEquals("00151", configurationService.acquisitionGlCode("ELECTRONICS"));
        assertEquals("00154", configurationService.acquisitionGlCode("MOTOR VEHICLES"));
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

        assertEquals("10051", configurationService.getAcquisitionCreditGlCode());
    }

    @Test
    public void getAcquisitionCreditGlId() {

        assertEquals("001", configurationService.getAcquisitionCreditGlId());
    }
}