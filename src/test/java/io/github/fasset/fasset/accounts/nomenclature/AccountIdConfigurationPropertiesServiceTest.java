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
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AccountIdConfigurationPropertiesServiceTest {


    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200,"KES"), "Electronics", "001", LocalDate.of(2018,2,5), "abc01", Money.of(9.5,"KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600,"KES"), "COMPUTERS", "987",LocalDate.of(2018,2,13), "abc02", Money.of(13.42,"KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156,"KES"), "FURNITURE & FITTINGS", "010",LocalDate.of(2018,1,13),"abc03", Money.of(19.24,"KES"));
    private AccountIdService configurationService = new AcquisitionAccountIdService("account-id", "account-label");

    //@Test
    public void acquisitionGlId() {

        assertEquals("013", configurationService.debitAccountPlaceHolder("COMPUTERS"));
        assertEquals("017", configurationService.debitAccountPlaceHolder("COMPUTER SOFTWARE"));
        assertEquals("015", configurationService.debitAccountPlaceHolder("FURNITURE & FITTINGS"));
        assertEquals("014", configurationService.debitAccountPlaceHolder("ELECTRONICS"));
        assertEquals("016", configurationService.debitAccountPlaceHolder("MOTOR VEHICLES"));
    }

    //@Test
    public void acquisitionGlCode() {

        assertEquals("00150", configurationService.debitGeneralLedgerCode("COMPUTERS"));
        assertEquals("00155", configurationService.debitGeneralLedgerCode("COMPUTER SOFTWARE"));
        assertEquals("00153", configurationService.debitGeneralLedgerCode("FURNITURE & FITTINGS"));
        assertEquals("00151", configurationService.debitGeneralLedgerCode("ELECTRONICS"));
        assertEquals("00154", configurationService.debitGeneralLedgerCode("MOTOR VEHICLES"));
    }

    //@Test
    public void getCurrencyCode() {

        assertEquals("00", configurationService.currencyCode("KES"));
        assertEquals("01", configurationService.currencyCode("USD"));
        assertEquals("02", configurationService.currencyCode("GBP"));
        assertEquals("03", configurationService.currencyCode("EUR"));
        assertEquals("04", configurationService.currencyCode("INR"));
        assertEquals("05", configurationService.currencyCode("CAD"));
        assertEquals("06", configurationService.currencyCode("CHF"));
        assertEquals("07", configurationService.currencyCode("ZAR"));
    }

    @Test
    public void getAcquisitionCreditGlCode() {

        //assertEquals("10051", configurationService.generalLedgerCode());
    }

    @Test
    public void getAcquisitionCreditGlId() {

        //TODO run this and confirm it works
        //assertEquals("001", configurationService.accountPlaceHolder(ACQUISITION, CREDIT, chair));
    }
}