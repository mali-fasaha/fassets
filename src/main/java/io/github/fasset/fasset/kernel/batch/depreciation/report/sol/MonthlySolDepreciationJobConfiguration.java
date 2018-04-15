/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

@Configuration
public class MonthlySolDepreciationJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    @Value("#{jobParameters['year']}")
    private static String YEAR;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("monthlySolDepreciationJobListener")
    private MonthlySolDepreciationJobListener monthlySolDepreciationJobListener;

    @Autowired
    private MonthlySolDepreciationService monthlySolDepreciationService;

    @Autowired
    @Qualifier("monthylSolDepreciationExecutor")
    private MonthlySolDepreciationExecutor monthlySolDepreciationExecutor;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Autowired
    public MonthlySolDepreciationJobConfiguration(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean("monthlySolDepreciationJob")
    public Job monthlySolDepreciationJob() {
        return jobBuilderFactory.get("monthlySolDepreciationJob")
                .incrementer(new RunIdIncrementer())
                .listener(monthlySolDepreciationJobListener)
                .preventRestart()
                .flow(createMonthlySolDepreciationItems())
                .end()
                .build();
    }

    @Bean
    public MonthlySolDepreciationWriter monthlySolDepreciationWriter() {

        return new MonthlySolDepreciationWriter(monthlySolDepreciationService);
    }

    @Bean
    @JobScope
    public MonthlySolDepreciationProcessor monthlySolDepreciationProcessor(@Value("#{jobParameters['year']}") String year) {

        return new MonthlySolDepreciationProcessor(monthlySolDepreciationExecutor, year);
    }

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

    @Bean
    public Step createMonthlySolDepreciationItems() {

        Step createMonthlySolDepreciationItems = null;

        try {
            createMonthlySolDepreciationItems = stepBuilderFactory
                    .get("createMonthlySolDepreciationItems")
                    .<String, MonthlySolDepreciation>chunk(5)
                    .reader(monthlySolDepreciationReader())
                    .writer(monthlySolDepreciationWriter())
                    .processor(monthlySolDepreciationProcessor(YEAR))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createMonthlySolDepreciationItems;
    }
}
