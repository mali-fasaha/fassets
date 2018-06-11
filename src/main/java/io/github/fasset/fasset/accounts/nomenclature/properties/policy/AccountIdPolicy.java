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
package io.github.fasset.fasset.accounts.nomenclature.properties.policy;

import io.github.fasset.fasset.accounts.definition.Posting;
import io.github.fasset.fasset.accounts.definition.TransactionType;

/**
 * This interface acts as building blocks to how assets are identified in front-facing systems using simple structure
 * which is data that has to be extracted from the asset attributes themselves.
 * <br> For instance instead of inquiring the currency code to be given to an account number to track a given asset, instead
 * we are asking what currency code will be given to an account number that identifies an account of an asset whose monetary
 * attributes are denominated in currency whose ISO 4217 code is 'KES' or 'USD'.
 * <br> This means that this interface can be reused for other things and not necessarily assets, since the queries are less abstract
 * <br> Also this means that the accountIdPolicy which should exist in the business domain and currently only exists in my
 * mind as the developer can be changed changed from a single point being an implementation of this policy.
 * <br> The current implementation will be known as {@code AccountIdPolicyVersion1}. Upon emergence of more light this might
 * be scrapped and be implemented as {@code AccountIdPolicyVersion2}. The 'scrapping' is a matter of changing default implementation
 * in the dependency container.
 * <br> One thing is not likely to change though. The fact that most things are assessed, depreciated, analysed, classified from
 * the asset category perspective. Depreciation, reporting, verification, revaluation and maintenance policies are bound to
 * be formulated and implemented with regards to an assets category.
 * <br> Even as a developer well rehearsed with the problem space I do have or think to have attained all the light to clearly
 * perceive the problem space and its attributes, but what I do think is that the business domain can be for the yet unforeseeable
 * future, be bent to appreciate assets from the category perspective. Bigger companies would not lack the service-outlet guys,
 * the currency guy, year of manufacture and year of acquisition guys. But that would be beyond the reach of this application.
 * Such guys should see me on the side and we can talk about how much value we need to give to my time, because such flexibility
 * would require time to implement. Or we can still try and do in this rapid development cycle, without shooting our hypothetical foot
 * and yet this is without guarantees of actually not blowing the whole damn foot in the process. One such scenario would be in
 * a distributed environment ... oh! Here's another one, micro-services architecture. If you would think to implement mutating
 * configurations in these two you are destined to experience pain the heights of which you have not experienced before.
 *
 */
public interface AccountIdPolicy {

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
     * @param category of the asset for which we need a category code
     * @return The category code to be added to the account number sequence after the currency code
     */
    String generalLedgerCode(TransactionType transactionType, Posting posting, String category);

    /**
     * Using the provided category of an asset this method returns a specific nomenclature code for the
     * category. This is the code segment that typically follows the general ledger code in the
     * account number sequence
     *
     * @param transactionType The type of fixed asset transaction
     * @param posting Enum shows whether we are posting on the CREDIT side or the DEBIT side
     * @param category of the Asset for which we need a placeholder
     * @return String GL Id to be used for credit transactions
     */
    String accountPlaceHolder(TransactionType transactionType, Posting posting, String category);

    /**
     *
     * @param transactionType Type of transaction Enum
     * @param posting Enum shows whether we are posting on the CREDIT side or the DEBIT side
     * @param category of the asset for which we seek transaction account name
     * @return Name of the account
     */
    String accountName(TransactionType transactionType, Posting posting, String category);
}
