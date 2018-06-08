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

import io.github.fasset.fasset.accounts.definition.TransactionType;
import io.github.fasset.fasset.accounts.definition.Posting;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static io.github.fasset.fasset.accounts.definition.AccountNumberSegment.GENERAL_LEDGER_CODE;
import static io.github.fasset.fasset.accounts.definition.AccountNumberSegment.PLACE_HOLDER;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implements the {@code AccountIdService} using pre stated values in a properties which are managed in
 * an internal map
 * <br> This implementation provides identities for accounts specific to acquisition transactions, in this
 * case typical it is expected that the debit account would be the asset account tracking its category,
 * and the credit account would be the sundry creditor's account
 */
@Component("accountIdConfigurationPropertiesService")
public final class AcquisitionAccountIdService extends AbstractAccountIdService implements AccountIdService {

    //private Properties accountConfigProperties = PropertiesUtils.fetchProperties("account-nomenclature-config");
    private static final Logger log = getLogger(AcquisitionAccountIdService.class);

    public AcquisitionAccountIdService(String propertiesFile, String labelsFile) {

        super(propertiesFile, labelsFile);
    }

    /**
     * Using the category of an asset this method returns the generic nomenclature code for the category, which in
     * accordance to the Account nomenclature and hierarchy policy version 1.0 follows after the currency
     * code in the account number sequence
     *
     * @param transactionType This is the type of fixed asset transaction and could ACQUISITION, DEPRECIATION among others
     * @param posting The direction which we are posting. This could be DEBIT or CREDIT
     * @param fixedAsset From which we inquire the category of the asset for which we need a category code
     * @return The category code to be added to the account number sequence after the currency code
     */
    @Override
    public String generalLedgerCode(TransactionType transactionType, Posting posting, FixedAsset fixedAsset) {

        log.debug("Fetching account ledger code transaction: {}, of asset {}, posting on the {} side", transactionType, fixedAsset, posting);

        String key = KeyFormatter.formatKey(fixedAsset.getCategory(), transactionType, posting, GENERAL_LEDGER_CODE); // e.g "sundry.acquisition. credit.general-ledger-code"

        log.debug("Fetching generalLedgerCode for an account whose key is encoded as {}", key);

        String glcode = accountConfigProperties.getProperty(key);

        log.debug("GL code for posting {} for the asset {} on the {} side, resolved as {}", transactionType, fixedAsset, posting, glcode);

        return glcode;
    }

    /**
     * @return String GL Id to be used for credit transactions
     */
    @Override
    public String accountPlaceHolder(TransactionType transactionType, Posting posting, FixedAsset fixedAsset) {

        log.debug("Resolving credit posting account for transaction type {}, for asset : {}", transactionType, fixedAsset);

        String key = KeyFormatter.formatKey(fixedAsset.getCategory(), transactionType, posting, PLACE_HOLDER); // e.g "sundry.acquisition. credit.placeHolder"

        log.debug("Resolving placeholder for the key, {}", key);

        return accountConfigProperties.getProperty(key);
    }

    /**
     * @param transactionType Type of transaction Enum
     * @param fixedAsset      Asset for which we seek transaction account name
     * @return Name of the account
     */
    @Override
    public String accountName(TransactionType transactionType, Posting posting, FixedAsset fixedAsset) {

        log.debug("Resolving credit posting account for transaction type {}, for asset : {}", transactionType, fixedAsset);

        String key = KeyFormatter.formatKey(fixedAsset.getCategory(), transactionType, posting);

        log.debug("Fetching account label for the key: {}", key);

        String accountLabel = accountLabelProperties.getProperty(key);//e.g computers.acquisition.credit

        log.debug("Credit posting account label for {} of the asset {} resolved to be {}, using the key: {}", transactionType, fixedAsset, accountLabel, key);

        return accountLabel;
    }

}
