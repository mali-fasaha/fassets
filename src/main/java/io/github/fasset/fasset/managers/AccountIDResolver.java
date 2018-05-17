/**
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
package io.github.fasset.fasset.managers;

import io.github.fasset.fasset.model.FixedAsset;

/**
 * Generates the name of an existing account, if one exists, or the name of one to be created
 */
public interface AccountIDResolver {

    String resolveName(FixedAsset fixedAsset);

    String resolveNumber(FixedAsset fixedAsset);

    /**
     * Resolve the name of a Contra account for a main account used for tracking the asset
     * @param fixedAsset The asset for which we seek an account to track financially
     * @return The name of the contra account
     */
    String resolveContraAccountId(FixedAsset fixedAsset);

    /**
     * Resolves the name of the appropriate general ledger that out to be used in this case for the
     * fixed assets. The general ledger is taken to be one hierarchy higher than an account. This is
     * being done by the accountIDResolver as the general-ledger id has something to do with the id
     * of the account itself
     * @param fixedAsset For which we need a general ledger
     * @return The ID of the general ledger
     */
    String resolveGeneralLedgerId(FixedAsset fixedAsset);

    /**
     * The category is of a lower hierarchy than an account yet for the account to be representative
     * of fixed assets comprehensively this out to be mandated as part of the account id or at least
     * as a field in an account that can be tracked
     * @param fixedAsset For which we need category id
     * @return The id of the category
     */
    String resolveCategoryId(FixedAsset fixedAsset);
}
