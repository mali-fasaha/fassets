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
     * Using the provided category of an asset this method returns a specific nomenclature code for the
     * category. This is the code segment that typically follows the general ledger code in the
     * account number sequence
     *
     * @param category The category of the asset for which we need a category nomenclature
     * @return The category nomenclature to be added to the account number sequence after the general ledger code
     */
    @Override
    public String debitAccountPlaceHolder(String category) {

        log.debug("Fetching gl nomenclature for category: {}", category);

        String placeHolder = accountConfigProperties.getProperty(formatKey(category, "acquisition", "placeholder"));

        log.debug("Place holder for category: {} resolved as {}", category, placeHolder);

        return placeHolder;
    }

    /**
     * Using the category of an asset this method returns the generic nomenclature code for the category, which in
     * accordance to the Account nomenclature and hierarchy policy version 1.0 follows after the currency
     * code in the account number sequence
     *
     * @param category The category of the asset for which we need a category code
     * @return The category code to be added to the account number sequence after the currency code
     */
    @Override
    public String debitGeneralLedgerCode(String category) {

        log.debug("Fetching gl code for category: {}", category);

        String glCode = accountConfigProperties.getProperty(formatKey(category));

        log.debug("General ledger code # for category : {} resolved as {}", category, glCode);

        return glCode;
    }

    private String formatKey(String propertyKey) {
        return formatKey(propertyKey, "acquisition", "gl.code");
    }

    private String formatKey(String propertyKey, String element) {
        return formatKey(propertyKey, "acquisition", element);
    }



    /**
     * @return String GL Code to be used for credit transactions
     */
    @Override
    public String creditGeneralLedgerCode() {

        log.debug("Fetching credit account for acquisitions...");

        return accountConfigProperties.getProperty("sundry.acquisition.gl.code");
    }

    /**
     * @return String GL Id to be used for credit transactions
     */
    @Override
    public String accountPlaceHolder(TransactionType transactionType, Posting posting, FixedAsset fixedAsset) {

        log.debug("Resolving credit posting account for transaction type {}, for asset : {}", transactionType, fixedAsset);

        String key = formatKey(fixedAsset.getCategory(), transactionType, posting, PLACE_HOLDER); // e.g "sundry.acquisition. credit.placeHolder"

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

        String key = formatKey(fixedAsset.getCategory(), transactionType, posting);

        log.debug("Fetching account label for the key: {}", key);

        String accountLabel = accountLabelProperties.getProperty(key);//e.g computers.acquisition.credit

        log.debug("Credit posting account label for {} of the asset {} resolved to be {}, using the key: {}", transactionType, fixedAsset, accountLabel, key);

        return accountLabel;
    }

}
