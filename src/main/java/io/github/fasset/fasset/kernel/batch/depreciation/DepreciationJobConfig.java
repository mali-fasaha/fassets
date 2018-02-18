package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepreciationJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemReader<FixedAsset> fixedAssetItemReader;

    @Autowired
    public DepreciationJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<FixedAsset> fixedAssetItemReader) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.fixedAssetItemReader = fixedAssetItemReader;
    }

    @Bean("depreciationJob")
    public Job depreciationJob(DepreciationJobListener depreciationJobListener) {
        return jobBuilderFactory.get("depreciationJob")
                .incrementer(new RunIdIncrementer())
                .listener(depreciationJobListener)
                .flow(depreciationStep1())
                .end()
                .build();
    }

    @Bean
    public DepreciationProcessor depreciationProcessor(){

        return new DepreciationProcessor();
    }

    @Bean
    public DepreciationWriter depreciationWriter(){

        return new DepreciationWriter();
    }

    @Bean
    public Step depreciationStep1() {
        return stepBuilderFactory.get("depreciationStep1")
                .<FixedAsset, List<Depreciation>> chunk(100)
                .reader(fixedAssetItemReader)
                .processor(depreciationProcessor())
                .writer(depreciationWriter())
                .build();
    }


}
