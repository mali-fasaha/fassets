package io.github.fasset.fasset.accounts.nomenclature;

import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdService;
import io.github.fasset.fasset.accounts.nomenclature.properties.FileAccountIdService;
import io.github.fasset.fasset.accounts.nomenclature.properties.policy.AccountIdPolicy;
import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static io.github.fasset.fasset.accounts.definition.TransactionType.ACQUISITION;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.CREDIT;
import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static org.junit.Assert.assertEquals;

public class AccountIdServiceTest {

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200, "KES"), "ELECTRONIC EQUIPMENT", "001", LocalDate.of(2018, 2, 5), "abc01", Money.of(9.5, "KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600, "KES"), "COMPUTERS", "987", LocalDate.of(2018, 2, 13), "abc02", Money.of(13.42, "KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156, "KES"), "FURNITURE & FITTINGS", "010", LocalDate.of(2018, 1, 13), "abc03", Money.of(19.24, "KES"));
    private final static FixedAsset zemana = new FixedAsset("Zemana Antilogger", Money.of(180, "KES"), "COMPUTER SOFTWARE", "986", LocalDate.of(2017, 12, 15), "abc48", Money.of(50.32, "KES"));
    private final static FixedAsset kca = new FixedAsset("KCA 265G", Money.of(950, "KES"), "MOTOR VEHICLES", "996", LocalDate.of(2018, 1, 5), "abc23", Money.of(500.12, "KES"));
    private final static FixedAsset officePartitioning =
        new FixedAsset("Office Partitioning Works", Money.of(1500, "KES"), "OFFICE RENOVATION", "978", LocalDate.of(2018, 1, 12), "abc56", Money.of(50.13, "KES"));

    private AccountIdService accountIdService;

    @BeforeEach
    void setUp() {

        AccountIdPolicy accountIdPolicy = Mockito.mock(AccountIdPolicy.class);

        

        accountIdService = new FileAccountIdService(accountIdPolicy);
    }

    @Test
    public void accountName() {

        Assertions.assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, radio));
        Assertions.assertEquals("ELECTRONIC EQUIPMENT", accountIdService.accountName(ACQUISITION, DEBIT, radio));
        Assertions.assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, lenovo));
        Assertions.assertEquals("COMPUTERS", accountIdService.accountName(ACQUISITION, DEBIT, lenovo));
        Assertions.assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, chair));
        Assertions.assertEquals("FURNITURE AND FITTINGS", accountIdService.accountName(ACQUISITION, DEBIT, chair));
        Assertions.assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, zemana));
        Assertions.assertEquals("COMPUTER SOFTWARE", accountIdService.accountName(ACQUISITION, DEBIT, zemana));
        Assertions.assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, kca));
        Assertions.assertEquals("MOTOR VEHICLES", accountIdService.accountName(ACQUISITION, DEBIT, kca));
        Assertions.assertEquals("OFFICE RENOVATION", accountIdService.accountName(ACQUISITION, DEBIT, officePartitioning));
        Assertions.assertEquals("SUNDRY CREDITORS", accountIdService.accountName(ACQUISITION, CREDIT, officePartitioning));
    }

    @Test
    public void generalLegerCode() {

        Assertions.assertEquals("00153", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, chair));
        Assertions.assertEquals("00152", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, lenovo));
        Assertions.assertEquals("00156", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, radio));
        Assertions.assertEquals("00155", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, zemana));
        Assertions.assertEquals("00151", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, kca));
        Assertions.assertEquals("00154", accountIdService.generalLedgerCode(ACQUISITION, DEBIT, officePartitioning));
        Assertions.assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, chair));
        Assertions.assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, lenovo));
        Assertions.assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, radio));
        Assertions.assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, zemana));
        Assertions.assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, kca));
        Assertions.assertEquals("10051", accountIdService.generalLedgerCode(ACQUISITION, CREDIT, officePartitioning));
    }

    @Test
    public void accountPlaceHolder() throws Exception {

        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, chair));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, chair));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, lenovo));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, lenovo));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, radio));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, radio));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, zemana));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, zemana));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, kca));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, kca));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, CREDIT, officePartitioning));
        Assertions.assertEquals("001", accountIdService.accountPlaceHolder(ACQUISITION, DEBIT, officePartitioning));
    }

    @Test
    public void accountNumber() {

        assertEquals("0010010051001", accountIdService.accountNumber("001","00","10051","001"));
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
