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
package io.github.fasset.fasset.accounts.nomenclature.policy;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import io.github.fasset.fasset.accounts.definition.Posting;
import io.github.fasset.fasset.accounts.definition.TransactionType;
import io.github.fasset.fasset.accounts.nomenclature.properties.KeyFormatter;
import io.github.fasset.fasset.kernel.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static io.github.fasset.fasset.accounts.definition.AccountNumberSegment.PLACE_HOLDER;

/**
 * Version1 implementation of the {@code AccountIdPolicy}
 */
public class AccountIdPolicyVersion1 implements AccountIdPolicy {

    private static final Logger log = LoggerFactory.getLogger(AccountIdPolicyVersion1.class);

    private final Properties accountConfigProperties;

    private final Properties accountLabels;

    public AccountIdPolicyVersion1(String accountIdProperties, String accountLabels) {
        String accountProperties = accountIdProperties == null ? "account-id" : accountIdProperties;
        String labels = accountLabels == null ? "account-label" : accountLabels;

        this.accountConfigProperties = PropertiesUtils.fetchConfigProperties(accountProperties);
        this.accountLabels = PropertiesUtils.fetchConfigProperties(labels);

    }

    /**
     * Using the currency code used in the fixed assets value at cost, the currency's ISO 4217 code, this method generates
     * the unique code to be used in the account number sequence after the service outlet code
     *
     * @param iso4217Code ISO 4217 currency code used to retrieve account number sequence code
     * @return Account number sequence code to follow the service outlet nomenclature
     */
    @Override
    public String currencyCode(String iso4217Code) {

        log.trace("Fetching currency code for ISO4217 currency code provided as : {}", iso4217Code);

        String code = accountConfigProperties.getProperty(iso4217Code);

        log.trace("Code for ISO4217 currency code : {} resolved as {}", iso4217Code, code);

        return code;
    }

    /**
     * Using the category of an asset this method returns the generic nomenclature code for the category, which in
     * accordance to the Account nomenclature and hierarchy policy version 1.0 follows after the currency
     * code in the account number sequence
     *
     * @param transactionType This is the type of fixed asset transaction and could ACQUISITION, DEPRECIATION among others
     * @param posting         The direction which we are posting. This could be DEBIT or CREDIT
     * @param category        of the asset for which we need a category code
     * @return The category code to be added to the account number sequence after the currency code
     */
    @Override
    public String generalLedgerCode(TransactionType transactionType, Posting posting, String category) {
        return null;
    }

    /**
     * Using the provided category of an asset this method returns a specific nomenclature code for the
     * category. This is the code segment that typically follows the general ledger code in the
     * account number sequence
     *
     * @param transactionType
     * @param posting
     * @param category
     * @return String GL Id to be used for credit transactions
     */
    @Override
    public String accountPlaceHolder(TransactionType transactionType, Posting posting, String category) {

        log.debug("Resolving account placeHolder for {} transaction, posting on the {} side for {} category", transactionType, posting, category);

        String key = KeyFormatter.formatKey(category, transactionType, posting, PLACE_HOLDER);

        log.debug("Resolving placeHolder for the key, {}", key);

        return accountConfigProperties.getProperty(key);
    }

    /**
     * @param transactionType Type of transaction Enum
     * @param posting
     * @param category        of the asset for which we seek transaction account name
     * @return Name of the account
     */
    @Override
    public String accountName(TransactionType transactionType, Posting posting, String category) {
        return null;
    }
}
