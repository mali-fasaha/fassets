package io.github.fasset.fasset.accounts.nomenclature.policy;

import io.github.fasset.fasset.accounts.nomenclature.properties.FileAccountIdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.fasset.fasset.accounts.definition.Posting.CREDIT;
import static io.github.fasset.fasset.accounts.definition.Posting.DEBIT;
import static io.github.fasset.fasset.accounts.definition.TransactionType.ACQUISITION;
import static org.junit.jupiter.api.Assertions.*;

class AccountIdPolicyVersion1Test {

    private AccountIdPolicy accountIdPolicy;

    @BeforeEach void setUp() {
        accountIdPolicy = new AccountIdPolicyVersion1(
            new FileAccountIdService("account-id", "account-label"));
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
    }

    @Test
    void accountPlaceHolder() {

        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, "FURNITURE & FITTINGS"));
        assertEquals("015", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, "FURNITURE & FITTINGS"));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, lenovo));
        assertEquals("013", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, lenovo));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, radio));
        assertEquals("014", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, radio));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, zemana));
        assertEquals("017", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, zemana));
        assertEquals("001", accountIdPolicy.accountPlaceHolder(ACQUISITION, CREDIT, kca));
        assertEquals("016", accountIdPolicy.accountPlaceHolder(ACQUISITION, DEBIT, kca));
    }

    @Test
    void accountName() {
    }
}