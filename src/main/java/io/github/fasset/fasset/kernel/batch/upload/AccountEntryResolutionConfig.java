/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * This class proposes to configure a process by which fixed assets are recognozed as actual accounting entry which enables the system to trace time-changing nature of accounts which we use to trace
 * assets for depreciation, acquisition and disposal purposes. <br> We obtain fixed assets using a JPA pointer using the battle-tested fixedAssetItemReader from the fileUploadBatchConfig bean. <br>
 * The reader will read assets from the database and the batch processor will then pass them to the {@code AccountEntryResolutionProcessor} which will emit Lists of {@code AccountingEntry} which will
 * then be persisted into the data sink by the {@code AccountEntryResolutionWriter}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Configuration("accountEntryResolution")
public class AccountEntryResolutionConfig {

    private ItemReader<List<FixedAsset>> fixedAssetItemsReader;

    private ItemProcessor<List<FixedAsset>, List<AccountingEntry>> accountEntryResolutionProcessor;

    private ItemWriter<List<AccountingEntry>> accountEntryResolutionWriter;

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    /**
     * <p>Constructor for AccountEntryResolutionConfig.</p>
     *
     * @param fixedAssetItemsReader a {@link org.springframework.batch.item.ItemReader} object.
     * @param accountEntryResolutionProcessor a {@link org.springframework.batch.item.ItemProcessor} object.
     * @param accountEntryResolutionWriter a {@link org.springframework.batch.item.ItemWriter} object.
     * @param jobBuilderFactory a {@link org.springframework.batch.core.configuration.annotation.JobBuilderFactory} object.
     * @param stepBuilderFactory a {@link org.springframework.batch.core.configuration.annotation.StepBuilderFactory} object.
     */
    @Autowired
    public AccountEntryResolutionConfig(@Qualifier("fixedAssetItemsReader") ItemReader<List<FixedAsset>> fixedAssetItemsReader,
                                        @Qualifier("accountEntryResolutionItemProcessor") ItemProcessor<List<FixedAsset>, List<AccountingEntry>> accountEntryResolutionProcessor,
                                        @Qualifier("accountEntryResolutionProcessor") ItemWriter<List<AccountingEntry>> accountEntryResolutionWriter,
                                        JobBuilderFactory jobBuilderFactory,
                                        StepBuilderFactory stepBuilderFactory) {
        this.fixedAssetItemsReader = fixedAssetItemsReader;
        this.accountEntryResolutionProcessor = accountEntryResolutionProcessor;
        this.accountEntryResolutionWriter = accountEntryResolutionWriter;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * <p>accountEntryResolutionJob.</p>
     *
     * @param listener a {@link io.github.fasset.fasset.kernel.batch.upload.AccountEntryResolutionExecutionListener} object.
     * @return a {@link org.springframework.batch.core.Job} object.
     */
    @Bean("accountEntryResolutionJob")
    public Job accountEntryResolutionJob(@Qualifier("accountEntryResolutionExecutionListener") AccountEntryResolutionExecutionListener listener) {
        return jobBuilderFactory.get("accountEntryResolutionJob")
                                .preventRestart()
                                .incrementer(new RunIdIncrementer())
                                .listener(listener)
                                .flow(generateAccountEntriesStep())
                                .end()
                                .build();
    }

    /**
     * <p>generateAccountEntriesStep.</p>
     *
     * @return a {@link org.springframework.batch.core.Step} object.
     */
    @Bean("generateAccountEntriesStep")
    public Step generateAccountEntriesStep() {
        return stepBuilderFactory.get("generateAccountEntriesStep").<List<FixedAsset>, List<AccountingEntry>>chunk(10).reader(fixedAssetItemsReader)
                                                                                                                      .processor(accountEntryResolutionProcessor)
                                                                                                                      .writer(accountEntryResolutionWriter)
                                                                                                                      .build();
    }
}
