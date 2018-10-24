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
package io.github.fasset.fasset.kernel.batch.depreciation.report.sol;

import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import io.github.fasset.fasset.service.MonthlySolDepreciationService;
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
 * Configuration object for the monthly service outlet depreciation job
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Configuration
public class MonthlySolDepreciationJobConfiguration {

    @Value("#{jobParameters['year']}")
    private static String year;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final MonthlySolDepreciationJobListener monthlySolDepreciationJobListener;

    private final MonthlySolDepreciationService monthlySolDepreciationService;

    private final MonthlySolDepreciationExecutor monthlySolDepreciationExecutor;

    private final EntityManagerFactory entityManagerFactory;


    /**
     * <p>Constructor for MonthlySolDepreciationJobConfiguration.</p>
     *
     * @param jobBuilderFactory                 a {@link org.springframework.batch.core.configuration.annotation.JobBuilderFactory} object.
     * @param stepBuilderFactory                a {@link org.springframework.batch.core.configuration.annotation.StepBuilderFactory} object.
     * @param monthlySolDepreciationJobListener a {@link io.github.fasset.fasset.kernel.batch.depreciation.report.sol.MonthlySolDepreciationJobListener} object.
     * @param monthlySolDepreciationService     a {@link io.github.fasset.fasset.service.MonthlySolDepreciationService} object.
     * @param monthlySolDepreciationExecutor    a {@link io.github.fasset.fasset.kernel.batch.depreciation.report.sol.MonthlySolDepreciationExecutor} object.
     * @param entityManagerFactory              a {@link javax.persistence.EntityManagerFactory} object.
     */
    @Autowired
    public MonthlySolDepreciationJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                                                  @Qualifier("monthlySolDepreciationJobListener") MonthlySolDepreciationJobListener monthlySolDepreciationJobListener,
                                                  MonthlySolDepreciationService monthlySolDepreciationService,
                                                  @Qualifier("monthlySolDepreciationExecutor") MonthlySolDepreciationExecutor monthlySolDepreciationExecutor,
                                                  EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.monthlySolDepreciationJobListener = monthlySolDepreciationJobListener;
        this.monthlySolDepreciationService = monthlySolDepreciationService;
        this.monthlySolDepreciationExecutor = monthlySolDepreciationExecutor;
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * <p>monthlySolDepreciationJob.</p>
     *
     * @return a {@link org.springframework.batch.core.Job} object.
     */
    @Bean("monthlySolDepreciationJob")
    public Job monthlySolDepreciationJob() {
        return jobBuilderFactory.get("monthlySolDepreciationJob")
                                .preventRestart()
                                .incrementer(new RunIdIncrementer())
                                .listener(monthlySolDepreciationJobListener)
                                .preventRestart()
                                .flow(createMonthlySolDepreciationItems())
                                .end()
                                .build();
    }

    /**
     * <p>monthlySolDepreciationWriter.</p>
     *
     * @return a {@link io.github.fasset.fasset.kernel.batch.depreciation.report.sol.MonthlySolDepreciationWriter} object.
     */
    @Bean
    public MonthlySolDepreciationWriter monthlySolDepreciationWriter() {

        return new MonthlySolDepreciationWriter(monthlySolDepreciationService);
    }

    /**
     * <p>monthlySolDepreciationProcessor.</p>
     *
     * @param year a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.kernel.batch.depreciation.report.sol.MonthlySolDepreciationProcessor} object.
     */
    @Bean
    @JobScope
    public MonthlySolDepreciationProcessor monthlySolDepreciationProcessor(@Value("#{jobParameters['year']}") String year) {

        return new MonthlySolDepreciationProcessor(monthlySolDepreciationExecutor, year);
    }

    /**
     * <p>monthlySolDepreciationReader.</p>
     *
     * @return a {@link org.springframework.batch.item.ItemReader} object.
     */
    @Bean
    public ItemReader<String> monthlySolDepreciationReader() {

        JpaPagingItemReader<String> solIdsReader = new JpaPagingItemReader<>();

        solIdsReader.setEntityManagerFactory(entityManagerFactory);

        solIdsReader.setQueryString("SELECT DISTINCT e.solId From Depreciation e");

        solIdsReader.setTransacted(true);
        solIdsReader.setPageSize(5);
        solIdsReader.setSaveState(true);
        try {
            solIdsReader.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solIdsReader;
    }

    /**
     * <p>createMonthlySolDepreciationItems.</p>
     *
     * @return a {@link org.springframework.batch.core.Step} object.
     */
    @Bean
    public Step createMonthlySolDepreciationItems() {

        Step createMonthlySolDepreciationItems = null;

        try {
            createMonthlySolDepreciationItems = stepBuilderFactory.get("createMonthlySolDepreciationItems").<String, MonthlySolDepreciation>chunk(5).reader(monthlySolDepreciationReader())
                                                                                                                                                    .writer(monthlySolDepreciationWriter())
                                                                                                                                                    .processor(monthlySolDepreciationProcessor(year))
                                                                                                                                                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createMonthlySolDepreciationItems;
    }
}
