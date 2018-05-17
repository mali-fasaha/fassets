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
package io.github.fasset.fasset.managers.id;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountIdConfigurationPropertiesServiceTest {


    private AccountIdConfigurationService configurationService = new AccountIdConfigurationPropertiesService("account-id-config");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void acquisitionGlId() {
    }

    @Test
    public void acquisitionGlCode() {

        assertEquals("00150", configurationService.acquisitionGlCode("COMPUTERS"));
        assertEquals("00155", configurationService.acquisitionGlCode("COMPUTER SOFTWARE"));
    }

    @Test
    public void getCurrencyCode() {

        assertEquals("00", configurationService.getCurrencyCode("KES"));
    }
}