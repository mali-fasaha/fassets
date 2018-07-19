package io.github.fasset.fasset.kernel.book;

import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.book.keeper.AccountingTransaction;
import io.github.fasset.fasset.kernel.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.kernel.book.keeper.util.MismatchedCurrencyException;
import io.github.ghacupha.time.point.SimpleDate;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class primarily creates a transaction for posting a given List of AccountingEntry objects
 */
@Component("accountingTransactionBuilder")
public class AccountingTransactionBuilder implements TransactionBuilder {

    private static final Logger log = getLogger(AccountingTransactionBuilder.class);

    private int counter = 0;

    @Override
    public Transaction createTransaction(List<AccountingEntry> entries) {

        log.debug("The accountingTransactionBuilder is creating transaction for {} entries", entries);

        Currency currency = entries.get(0)
                                   .getCurrency();

        // todo create sequential naming for transactions
        AccountingTransaction entryTransaction = AccountingTransaction.create(format("Tran%s", ++counter), SimpleDate.today(), currency);

        entries.forEach(entry -> {
            try {
                log.trace("Adding entry : {} to transaction {}", entry, entryTransaction);
                entryTransaction.addEntry(entry);
            } catch (MismatchedCurrencyException | ImmutableEntryException e) {
                e.printStackTrace();
            }
        });

        return entryTransaction;
    }
}
