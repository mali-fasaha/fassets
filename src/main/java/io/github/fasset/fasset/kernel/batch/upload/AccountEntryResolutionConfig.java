package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * This class proposes to configure a process by which fixed assets are recognozed as actual accounting
 * entry which enables the system to trace time-changing nature of accounts which we use to trace assets
 * for depreciation, acquisition and disposal purposes.
 * <br> We obtain fixed assets using a JPA pointer using the battle-tested fixedAssetItemReader
 * from the fileUploadBatchConfig bean.
 * <br> The reader will read assets from the database and the batch processor will then pass them to the
 * {@code AccountEntryResolutionProcessor} which will emit Lists of {@code AccountingEntry} which will then
 * be persisted into the data sink by the {@code AccountEntryResolutionWriter}
 */
@Configuration("accountEntryResolution")
public class AccountEntryResolutionConfig {

    @Autowired
    @Qualifier("fixedAssetItemReader")
    private ItemReader<FixedAsset> fixedAssetItemReader;

    @Autowired
    @Qualifier("accountEntryResolutionProcessor")
    private ItemProcessor<List<FixedAsset>, List<AccountingEntry>> accountEntryResolutionProcessor;

    @Autowired
    @Qualifier("accountEntryResolutionProcessor")
    private ItemWriter<List<AccountingEntry>> accountEntryResolutionWriter;
}
