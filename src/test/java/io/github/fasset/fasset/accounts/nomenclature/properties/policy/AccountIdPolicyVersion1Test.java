package io.github.fasset.fasset.accounts.nomenclature.properties.policy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.fasset.fasset.accounts.definition.Posting.CREDIT;
import static io.github.fasset.fasset.accounts.definition.Posting.DEBIT;
import static io.github.fasset.fasset.accounts.definition.TransactionType.ACQUISITION;
import static org.junit.jupiter.api.Assertions.*;

class AccountIdPolicyVersion1Test {

    private AccountIdPolicy accountIdPolicy;

    @BeforeEach void setUp() {
        accountIdPolicy =
            new AccountIdPolicyVersion1("account-id");
    }

    @Test void testCurrencyCode() throws Exception {

        assertEquals("00", accountIdPolicy.currencyCode("KES"));
        assertEquals("01", accountIdPolicy.currencyCode("USD"));
        assertEquals("02", accountIdPolicy.currencyCode("GBP"));
        assertEquals("03", accountIdPolicy.currencyCode("EUR"));
        assertEquals("04", accountIdPolicy.currencyCode("INR"));
        assertEquals("05", accountIdPolicy.currencyCode("CAD"));
        assertEquals("06", accountIdPolicy.currencyCode("CHF"));
        assertEquals("07", accountIdPolicy.currencyCode("ZAR"));

    }

    @Test
    void generalLedgerCode() {

        assertEquals("10051", accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "FURNITURE & FITTINGS"));
        assertEquals("00153", accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "FURNITURE & FITTINGS"));
        assertEquals("10051", accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "COMPUTERS"));
        assertEquals("00152", accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "COMPUTERS"));
        assertEquals("10051", accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "ELECTRONIC EQUIPMENT"));
        assertEquals("00156", accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "ELECTRONIC EQUIPMENT"));
        assertEquals("10051", accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "COMPUTER SOFTWARE"));
        assertEquals("00155", accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "COMPUTER SOFTWARE"));
        assertEquals("10051", accountIdPolicy.generalLedgerCode(ACQUISITION, CREDIT, "MOTOR VEHICLES"));
        assertEquals("00151", accountIdPolicy.generalLedgerCode(ACQUISITION, DEBIT, "MOTOR VEHICLES"));
    }

    @Test
    void accountPlaceHolder() {

        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "FURNITURE & FITTINGS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "FURNITURE & FITTINGS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "COMPUTERS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "COMPUTERS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "ELECTRONIC EQUIPMENT"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "ELECTRONIC EQUIPMENT"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "COMPUTER SOFTWARE"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "COMPUTER SOFTWARE"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "MOTOR VEHICLES"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "MOTOR VEHICLES"));
    }

    @Test
    void accountName() {

        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "FURNITURE & FITTINGS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "FURNITURE & FITTINGS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "COMPUTERS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "COMPUTERS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "ELECTRONIC EQUIPMENT"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "ELECTRONIC EQUIPMENT"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "COMPUTER SOFTWARE"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "COMPUTER SOFTWARE"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "MOTOR VEHICLES"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "MOTOR VEHICLES"));
    }
}