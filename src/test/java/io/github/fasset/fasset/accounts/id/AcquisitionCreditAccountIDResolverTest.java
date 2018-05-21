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
package io.github.fasset.fasset.accounts.id;

import io.github.fasset.fasset.managers.id.AccountIdConfigurationPropertiesService;
import io.github.fasset.fasset.managers.id.AccountIdConfigurationService;
import io.github.fasset.fasset.managers.id.AcquisitionCreditAccountIDResolver;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AcquisitionCreditAccountIDResolverTest {

    private AcquisitionCreditAccountIDResolver accountIDResolver;

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200,"KES"), "Electronics", "001", LocalDate.of(2018,2,5), "abc01", Money.of(9.5,"KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600,"KES"), "COMPUTERS", "987",LocalDate.of(2018,2,13), "abc02", Money.of(13.42,"KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156,"KES"), "FURNITURE & FITTINGS", "010",LocalDate.of(2018,1,13),"abc03", Money.of(19.24,"KES"));

    @Before
    public void setUp() throws Exception {

        AccountIdConfigurationService configurationService = Mockito.mock(AccountIdConfigurationService.class);

        accountIDResolver = new AcquisitionCreditAccountIDResolver(new AccountIdConfigurationPropertiesService("account-id-config"));
    }

    @Test
    public void resolveName() {

        assertEquals("SUNDRY CREDITORS ACCOUNT", accountIDResolver.resolveName(radio));
        assertEquals("SUNDRY CREDITORS ACCOUNT", accountIDResolver.resolveName(lenovo));
        assertEquals("SUNDRY CREDITORS ACCOUNT", accountIDResolver.resolveName(chair));
    }

    @Test
    public void resolveNumber() {

        assertEquals("0010010051001", accountIDResolver.resolveNumber(radio));
        assertEquals("9870010051001", accountIDResolver.resolveNumber(lenovo));
        assertEquals("0100010051001", accountIDResolver.resolveNumber(chair));
    }

    @Test
    public void resolveContraAccountId() {

        assertEquals("SUNDRY CREDITORS ACCOUNT", accountIDResolver.resolveContraAccountId(radio));
        assertEquals("SUNDRY CREDITORS ACCOUNT", accountIDResolver.resolveContraAccountId(chair));
        assertEquals("SUNDRY CREDITORS ACCOUNT", accountIDResolver.resolveContraAccountId(lenovo));
    }

    @Test
    public void resolveGeneralLedgerName() {

        assertEquals("SUNDRY CREDITORS", accountIDResolver.resolveGeneralLedgerName(radio));
        assertEquals("SUNDRY CREDITORS", accountIDResolver.resolveGeneralLedgerName(chair));
        assertEquals("SUNDRY CREDITORS", accountIDResolver.resolveGeneralLedgerName(lenovo));
    }

    @Test
    public void resolveCategoryId() {

        assertEquals("ELECTRONICS", accountIDResolver.resolveCategoryId(radio));
        assertEquals("FURNITURE & FITTINGS", accountIDResolver.resolveCategoryId(chair));
        assertEquals("COMPUTERS", accountIDResolver.resolveCategoryId(lenovo));
    }
}