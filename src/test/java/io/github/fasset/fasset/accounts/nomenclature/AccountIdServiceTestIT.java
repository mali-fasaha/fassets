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
import io.github.fasset.fasset.accounts.nomenclature.properties.FileAccountIdService;
import io.github.fasset.fasset.accounts.nomenclature.properties.policy.AccountIdPolicyVersion1;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static io.github.fasset.fasset.accounts.definition.TransactionType.ACQUISITION;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.kernel.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.assertEquals;

/**
 * When you run this tests you have to make sure everything even the configuration dependencies are okay. So do that :)
 */
public class AccountIdServiceTestIT {


    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200, "KES"), "ELECTRONIC EQUIPMENT", "001", LocalDate.of(2018, 2, 5), "abc01", Money.of(9.5, "KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600, "KES"), "COMPUTERS", "987", LocalDate.of(2018, 2, 13), "abc02", Money.of(13.42, "KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156, "KES"), "FURNITURE & FITTINGS", "010", LocalDate.of(2018, 1, 13), "abc03", Money.of(19.24, "KES"));
    private final static FixedAsset zemana = new FixedAsset("Zemana Antilogger", Money.of(180, "KES"), "COMPUTER SOFTWARE", "986", LocalDate.of(2017, 12, 15), "abc48", Money.of(50.32, "KES"));
    private final static FixedAsset kca = new FixedAsset("KCA 265G", Money.of(950, "KES"), "MOTOR VEHICLES", "996", LocalDate.of(2018, 1, 5), "abc23", Money.of(500.12, "KES"));
    private final static FixedAsset officePartitioning =
        new FixedAsset("Office Partitioning Works", Money.of(1500, "KES"), "OFFICE RENOVATION", "978", LocalDate.of(2018, 1, 12), "abc56", Money.of(50.13, "KES"));

    private AccountIdService accountIdService;

    @Before
    public void setUp() throws Exception {
        accountIdService = new FileAccountIdService(new AccountIdPolicyVersion1("account-id"));
    }

    @Test
    public void contraAccountName() {
        assertEquals("ACCUMULATED DEPRECIATION - ELECTRONIC EQUIPMENT", accountIdService.contraAccountName(ACQUISITION, DEBIT, radio));
        assertEquals("ACCUMULATED DEPRECIATION - COMPUTERS", accountIdService.contraAccountName(ACQUISITION, DEBIT, lenovo));
        assertEquals("ACCUMULATED DEPRECIATION - FURNITURE AND FITTINGS", accountIdService.contraAccountName(ACQUISITION, DEBIT, chair));
        assertEquals("ACCUMULATED DEPRECIATION - COMPUTER SOFTWARE", accountIdService.contraAccountName(ACQUISITION, DEBIT, zemana));
        assertEquals("ACCUMULATED DEPRECIATION - MOTOR VEHICLES", accountIdService.contraAccountName(ACQUISITION, DEBIT, kca));
        assertEquals("ACCUMULATED DEPRECIATION - OFFICE RENOVATION", accountIdService.contraAccountName(ACQUISITION, DEBIT, officePartitioning));
    }

    @Test
    public void accountName() {

        assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, radio));
        assertEquals("ELECTRONIC EQUIPMENT", accountIdService.accountName(ACQUISITION, DEBIT, radio));
        assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, lenovo));
        assertEquals("COMPUTERS", accountIdService.accountName(ACQUISITION, DEBIT, lenovo));
        assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, chair));
        assertEquals("FURNITURE AND FITTINGS", accountIdService.accountName(ACQUISITION, DEBIT, chair));
        assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, zemana));
        assertEquals("COMPUTER SOFTWARE", accountIdService.accountName(ACQUISITION, DEBIT, zemana));
        assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, kca));
        assertEquals("MOTOR VEHICLES", accountIdService.accountName(ACQUISITION, DEBIT, kca));
        assertEquals("OFFICE RENOVATION", accountIdService.accountName(ACQUISITION, DEBIT, officePartitioning));
        assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, officePartitioning));
    }

    @Test
    public void generalLegerCode() {

        assertEquals("00153", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, chair));
        assertEquals("00152", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, lenovo));
        assertEquals("00156", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, radio));
        assertEquals("00155", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, zemana));
        assertEquals("00151", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, kca));
        assertEquals("00154", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, officePartitioning));
        assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, chair));
        assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, lenovo));
        assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, radio));
        assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, zemana));
        assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, kca));
        assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, officePartitioning));
    }

    @Test
    public void accountPlaceHolder() throws Exception {

        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, chair));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, chair));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, lenovo));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, lenovo));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, radio));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, radio));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, zemana));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, zemana));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, kca));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, kca));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, officePartitioning));
        assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, officePartitioning));
    }

    @Test
    public void accountNumber() {

        assertEquals("0010010051001", accountIdService.accountNumber("001", "00", "10051", "001"));
    }

    @Test
    public void getCurrencyCode() {

        assertEquals("00", accountIdService.currencyCode("KES"));
        assertEquals("01", accountIdService.currencyCode("USD"));
        assertEquals("02", accountIdService.currencyCode("GBP"));
        assertEquals("03", accountIdService.currencyCode("EUR"));
        assertEquals("04", accountIdService.currencyCode("INR"));
        assertEquals("05", accountIdService.currencyCode("CAD"));
        assertEquals("06", accountIdService.currencyCode("CHF"));
        assertEquals("07", accountIdService.currencyCode("ZAR"));
    }

}