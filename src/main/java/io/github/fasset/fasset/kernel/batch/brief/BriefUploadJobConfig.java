package io.github.fasset.fasset.kernel.batch.brief;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BriefUploadJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("categoryBriefWriter")
    private CategoryBriefWriter categoryBriefWriter;


    @Autowired
    @Qualifier("categoryBriefProcessor")
    private CategoryBriefProcessor categoryBriefProcessor;

    @Autowired
    @Qualifier("serviceOutletBriefProcessor")
    private ServiceOutletBriefProcessor serviceOutletBriefProcessor;

    @Autowired
    @Qualifier("serviceOutletBriefWriter")
    private ServiceOutletBriefWriter serviceOutletBriefWriter;

    @Bean("briefFixedAssetReader")
    public ItemReader<FixedAsset> briefFixedAssetReader() throws Exception{

        JpaPagingItemReader<FixedAsset> dataBaseReader = new JpaPagingItemReader<>();

        dataBaseReader.setQueryString("SELECT a FROM FixedAsset a");

        dataBaseReader.setTransacted(true);
        dataBaseReader.setPageSize(100);
        dataBaseReader.setSaveState(true);
        dataBaseReader.afterPropertiesSet();

        return dataBaseReader;
    }

    @Bean("briefUploadJob")
    public Job briefUploadJob(BriefBatchNotificationListener notificationListener){

        return jobBuilderFactory.get("briefUploadJob")
                .incrementer(new RunIdIncrementer())
                .listener(notificationListener)
                .flow(step1()).on("COMPLETED")
                .to(step2())
                .end()
                .build();
    }

    private Step step1() {

        Step step1 = null;

        try {
            step1 = stepBuilderFactory.get("step1")
                    .<FixedAsset,CategoryBrief>chunk(100)
                    .reader(briefFixedAssetReader())
                    .processor(categoryBriefProcessor)
                    .writer(categoryBriefWriter)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return step1;
    }

    private Step step2(){

        Step step2 = null;

        try {
            step2 = stepBuilderFactory.get("step2")
                    .<FixedAsset,ServiceOutletBrief>chunk(100)
                    .reader(briefFixedAssetReader())
                    .processor(serviceOutletBriefProcessor)
                    .writer(serviceOutletBriefWriter)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return step2;
    }
}
