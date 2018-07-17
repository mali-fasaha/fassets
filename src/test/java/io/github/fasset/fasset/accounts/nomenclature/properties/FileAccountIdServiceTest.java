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
package io.github.fasset.fasset.accounts.nomenclature.properties;

import io.github.fasset.fasset.accounts.nomenclature.properties.policy.AccountIdPolicy;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static io.github.fasset.fasset.accounts.definition.TransactionType.ACQUISITION;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * TODO mock out dependencies
 */
public class FileAccountIdServiceTest {

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200, "KES"), "ELECTRONIC EQUIPMENT", "001", LocalDate.of(2018, 2, 5), "abc01", Money.of(9.5, "KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600, "KES"), "COMPUTERS", "987", LocalDate.of(2018, 2, 13), "abc02", Money.of(13.42, "KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156, "KES"), "FURNITURE & FITTINGS", "010", LocalDate.of(2018, 1, 13), "abc03", Money.of(19.24, "KES"));
    private final static FixedAsset zemana = new FixedAsset("Zemana Antilogger", Money.of(180, "KES"), "COMPUTER SOFTWARE", "986", LocalDate.of(2017, 12, 15), "abc48", Money.of(50.32, "KES"));
    private final static FixedAsset kca = new FixedAsset("KCA 265G", Money.of(950, "KES"), "MOTOR VEHICLES", "996", LocalDate.of(2018, 1, 5), "abc23", Money.of(500.12, "KES"));
    private final static FixedAsset officePartitioning =
        new FixedAsset("Office Partitioning Works", Money.of(1500, "KES"), "OFFICE RENOVATION", "978", LocalDate.of(2018, 1, 12), "abc56", Money.of(50.13, "KES"));
    private static FileAccountIdService fileAccountIdService;

    @Before
    public void setUp() {

        AccountIdPolicy accountIdPolicy = Mockito.mock(AccountIdPolicy.class);

        when(accountIdPolicy.accountName(ACQUISITION, DEBIT, "ELECTRONIC EQUIPMENT")).thenReturn("ELECTRONIC EQUIPMENT");
        when(accountIdPolicy.accountName(ACQUISITION, DEBIT, "COMPUTERS")).thenReturn("COMPUTERS");
        when(accountIdPolicy.accountName(ACQUISITION, DEBIT, "FURNITURE & FITTINGS")).thenReturn("FURNITURE AND FITTINGS");
        when(accountIdPolicy.accountName(ACQUISITION, DEBIT, "COMPUTER SOFTWARE")).thenReturn("COMPUTER SOFTWARE");
        when(accountIdPolicy.accountName(ACQUISITION, DEBIT, "MOTOR VEHICLES")).thenReturn("MOTOR VEHICLES");
        when(accountIdPolicy.accountName(ACQUISITION, DEBIT, "OFFICE RENOVATION")).thenReturn("OFFICE RENOVATION");
        when(accountIdPolicy.accountName(ACQUISITION, CREDIT, "ELECTRONIC EQUIPMENT")).thenReturn("SUNDRY CREDITORS");
        when(accountIdPolicy.accountName(ACQUISITION, CREDIT, "COMPUTERS")).thenReturn("SUNDRY CREDITORS");
        when(accountIdPolicy.accountName(ACQUISITION, CREDIT, "FURNITURE & FITTINGS")).thenReturn("SUNDRY CREDITORS");
        when(accountIdPolicy.accountName(ACQUISITION, CREDIT, "COMPUTER SOFTWARE")).thenReturn("SUNDRY CREDITORS");
        when(accountIdPolicy.accountName(ACQUISITION, CREDIT, "MOTOR VEHICLES")).thenReturn("SUNDRY CREDITORS");
        when(accountIdPolicy.accountName(ACQUISITION, CREDIT, "OFFICE RENOVATION")).thenReturn("SUNDRY CREDITORS");

        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "ELECTRONIC EQUIPMENT")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "COMPUTERS")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "FURNITURE & FITTINGS")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "COMPUTER SOFTWARE")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "MOTOR VEHICLES")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "OFFICE RENOVATION")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "ELECTRONIC EQUIPMENT")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "COMPUTERS")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "FURNITURE & FITTINGS")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "COMPUTER SOFTWARE")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "MOTOR VEHICLES")).thenReturn("001");
        when(accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "OFFICE RENOVATION")).thenReturn("001");

        when(accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "ELECTRONIC EQUIPMENT")).thenReturn("00156");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "COMPUTERS")).thenReturn("00152");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "FURNITURE & FITTINGS")).thenReturn("00153");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "COMPUTER SOFTWARE")).thenReturn("00155");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "MOTOR VEHICLES")).thenReturn("00151");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "OFFICE RENOVATION")).thenReturn("00154");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "ELECTRONIC EQUIPMENT")).thenReturn("10051");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "COMPUTERS")).thenReturn("10051");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "FURNITURE & FITTINGS")).thenReturn("10051");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "COMPUTER SOFTWARE")).thenReturn("10051");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "MOTOR VEHICLES")).thenReturn("10051");
        when(accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "OFFICE RENOVATION")).thenReturn("10051");

        fileAccountIdService = new FileAccountIdService(accountIdPolicy);
    }

    @Test
    public void generalLegerCode() throws Exception {

        assertEquals("00153", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, chair));
        assertEquals("00152", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, lenovo));
        assertEquals("00156", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, radio));
        assertEquals("00155", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, zemana));
        assertEquals("00151", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, kca));
        assertEquals("00154", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, officePartitioning));
        assertEquals("10051", fileAccountIdService.generalLedgerCode(ACQUISITION, CREDIT, chair));
        assertEquals("10051", fileAccountIdService.generalLedgerCode(ACQUISITION, CREDIT, lenovo));
        assertEquals("10051", fileAccountIdService.generalLedgerCode(ACQUISITION, CREDIT, radio));
        assertEquals("10051", fileAccountIdService.generalLedgerCode(ACQUISITION, CREDIT, zemana));
        assertEquals("10051", fileAccountIdService.generalLedgerCode(ACQUISITION, CREDIT, kca));
        assertEquals("10051", fileAccountIdService.generalLedgerCode(ACQUISITION, CREDIT, officePartitioning));
    }

    @Test
    public void accountName() {

        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, radio));
        assertEquals("ELECTRONIC EQUIPMENT", fileAccountIdService.accountName(ACQUISITION, DEBIT, radio));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, lenovo));
        assertEquals("COMPUTERS", fileAccountIdService.accountName(ACQUISITION, DEBIT, lenovo));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, chair));
        assertEquals("FURNITURE AND FITTINGS", fileAccountIdService.accountName(ACQUISITION, DEBIT, chair));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, zemana));
        assertEquals("COMPUTER SOFTWARE", fileAccountIdService.accountName(ACQUISITION, DEBIT, zemana));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, kca));
        assertEquals("MOTOR VEHICLES", fileAccountIdService.accountName(ACQUISITION, DEBIT, kca));
        assertEquals("OFFICE RENOVATION", fileAccountIdService.accountName(ACQUISITION, DEBIT, officePartitioning));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, officePartitioning));
    }

    @Test
    public void accountPlaceHolder() throws Exception {

        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, chair));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, DEBIT, chair));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, lenovo));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, DEBIT, lenovo));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, radio));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, DEBIT, radio));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, zemana));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, DEBIT, zemana));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, kca));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, DEBIT, kca));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, officePartitioning));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, DEBIT, officePartitioning));
    }

}
