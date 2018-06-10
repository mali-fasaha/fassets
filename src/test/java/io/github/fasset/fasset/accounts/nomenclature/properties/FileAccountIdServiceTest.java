package io.github.fasset.fasset.accounts.nomenclature.properties;

import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.github.fasset.fasset.accounts.definition.Posting.DEBIT;
import static io.github.fasset.fasset.accounts.definition.TransactionType.ACQUISITION;
import static io.github.fasset.fasset.accounts.definition.Posting.CREDIT;
import static org.junit.jupiter.api.Assertions.*;

class FileAccountIdServiceTest {

    static FileAccountIdService fileAccountIdService;

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200,"KES"), "Electronics", "001", LocalDate.of(2018,2,5), "abc01", Money.of(9.5,"KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600,"KES"), "COMPUTERS", "987",LocalDate.of(2018,2,13), "abc02", Money.of(13.42,"KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156,"KES"), "FURNITURE & FITTINGS", "010",LocalDate.of(2018,1,13),"abc03", Money.of(19.24,"KES"));
    private final static FixedAsset zemana = new FixedAsset("Zemana Antilogger", Money.of(180,"KES"), "COMPUTER SOFTWARE", "986",LocalDate.of(2017,12,15),"abc48", Money.of(50.32,"KES"));
    private final static FixedAsset kca = new FixedAsset("KCA 265G", Money.of(950,"KES"), "MOTOR VEHICLES", "996",LocalDate.of(2018,1,5),"abc23", Money.of(500.12,"KES"));

    @BeforeEach
    void setUp() {

        fileAccountIdService = new FileAccountIdService("account-id", "account-label");
    }

    @Test
    void accountName() {

        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, radio));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, lenovo));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, chair));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, zemana));
        assertEquals("SUNDRY CREDITORS", fileAccountIdService.accountName(ACQUISITION, CREDIT, kca));
    }

    @Test void accountPlaceHolder() throws Exception {

        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, chair));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, lenovo));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, radio));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, zemana));
        assertEquals("001", fileAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, kca));
    }

    @Test void generalLegerCode() throws Exception {

        assertEquals("00153", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, chair));
        assertEquals("00150", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, lenovo));
        assertEquals("00151", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, radio));
        assertEquals("00155", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, zemana));
        assertEquals("00154", fileAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, kca));
    }
}