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

/**
 * This service reads data from a configuration properties file and maintains a map which is used to provide
 * <p>
 * properties required to implement the account nomenclature and hierarchy policy version 1.0.
 * <br> Th main philosohy here is that we can be able to generate account names if we know which asset we are transacting
 */
public interface AccountIdService {

    /**
     * Using the currency code used in the fixed assets value at cost, the currency's ISO 4217 code, this method generates
     * the unique code to be used in the account number sequence after the service outlet code
     *
     * @param currencyCode ISO 4217 currency code used to retrieve account number sequence code
     * @return Account number sequence code to follow the service outlet nomenclature
     */
    String currencyCode(String currencyCode);

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
    String generalLedgerCode(TransactionType transactionType, Posting posting, FixedAsset fixedAsset);

    /**
     * Using the provided category of an asset this method returns a specific nomenclature code for the
     * category. This is the code segment that typically follows the general ledger code in the
     * account number sequence.
     *
     * @param fixedAsset for which seek an account placeHolder
     * @param posting Whether we are posting on the debit side or the credit side
     * @param transactionType The type of transaction for the fixed asset
     * @return String GL Id to be used for credit transactions
     */
    String accountPlaceHolder(TransactionType transactionType, Posting posting, FixedAsset fixedAsset);

    /**
     *
     * @param transactionType Type of transaction Enum
     * @param fixedAsset Asset for which we seek transaction account name
     * @return Name of the account
     */
    String accountName(TransactionType transactionType, Posting posting, FixedAsset fixedAsset);
}
