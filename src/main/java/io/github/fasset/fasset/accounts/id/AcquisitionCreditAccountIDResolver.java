/**
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.accounts.id;

import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implements the {@code CreditAccountIDResolver} interface for the transaction that involves acquisition of assets. In summary
 * one can expect that all acquisition transaction would be posted to the Sundry Creditor's account abstracting this application
 * from the knowledge of to which stakeholders settlements as this varies to a large degree depending on the domain of a given
 * tax authority, the same amount being split between vendor and tax authority in varying ratios. This is complicated situation to
 * master that requires more than one account, the effect of which could hardly be justified by the effort.
 *
 * @author edwin.njeru
 */
@Component("creditAccountIDResolver")
public class AcquisitionCreditAccountIDResolver implements CreditAccountIDResolver {

    private static final Logger log = getLogger(AcquisitionCreditAccountIDResolver.class);

    private AccountIdConfigurationService idConfigurationService;

    @Autowired
    public AcquisitionCreditAccountIDResolver(@Qualifier("accountIdConfigurationPropertiesService") AccountIdConfigurationService idConfigurationService) {
        this.idConfigurationService = idConfigurationService;
    }

    @Override
    public String resolveName(FixedAsset fixedAsset) {

        log.debug("Resolving the account name for the account to be credited in the acquisition of asset : {}", fixedAsset.getAssetDescription());

        return "SUNDRY CREDITORS ACCOUNT";
    }

    @Override
    public String resolveNumber(FixedAsset fixedAsset) {

        log.debug("Resolving the account number for the account to be credited in the acquisition of asset : {}", fixedAsset.getAssetDescription());

        //TODO Abstract account number resolution
        return fixedAsset.getSolId() + currencyCode(fixedAsset) + glCode() + glId();
    }

    private String glCode() {

        log.debug("Resolving the account GL Code for the account to be credited in the acquisition of asset ");

        return idConfigurationService.getAcquisitionCreditGlCode();
    }

    private String glId() {

        log.debug("Resolving the account GL Code for the account to be credited in the acquisition of asset ");

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

        log.debug("Resolving the account GL Code for the account to be credited in the acquisition of asset : {}", fixedAsset.getAssetDescription());

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
