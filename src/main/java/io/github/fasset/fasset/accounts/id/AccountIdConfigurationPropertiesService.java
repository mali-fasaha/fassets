/**
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.accounts.id;

import io.github.fasset.fasset.kernel.util.PropertiesUtils;

import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implements the {@code AccountIdConfigurationService} using prestated values in a properties which are managed in
 * an internal map
 */
public class AccountIdConfigurationPropertiesService implements AccountIdConfigurationService {

    //private Properties accountConfigProperties = PropertiesUtils.fetchProperties("account-id-config");
    private static final org.slf4j.Logger log = getLogger(AccountIdConfigurationPropertiesService.class);

    // Using external configuration
    private Properties accountConfigProperties;

    public AccountIdConfigurationPropertiesService(String propertiesFile) {

        accountConfigProperties = PropertiesUtils.fetchProperties("account-id-config");

    }

    /**
     * Using the provided category of an asset this method returns a specific id code for the
     * category. This is the code segment that typically follows the general ledger code in the
     * account number sequence
     *
     * @param category The category of the asset for which we need a category id
     * @return The category id to be added to the account number sequence after the general ledger code
     */
    @Override
    public String acquisitionGlId(String category) {

        log.debug("Fetching gl id for category: {}", category);

        return accountConfigProperties.getProperty(formatKey(category, "acquisition", "gl.id"));
    }

    /**
     * Using the category of an asset this method returns the generic id code for the category, which in
     * accordance to the Account nomenclature and hierarchy policy version 1.0 follows after the currency
     * code in the account number sequence
     *
     * @param category The category of the asset for which we need a category code
     * @return The category code to be added to the account number sequence after the currency code
     */
    @Override
    public String acquisitionGlCode(String category) {

        log.debug("Fetching gl code for category: {}", category);

        return accountConfigProperties.getProperty(formatKey(category, "acquisition", "gl.code"));
    }

    private String formatKey(String propertyKey, String transaction, String element) {
        return String.format("%s.%s.%s", propertyKey.toLowerCase(), transaction, element).replace(" ", "-").replace("&", "and");
    }

    /**
     * Using the currency code used in the fixed assets value at cost, the currency's ISO 4217 code, this method generates
     * the unique code to be used in the account number sequence after the service outlet code
     *
     * @param currencyCode ISO 4217 currency code used to retrieve account number sequence code
     * @return Account number sequence code to follow the service outlet id
     */
    @Override
    public String getCurrencyCode(String currencyCode) {

        log.debug("Fetching currency code for Currency : {}", currencyCode);

        return accountConfigProperties.getProperty(currencyCode);
    }

    /**
     * @return String GL Code to be used for credit transactions
     */
    @Override
    public String getAcquisitionCreditGlCode() {

        log.debug("Fetching credit account for acquisitions...");

        return accountConfigProperties.getProperty("sundry.acquisition.gl.code");
    }

    /**
     * @return String GL Id to be used for credit transactions
     */
    @Override
    public String getAcquisitionCreditGlId() {

        return accountConfigProperties.getProperty("sundry.acquisition.gl.id");
    }
}
