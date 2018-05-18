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
package io.github.fasset.fasset.managers.id;

import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implements the {@code CreditAccountIDResolver} interface for the transaction that involves acquisition of assets
 *
 */
public class AcquisitionCreditAccountIDResolver implements CreditAccountIDResolver {

    private static final Logger log = getLogger(AcquisitionCreditAccountIDResolver.class);

    private AccountIdConfigurationService idConfigurationService;

    public AcquisitionCreditAccountIDResolver(AccountIdConfigurationService idConfigurationService) {
        this.idConfigurationService = idConfigurationService;
    }

    @Override
    public String resolveName(FixedAsset fixedAsset) {

        return "SUNDRY CREDITORS ACCOUNT";
    }

    @Override
    public String resolveNumber(FixedAsset fixedAsset) {

        return fixedAsset.getSolId() + currencyCode(fixedAsset) + glCode() + glId();
    }

    private String glCode() {

       return idConfigurationService.getAcquisitionCreditGlCode();
    }

    private String glId(){

        return idConfigurationService.getAcquisitionCreditGlId();
    }

    private String currencyCode(FixedAsset fixedAsset) {

        log.debug("Resolving general ledger code # for fixedAsset : {}", fixedAsset.getAssetDescription());

        return idConfigurationService.getCurrencyCode(fixedAsset.getPurchaseCost().getCurrency().getCurrencyCode());
    }

    /**
     * Resolve the name of a Contra account for a main account used for tracking the asset
     *
     * @param fixedAsset The asset for which we seek an account to track financially
     * @return The name of the contra account
     */
    @Override
    public String resolveContraAccountId(FixedAsset fixedAsset) {
        return resolveName(fixedAsset);
    }

    /**
     * Resolves the name of the appropriate general ledger that out to be used in this case for the
     * fixed assets. The general ledger is taken to be one hierarchy higher than an account. This is
     * being done by the accountIDResolver as the general-ledger id has something to do with the id
     * of the account itself
     *
     * @param fixedAsset For which we need a general ledger
     * @return The ID of the general ledger
     */
    @Override
    public String resolveGeneralLedgerName(FixedAsset fixedAsset) {

        return "SUNDRY CREDITORS";
    }

    /**
     * The category is of a lower hierarchy than an account yet for the account to be representative
     * of fixed assets comprehensively this out to be mandated as part of the account id or at least
     * as a field in an account that can be tracked
     *
     * @param fixedAsset For which we need category id
     * @return The id of the category
     */
    @Override
    public String resolveCategoryId(FixedAsset fixedAsset) {

        return fixedAsset.getCategory().toUpperCase();
    }
}
