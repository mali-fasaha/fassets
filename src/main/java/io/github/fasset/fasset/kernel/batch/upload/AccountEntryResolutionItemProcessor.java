package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.accounts.BatchEntryResolver;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This processor primarily converts lists of FixedAsset objects into lists of Account domain AccountingEntry objects
 */
@Component("accountEntryResolutionItemProcessor")
public class AccountEntryResolutionItemProcessor implements ItemProcessor<List<FixedAsset>, List<AccountingEntry>> {

    private static final Logger log = getLogger(AccountEntryResolutionItemProcessor.class);

    private BatchEntryResolver batchEntryResolver;

    @Autowired
    public AccountEntryResolutionItemProcessor(@Qualifier("batchEntryResolver") BatchEntryResolver batchEntryResolver) {
        this.batchEntryResolver = batchEntryResolver;
    }

    @Override
    public List<AccountingEntry> process(List<FixedAsset> fixedAssets) throws Exception {

        log.trace("Creating entries for posting {} fixedAssets", fixedAssets);

        return batchEntryResolver.resolveEntries(fixedAssets);
    }
}
