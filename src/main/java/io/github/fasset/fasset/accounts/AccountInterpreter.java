package io.github.fasset.fasset.accounts;

import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.model.FixedAsset;

import java.util.List;

/**
 * This interpretter allows the client to use underlying assets and category architecture
 * while at the same time using the account infrastructure on the front-end
 */
public interface AccountInterpreter {

    /**
     * This method generates a List of Accounts from the assets provided.
     * @param fixedAssets list containing assets to be interpreted into a list of Accounts
     * @return a list of accounts
     */
    List<Account> interpretAssets(List<FixedAsset> fixedAssets);

    /**
     * This method generates a list of Assets given a list of accounts
     * @param accounts List of accounts to be translated into assets
     * @return list of fixed assets
     */
    List<FixedAsset> interpretAccounts(List<Account> accounts);
}
