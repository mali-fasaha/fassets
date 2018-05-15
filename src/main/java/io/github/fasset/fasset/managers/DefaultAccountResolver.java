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
import io.github.fasset.fasset.book.keeper.unit.time.SimpleDate;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Currency;

import static io.github.fasset.fasset.book.keeper.balance.AccountSide.DEBIT;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This object can dictate appropriate account for tracking a given {@code FixedAsset} item. This is obtained from a manually configured
 * properties. While the account must be appropriate to the circumstances of the transaction (i.e. whether acquisition, transfer,disposal)
 * the account must match the fixedAsset's currency amount, the service outlet, and the fixedAsset's category. Ideally the account names
 * are pre-stated in a properties file. But that is to implemented another time, since implementing that today messes up the
 * container configuration which recognizes this object as a singleton called "chartOfAccounts".
 * The Object must lookup whether the account exists before generating a new Account for the fixedAsset. Which bring a new problem,
 * the resolution of the opening date. Will it be pegged to the purchase date of the asset
 */
@Component("accountResolver")
public class DefaultAccountResolver implements AccountResolver {

    private static final org.slf4j.Logger log = getLogger(DefaultAccountResolver.class);

    private AccountIDResolver accountIDResolver;

    public DefaultAccountResolver(AccountIDResolver accountIDResolver) {
        this.accountIDResolver = accountIDResolver;
    }

    /**
     * Generates appropriate Account for the asset passed in the parameter, when we are posting Acquisition
     *
     * @param fixedAsset for which we seek an appropriate Account
     * @return Account appropriate for the recording of transaction for the parameter
     * fixedAsset
     */
    public Account getAcquisitionDebitAccount(FixedAsset fixedAsset) {

        log.debug("Getting acquisition debit account for asset : {}", fixedAsset.getAssetDescription());

        return new Account(accountIDResolver.resolveName(fixedAsset), accountIDResolver.resolveNumber(fixedAsset), DEBIT,
            Currency.getInstance(fixedAsset.getPurchaseCost().getCurrency().getCurrencyCode()), SimpleDate.ofLocal(fixedAsset.getPurchaseDate()));
    }

    /**
     * Generates appropriate credit Account for the asset passed in the parameter, when we are posting Acquisition
     *
     * @param fixedAsset for which we seek an appropriate Account
     * @return Account appropriate for the recording of transaction for the parameter
     * fixedAsset
     */
    public Account getAcquisitionCreditAccount(FixedAsset fixedAsset) {

        log.debug("Getting acquisition debit account for asset : {}", fixedAsset.getAssetDescription());

        return new Account(accountIDResolver.resolveName(fixedAsset), accountIDResolver.resolveNumber(fixedAsset), DEBIT,
            Currency.getInstance(fixedAsset.getPurchaseCost().getCurrency().getCurrencyCode()), SimpleDate.ofLocal(fixedAsset.getPurchaseDate()));
    }
}
