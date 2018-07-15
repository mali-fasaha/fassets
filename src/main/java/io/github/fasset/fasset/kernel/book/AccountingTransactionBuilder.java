package io.github.fasset.fasset.kernel.book;

import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.book.keeper.AccountingTransaction;
import io.github.fasset.fasset.kernel.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.kernel.book.keeper.util.MismatchedCurrencyException;
import io.github.ghacupha.time.point.SimpleDate;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;

/**
 * This class primarily creates a transaction for posting a given List of AccountingEntry objects
 */
@Component("accountingTransactionBUilder")
public class AccountingTransactionBuilder implements TransactionBuilder {

    @Override
    public Transaction createTransaction(List<AccountingEntry> entries) {

        Currency currency = entries.get(0).getCurrency();

        AccountingTransaction entryTransaction = AccountingTransaction.create("Tran01", SimpleDate.today(), currency);

        entries.forEach(entry -> {
            try {
                entryTransaction.addEntry(entry);
            } catch (MismatchedCurrencyException | ImmutableEntryException e) {
                e.printStackTrace();
            }
        });

        return entryTransaction;
    }
}
