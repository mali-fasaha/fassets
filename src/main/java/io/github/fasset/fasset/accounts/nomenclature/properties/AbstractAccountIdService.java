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

import io.github.fasset.fasset.kernel.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Implements common methods of the AccountIdService interface
 */
public abstract class AbstractAccountIdService implements AccountIdService {

    // Using external configuration
    final Properties accountConfigProperties;
    final Properties accountLabelProperties;

    AbstractAccountIdService(final String propertiesFile, final String labelsFile) {

        String source = propertiesFile == null ? "account-id" : propertiesFile;
        String labelSource = propertiesFile == null ? "account-label" : labelsFile;

        this.accountConfigProperties = PropertiesUtils.fetchConfigProperties(source);
        this.accountLabelProperties = PropertiesUtils.fetchConfigProperties(labelSource);
    }

    private static final Logger log = LoggerFactory.getLogger(AbstractAccountIdService.class);

    /**
     * Using the currency code used in the fixed assets value at cost, the currency's ISO 4217 code, this method generates
     * the unique code to be used in the account number sequence after the service outlet code
     *
     * @param currencyCode ISO 4217 currency code used to retrieve account number sequence code
     * @return Account number sequence code to follow the service outlet nomenclature
     */
    @Override
    public String currencyCode(String currencyCode) {

        log.debug("Fetching currency code for Currency : {}", currencyCode);

        String code = accountConfigProperties.getProperty(currencyCode);

        log.debug("Currency code for ISO4217 currency code value {} resolved as {}", currencyCode, code);

        return code;
    }
}
