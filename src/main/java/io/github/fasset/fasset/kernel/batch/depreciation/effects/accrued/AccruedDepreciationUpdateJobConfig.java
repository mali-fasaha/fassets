package io.github.fasset.fasset.kernel.batch.depreciation.effects.accrued;

import io.github.fasset.fasset.kernel.batch.upload.AccruedDepreciationWriter;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.model.AccruedDepreciation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccruedDepreciationUpdateJobConfig {

    @Value("#{jobParameters['itemsList']}")
    public static final List<AccruedDepreciationDto> ITEMS_LIST = null;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("accruedDepreciationWriter")
    private AccruedDepreciationWriter accruedDepreciationWriter;

    @Autowired
    @Qualifier("accruedDepreciationDtoProcessor")
    private AccruedDepreciationDtoProcessor accruedDepreciationDtoProcessor;

    @Bean("accruedDepreciationUpdateJob")
    public Job accruedDepreciationUpdateJob(AccruedDepreciationJobListener accruedDepreciationJobListener) {
        return jobBuilderFactory.get("accruedDepreciationUpdateJob")
                .incrementer(new RunIdIncrementer())
                .listener(accruedDepreciationJobListener)
                .flow(updateAccruedDepreciation())
                .end()
                .build();
    }

    @Bean
    public Step updateAccruedDepreciation() {
        return stepBuilderFactory.get("updateAccruedDepreciation")
                .<AccruedDepreciationDto, AccruedDepreciation> chunk(100)
                .reader(accruedDepreciationReader(ITEMS_LIST))
                .processor(accruedDepreciationDtoProcessor)
                .writer(accruedDepreciationWriter)
                .build();
    }

    @Bean
    @JobScope
    public ItemReader<AccruedDepreciationDto> accruedDepreciationReader(@Value("#{jobParameters['itemsList']}")List<AccruedDepreciationDto> itemsList) {

        return new AccruedDepreciationReader(itemsList);
    }
}
