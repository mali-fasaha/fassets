package io.github.fasset.fasset.kernel.batch.depreciation.report.category;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import io.github.fasset.fasset.service.MonthlyCategoryDepreciationService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class MonthlyCategoryDepreciationJobConfiguration {

    @Value("#{jobParameters['year']}")
    private static String YEAR;

    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MonthlyCategoryDepreciationService monthlyCategoryDepreciationService;

    @Autowired
    private MonthyCategoryDepreciationJobListener monthyCategoryDepreciationJobListener;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private MonthlyCategoryDepreciationExecutor monthlyCategoryDepreciationExecutor;


    @Autowired
    public MonthlyCategoryDepreciationJobConfiguration(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean("monthlyCategoryDepreciationJob")
    public Job monthlyCategoryDepreciationJob(){
        return jobBuilderFactory.get("monthlyCategoryDepreciationJob")
                .incrementer(new RunIdIncrementer())
                .listener(monthyCategoryDepreciationJobListener)
                .preventRestart()
                .flow(createMonthlyCategoryDepreciationItems())
                .end()
                .build();
    }

    @Bean
    public Step createMonthlyCategoryDepreciationItems() {

        Step createMonthlyCategoryDepreciationItems = null;

        try {
            createMonthlyCategoryDepreciationItems = stepBuilderFactory
                    .get("createMonthlyCategoryDepreciationItems")
                    .<String,MonthlyCategoryDepreciation>chunk(5)
                    .reader(monthlyCategoryDepreciationReader())
                    .writer(monthlyCategoryDepreciationWriter())
                    .processor(monthlyCategoryDepreciationProcessor(YEAR))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createMonthlyCategoryDepreciationItems;
    }

    @Bean
    @JobScope
    public ItemProcessor<String,MonthlyCategoryDepreciation> monthlyCategoryDepreciationProcessor(@Value("#{jobParameters['year']}") String year) {

        return new MonthlyCategoryDepreciationProcessor(monthlyCategoryDepreciationExecutor,year);
    }

    @Bean
    public ItemWriter<? super MonthlyCategoryDepreciation> monthlyCategoryDepreciationWriter() {
        return new MonthlyCategoryDepreciationWriter(monthlyCategoryDepreciationService);
    }

    @Bean
    public ItemReader<? extends String> monthlyCategoryDepreciationReader() {

        JpaPagingItemReader<String> categoryNameReader = new JpaPagingItemReader<>();

        categoryNameReader.setEntityManagerFactory(entityManagerFactory);

        categoryNameReader.setQueryString("SELECT DISTINCT e.category From Depreciation e");

        categoryNameReader.setTransacted(true);
        categoryNameReader.setPageSize(5);
        categoryNameReader.setSaveState(true);
        try {
            categoryNameReader.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryNameReader;
    }

}
