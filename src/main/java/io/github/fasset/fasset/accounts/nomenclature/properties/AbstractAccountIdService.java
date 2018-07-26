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

import io.github.fasset.fasset.accounts.nomenclature.properties.policy.AccountIdPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implements common methods of the AccountIdService interface
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public abstract class AbstractAccountIdService implements AccountIdService {

    private static final Logger log = LoggerFactory.getLogger(AbstractAccountIdService.class);

    // Using external configuration
    final AccountIdPolicy accountIdPolicy;

    AbstractAccountIdService(AccountIdPolicy accountIdPolicy) {

        this.accountIdPolicy = accountIdPolicy;
    }

    /**
     * {@inheritDoc}
     *
     * Using the currency code used in the fixed assets value at cost, the currency's ISO 4217 code, this method generates the unique code to be used in the account number sequence after the service
     * outlet code
     */
    @Override
    public String currencyCode(String currencyCode) {

        log.debug("Fetching currency code effective for the ISO4217 code : {}", currencyCode);

        return accountIdPolicy.currencyCode(currencyCode);
    }
}
