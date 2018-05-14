/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.managers;

import io.github.fasset.fasset.book.keeper.Account;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.stereotype.Component;

/**
 * This object can dictate appropriate account for tracking a given {@code FixedAsset} item. This is obtained from a manually configured
 * properties
 */
@Component("chartOfAccounts")
public class ChartOfAccounts {

    /**
     * Generates appropriate Account for the asset passed in the parameter, when we are posting Acquisition
     *
     * @param fixedAsset for which we seek an appropriate Account
     * @return Account appropriate for the recording of transaction for the parameter
     * fixedAsset
     */
    Account getAcquistionDebitAccount(FixedAsset fixedAsset) {
        return null;
    }

    /**
     * Generates appropriate credit Account for the asset passed in the parameter, when we are posting Acquisition
     *
     * @param fixedAsset for which we seek an appropriate Account
     * @return Account appropriate for the recording of transaction for the parameter
     * fixedAsset
     */
    Account getAcquisitionCreditAccount(FixedAsset fixedAsset) {
        return null;
    }
}
