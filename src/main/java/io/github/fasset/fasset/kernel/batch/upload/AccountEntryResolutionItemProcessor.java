package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.accounts.BatchEntryResolver;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This processor primarily converts lists of FixedAsset objects into lists of Account domain AccountingEntry objects
 */
@Component("accountEntryResolutionItemProcessor")
public class AccountEntryResolutionItemProcessor implements ItemProcessor<List<FixedAsset>, List<AccountingEntry>> {

    //todo log this class

    private BatchEntryResolver batchEntryResolver;

    @Autowired
    public AccountEntryResolutionItemProcessor(@Qualifier("batchEntryResolver") BatchEntryResolver batchEntryResolver) {
        this.batchEntryResolver = batchEntryResolver;
    }

    @Override
    public List<AccountingEntry> process(List<FixedAsset> fixedAssets) throws Exception {

        return batchEntryResolver.resolveEntries(fixedAssets);
    }
}
