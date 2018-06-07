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
package io.github.fasset.fasset.config;

import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdConfigurationPropertiesService;
import io.github.fasset.fasset.accounts.nomenclature.properties.AccountIdConfigurationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration beans used for configuration of resolver objects
 */
@Configuration
public class AccountsConfig {

    @Bean("accountIdConfigurationPropertiesService")
    public AccountIdConfigurationService accountIdConfigurationPropertiesService() {

        return new AccountIdConfigurationPropertiesService("account-nomenclature-config");
    }
}
