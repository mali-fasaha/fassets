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

class AcquisitionAccountIdServiceTest {

    static AcquisitionAccountIdService acquisitionAccountIdService;

    private final static FixedAsset radio = new FixedAsset("Radio", Money.of(200,"KES"), "Electronics", "001", LocalDate.of(2018,2,5), "abc01", Money.of(9.5,"KES"));
    private final static FixedAsset lenovo = new FixedAsset("Lenovo IDP110", Money.of(5600,"KES"), "COMPUTERS", "987",LocalDate.of(2018,2,13), "abc02", Money.of(13.42,"KES"));
    private final static FixedAsset chair = new FixedAsset("Chair", Money.of(156,"KES"), "FURNITURE & FITTINGS", "010",LocalDate.of(2018,1,13),"abc03", Money.of(19.24,"KES"));

    @BeforeEach
    void setUp() {

        acquisitionAccountIdService = new AcquisitionAccountIdService("account-id", "account-label");
    }

    @Test
    void accountName() {

        assertEquals("SUNDRY CREDITORS", acquisitionAccountIdService.accountName(ACQUISITION, CREDIT, radio));
        assertEquals("SUNDRY CREDITORS", acquisitionAccountIdService.accountName(ACQUISITION, CREDIT, lenovo));
        assertEquals("SUNDRY CREDITORS", acquisitionAccountIdService.accountName(ACQUISITION, CREDIT, chair));
    }

    @Test void accountPlaceHolder() throws Exception {

        assertEquals("001", acquisitionAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, chair));
        assertEquals("001", acquisitionAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, lenovo));
        assertEquals("001", acquisitionAccountIdService.accountPlaceHolder(ACQUISITION, CREDIT, radio));
    }

    @Test void generalLegerCode() throws Exception {

        assertEquals("00153", acquisitionAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, chair));
        assertEquals("00150", acquisitionAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, lenovo));
        assertEquals("00151", acquisitionAccountIdService.generalLedgerCode(ACQUISITION, DEBIT, radio));
    }
}