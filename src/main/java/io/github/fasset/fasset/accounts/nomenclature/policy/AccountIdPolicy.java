package io.github.fasset.fasset.accounts.nomenclature.policy;

/**
 * This interface acts as building blocks to how assets are identified in front-facing systems using simple structure
 * which is data that has to be extracted from the asset attributes themselves.
 * <br> For instance instead of inquiring the currency code to be given to an account number to track a given asset, instead
 * we are asking what currency code will be given to an account number that identifies an account of an asset whose monetary
 * attributes are denominated in currency whose ISO 4217 code is 'KES' or 'USD'.
 * <br> This means that this interface can be reused for other things and not necessarily assets, since the queries are less abstract
 * <br> Also this means that the accountIdPolicy which should exist in the business domain and currently only exists in my
 * mind as the developer can be changed changed from a single point being an implementation of this policy.
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
}
