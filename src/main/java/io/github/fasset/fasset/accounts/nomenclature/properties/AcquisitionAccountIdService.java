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

import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implements the {@code AccountIdService} using prestated values in a properties which are managed in
 * an internal map
 */
@Component("accountIdConfigurationPropertiesService")
public final class AcquisitionAccountIdService extends AbstractAccountIdService implements AccountIdService {

    //private Properties accountConfigProperties = PropertiesUtils.fetchProperties("account-nomenclature-config");
    private static final org.slf4j.Logger log = getLogger(AcquisitionAccountIdService.class);

    public AcquisitionAccountIdService(String propertiesFile) {

        super(propertiesFile);
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
    public String acquisitionGlId(String category) {

        log.debug("Fetching gl nomenclature for category: {}", category);

        return accountConfigProperties.getProperty(formatKey(category, "acquisition", "gl.id"));
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
    public String acquisitionGlCode(String category) {

        log.debug("Fetching gl code for category: {}", category);

        return accountConfigProperties.getProperty(formatKey(category));
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
