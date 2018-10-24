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

import io.github.fasset.fasset.kernel.excel.ExcelMapperService;
import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.FixedAssetDTO;
import io.github.fasset.fasset.model.NetBookValue;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * Configuration for fileUpload job
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Configuration("fileUploadBatchConfig")
public class FileUploadBatchConfig {

    /*@Value("#{jobParameters['fileName']}")
    public static final String filePath = null;*/

    @Value("#{jobParameters['fileName']}")
    private static String filePath;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ExcelMapperService excelMapperService;
    private final ExcelItemProcessor excelItemProcessor;
    private final ExcelItemWriter excelItemWriter;
    private final EntityManagerFactory entityManagerFactory;
    private final FixedAssetAccruedDepreciationProcessor fixedAssetAccruedDepreciationProcessor;
    private final AccruedDepreciationWriter accruedDepreciationWriter;
    private final NetBookValueWriter netBookValueWriter;
    private final FixedAssetNetBookValueProcessor fixedAssetNetBookValueProcessor;

    /**
     * <p>Constructor for FileUploadBatchConfig.</p>
     *
     * @param jobBuilderFactory                      a {@link org.springframework.batch.core.configuration.annotation.JobBuilderFactory} object.
     * @param stepBuilderFactory                     a {@link org.springframework.batch.core.configuration.annotation.StepBuilderFactory} object.
     * @param excelMapperService                     a {@link io.github.fasset.fasset.kernel.excel.ExcelMapperService} object.
     * @param excelItemProcessor                     a {@link io.github.fasset.fasset.kernel.batch.upload.ExcelItemProcessor} object.
     * @param excelItemWriter                        a {@link io.github.fasset.fasset.kernel.batch.upload.ExcelItemWriter} object.
     * @param entityManagerFactory                   a {@link javax.persistence.EntityManagerFactory} object.
     * @param fixedAssetAccruedDepreciationProcessor a {@link io.github.fasset.fasset.kernel.batch.upload.FixedAssetAccruedDepreciationProcessor} object.
     * @param accruedDepreciationWriter              a {@link io.github.fasset.fasset.kernel.batch.upload.AccruedDepreciationWriter} object.
     * @param netBookValueWriter                     a {@link io.github.fasset.fasset.kernel.batch.upload.NetBookValueWriter} object.
     * @param fixedAssetNetBookValueProcessor        a {@link io.github.fasset.fasset.kernel.batch.upload.FixedAssetNetBookValueProcessor} object.
     */
    @Autowired
    public FileUploadBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, @Qualifier("excelMapperService") ExcelMapperService excelMapperService,
                                 @Qualifier("excelItemProcessor") ExcelItemProcessor excelItemProcessor, @Qualifier("excelItemWriter") ExcelItemWriter excelItemWriter,
                                 EntityManagerFactory entityManagerFactory,
                                 @Qualifier("fixedAssetAccruedDepreciationProcessor") FixedAssetAccruedDepreciationProcessor fixedAssetAccruedDepreciationProcessor,
                                 @Qualifier("accruedDepreciationWriter") AccruedDepreciationWriter accruedDepreciationWriter, @Qualifier("netBookValueWriter") NetBookValueWriter netBookValueWriter,
                                 @Qualifier("fixedAssetNetBookValueProcessor") FixedAssetNetBookValueProcessor fixedAssetNetBookValueProcessor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.excelMapperService = excelMapperService;
        this.excelItemProcessor = excelItemProcessor;
        this.excelItemWriter = excelItemWriter;
        this.entityManagerFactory = entityManagerFactory;
        this.fixedAssetAccruedDepreciationProcessor = fixedAssetAccruedDepreciationProcessor;
        this.accruedDepreciationWriter = accruedDepreciationWriter;
        this.netBookValueWriter = netBookValueWriter;
        this.fixedAssetNetBookValueProcessor = fixedAssetNetBookValueProcessor;
    }


    /**
     * <p>excelItemReader.</p>
     *
     * @param filePath a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.kernel.batch.upload.ExcelItemReader} object.
     */
    @Bean
    @JobScope
    public ExcelItemReader excelItemReader(@Value("#{jobParameters['fileName']}") String filePath) {

        return new ExcelItemReader(filePath, excelMapperService);
    }

    /**
     * <p>fixedAssetItemReader.</p>
     *
     * @return a {@link org.springframework.batch.item.ItemReader} object.
     * @throws java.lang.Exception if any.
     */
    @Bean
    public ItemReader<FixedAsset> fixedAssetItemReader() throws Exception {

        JpaPagingItemReader<FixedAsset> dataBaseReader = new JpaPagingItemReader<>();
        dataBaseReader.setEntityManagerFactory(entityManagerFactory);

        dataBaseReader.setQueryString("SELECT a FROM FixedAsset a");

        dataBaseReader.setTransacted(true);
        dataBaseReader.setPageSize(100);
        dataBaseReader.setSaveState(true);
        dataBaseReader.afterPropertiesSet();

        return dataBaseReader;
    }

    /**
     * <p>importExcelJob.</p>
     *
     * @param listener a {@link io.github.fasset.fasset.kernel.batch.upload.BatchNotifications} object.
     * @return a {@link org.springframework.batch.core.Job} object.
     */
    @Bean("importExcelJob")
    public Job importExcelJob(BatchNotifications listener) {
        return jobBuilderFactory.get("importExcelJob")
                                .preventRestart()
                                .incrementer(new RunIdIncrementer())
                                .listener(listener)
                                .flow(readExcelFileStep())
                                .on("COMPLETED")
                                .to(accrueDepreciationStep())
                                .on("COMPLETED")
                                .to(netBookValueUpdateStep())
                                .end()
                                .build();
    }

    /**
     * <p>readExcelFileStep.</p>
     *
     * @return a {@link org.springframework.batch.core.Step} object.
     */
    @Bean
    public Step readExcelFileStep() {
        return stepBuilderFactory.get("readExcelFileStep").<FixedAssetDTO, FixedAsset>chunk(100).reader(excelItemReader(filePath)).processor(excelItemProcessor).writer(excelItemWriter).build();
    }

    /**
     * <p>accrueDepreciationStep.</p>
     *
     * @return a {@link org.springframework.batch.core.Step} object.
     */
    @Bean
    public Step accrueDepreciationStep() {

        Step step2 = null;

        try {
            step2 = stepBuilderFactory.get("accrueDepreciationStep").<FixedAsset, AccruedDepreciation>chunk(100).reader(fixedAssetItemReader())
                                                                                                                .processor(fixedAssetAccruedDepreciationProcessor)
                                                                                                                .writer(accruedDepreciationWriter)
                                                                                                                .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return step2;
    }

    /**
     * <p>netBookValueUpdateStep.</p>
     *
     * @return a {@link org.springframework.batch.core.Step} object.
     */
    @Bean
    public Step netBookValueUpdateStep() {

        Step step3 = null;

        try {
            step3 = stepBuilderFactory.get("netBookValueUpdateStep").<FixedAsset, NetBookValue>chunk(100).reader(fixedAssetItemReader())
                                                                                                         .processor(fixedAssetNetBookValueProcessor)
                                                                                                         .writer(netBookValueWriter)
                                                                                                         .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return step3;
    }

}
