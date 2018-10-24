package io.github.fasset.fasset.accounts;

import io.github.fasset.fasset.kernel.book.Transaction;
import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.kernel.book.keeper.util.MismatchedCurrencyException;
import io.github.fasset.fasset.kernel.book.keeper.util.UnableToPostException;
import io.github.fasset.fasset.kernel.util.ConcurrentList;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.kernel.util.exception.AccountInterpreterException;
import io.github.fasset.fasset.model.FixedAsset;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * This object is to be used to move between Accounts and Assets representations in the back-end making it easier to reason about assets and acquisition dates.
 */
public class DefaultAccountInterpreter implements AccountInterpreter {

    //todo implement thread-safe double-checked locking singleton

    private final BatchEntryResolver batchEntryResolver;
    private final Transaction transaction;

    private DefaultAccountInterpreter(Transaction transaction, BatchEntryResolver batchEntryResolver) {
        this.transaction = transaction;
        this.batchEntryResolver = batchEntryResolver;
    }

    //@formatter: off

    /**
     * This method generates a List of Accounts from the assets provided.
     *
     * @param fixedAssets list containing assets to be interpreted into a list of Accounts
     * @return a list of accounts
     */
    @Override
    public List<Account> interpretAssets(final List<FixedAsset> fixedAssets) {

        List<AccountingEntry> entries = batchEntryResolver.resolveEntries(fixedAssets);

        entries.forEach(entry -> {
            try {
                transaction.addEntry(entry);
            } catch (MismatchedCurrencyException | ImmutableEntryException e) {
                e.printStackTrace();
            }
        });

        try {
            transaction.post();
        } catch (UnableToPostException | ImmutableEntryException e) {
            throw new AccountInterpreterException(entries, transaction, e);
        }

        return ConcurrentList.of(transaction.getEntries().stream().map(AccountingEntry::getAccount).distinct().collect(ImmutableListCollector.toImmutableFastList()));
    }
    // @formatter: on

    //@formatter: off

    /**
     * This method generates a list of Assets given a list of accounts. Before it is run, there will be a need to check for duplication with already existing assets
     *
     * @param accounts List of accounts to be translated into assets
     * @return list of fixed assets
     */
    @Override
    public List<FixedAsset> interpretAccounts(final List<Account> accounts) {

        List<AccountingEntry> entries = ConcurrentList.of(accounts.stream().map(Account::getEntries).flatMap(Collection::stream).sorted().collect(ImmutableListCollector.toImmutableFastList()));

        //todo return batchAssetsResolver.resolveAssets(entries);
        return null;
    }
    // @formatter: on
}
