package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.excel.ExcelMapperService;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.FixedAssetDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class FileUploadBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("excelMapperService")
    private ExcelMapperService excelMapperService;

    @Autowired
    @Qualifier("excelItemProcessor")
    private ExcelItemProcessor excelItemProcessor;

    @Autowired
    @Qualifier("excelItemWriter")
    private ExcelItemWriter excelItemWriter;


    @Value("#{jobParameters['fileName']}")
    public static final String FILE_PATH = null;


    @Bean
    @JobScope
    public ExcelItemReader excelItemReader(@Value("#{jobParameters['fileName']}")String filePath){

        return new ExcelItemReader(filePath,excelMapperService);
    }

    @Bean("importExcelJob")
    public Job importExcelJob(BatchNotifications listener) {
        return jobBuilderFactory.get("importExcelJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<FixedAssetDTO, FixedAsset> chunk(10)
                .reader(excelItemReader(FILE_PATH))
                .processor(excelItemProcessor)
                .writer(excelItemWriter)
                .build();
    }
}
