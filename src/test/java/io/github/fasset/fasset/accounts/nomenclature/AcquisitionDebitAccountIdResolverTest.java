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

import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdService;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AcquisitionDebitAccountIdResolverTest {

    private AccountIdResolver accountIdResolver;

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200,"KES"), "ELECTRONICS", "001", LocalDate.of(2018,2,5), "abc01", Money.of(9.5,"KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600,"KES"), "COMPUTERS", "987",LocalDate.of(2018,2,13), "abc02", Money.of(13.42,"KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156,"KES"), "FURNITURE", "010",LocalDate.of(2018,1,13),"abc03", Money.of(19.24,"KES"));

    @Before
    public void setUp() throws Exception {

        AccountIdService configurationService = Mockito.mock(AccountIdService.class);

        // General ledger Codes
        when(configurationService.debitGeneralLedgerCode("COMPUTERS")).thenReturn("00152");
        when(configurationService.debitGeneralLedgerCode("FURNITURE")).thenReturn("00153");
        when(configurationService.debitGeneralLedgerCode("ELECTRONICS")).thenReturn("00154");

        // General ledger Ids
        when(configurationService.debitAccountPlaceHolder("COMPUTERS")).thenReturn("013");
        when(configurationService.debitAccountPlaceHolder("FURNITURE")).thenReturn("014");
        when(configurationService.debitAccountPlaceHolder("ELECTRONICS")).thenReturn("015");

        // ISO 4217 Currency codes
        when(configurationService.currencyCode("KES")).thenReturn("00");

        accountIdResolver = new AcquisitionDebitAccountIdResolver(configurationService);
    }

    @Test
    public void resolveName() {

        assertEquals("COMPUTERS", accountIdResolver.accountName(lenovo));
        assertEquals("FURNITURE", accountIdResolver.accountName(chair));
        assertEquals("ELECTRONICS", accountIdResolver.accountName(radio));
    }

    @Test
    public void resolveContraAccountId() {

        assertEquals("ACCUMULATED DEPRECIATION ON COMPUTERS", accountIdResolver.resolveContraAccountId(lenovo));
        assertEquals("ACCUMULATED DEPRECIATION ON FURNITURE", accountIdResolver.resolveContraAccountId(chair));
        assertEquals("ACCUMULATED DEPRECIATION ON ELECTRONICS", accountIdResolver.resolveContraAccountId(radio));
    }

    @Test
    public void resolveCategoryId() {
        assertEquals("COMPUTERS", accountIdResolver.accountName(lenovo));
        assertEquals("FURNITURE", accountIdResolver.accountName(chair));
        assertEquals("ELECTRONICS", accountIdResolver.accountName(radio));
    }

    @Test
    public void resolveNumber() {

        assertEquals("0010000154015", accountIdResolver.accountNumber(radio));
        assertEquals("0100000153014", accountIdResolver.accountNumber(chair));
        assertEquals("9870000152013", accountIdResolver.accountNumber(lenovo));
    }

    @Test
    public void resolveGeneralLedgerId() {

        assertEquals("ELECTRONICS", accountIdResolver.generalLedgerName(radio));
        assertEquals("FURNITURE", accountIdResolver.generalLedgerName(chair));
        assertEquals("COMPUTERS", accountIdResolver.generalLedgerName(lenovo));
    }
}