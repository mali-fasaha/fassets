package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.book.Transaction;
import io.github.fasset.fasset.kernel.book.TransactionBuilder;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.kernel.book.keeper.util.ImmutableEntryException;
import io.github.fasset.fasset.kernel.book.keeper.util.UnableToPostException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AccountingEntry objects generated by the batch processor are persisted to the data sink using a service
 * of our choice that converts those into Account objects and AccountingTransaction items
 */
@Component("accountEntryResolutionProcessor")
public class AccountEntryResolutionWriter implements ItemWriter<List<AccountingEntry>> {

    private final TransactionBuilder transactionBuilder;

    public AccountEntryResolutionWriter(TransactionBuilder transactionBuilder) {
        this.transactionBuilder = transactionBuilder;
    }

    @Override
    public void write(List<? extends List<AccountingEntry>> list) throws Exception {


        list.stream().map(transactionBuilder::createTransaction).forEach(entryTransaction -> {
            try {
                entryTransaction.post();
            } catch (UnableToPostException | ImmutableEntryException e) {
                e.printStackTrace();
            }
        });


    }
}
