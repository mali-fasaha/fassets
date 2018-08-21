package io.github.fasset.fasset.accounts;

import io.github.fasset.fasset.kernel.book.Transaction;
import io.github.fasset.fasset.kernel.book.keeper.Account;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.kernel.book.keeper.util.UnableToPostException;
import io.github.fasset.fasset.kernel.util.ConcurrentList;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.kernel.util.exception.AccountInterpreterException;
import io.github.fasset.fasset.model.FixedAsset;
import org.mali.fasaha.utils.Errors;
import org.mali.fasaha.utils.Throwing.Consumer;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class DefaultAccountInterpreter implements AccountInterpreter {

    private BatchEntryResolver batchEntryResolver;

    private Transaction transaction;

    /**
     * This method generates a List of Accounts from the assets provided.
     *
     * @param fixedAssets list containing assets to be interpreted into a list of Accounts
     * @return a list of accounts
     */
    @Override
    public List<Account> interpretAssets(final List<FixedAsset> fixedAssets) {

        List<AccountingEntry> entries = batchEntryResolver.resolveEntries(fixedAssets);

        entries.forEach(Errors.rethrow().wrap((Consumer<AccountingEntry>) transaction::addEntry));

        try {
            transaction.post();
        } catch (UnableToPostException | ImmutableEntryException e) {
            throw new AccountInterpreterException(entries, transaction, e);
        }

        return ConcurrentList.of(transaction.getEntries().stream().map(AccountingEntry::getAccount).distinct().collect(ImmutableListCollector.toImmutableFastList()));
    }

    /**
     * This method generates a list of Assets given a list of accounts
     *
     * @param accounts List of accounts to be translated into assets
     * @return list of fixed assets
     */
    @Override
    public List<FixedAsset> interpretAccounts(final List<Account> accounts) {

        List<AccountingEntry> entries = ConcurrentList.of(
            accounts.stream()
                    .map(Account::getEntries)
                    .flatMap(Collection::stream)
                    .sorted()
                    .collect(ImmutableListCollector.toImmutableFastList()));

        //todo List<FixedAssets> fixedAssets = batchAssetsResolver.resolveAssets(entries);
        //todo return fixedAssets;
        return null;
    }
}
