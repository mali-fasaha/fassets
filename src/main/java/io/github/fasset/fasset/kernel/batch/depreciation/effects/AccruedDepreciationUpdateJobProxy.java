package io.github.fasset.fasset.kernel.batch.depreciation.effects;

import io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.kernel.util.WorkInProgressListener;
import io.github.fasset.fasset.service.FixedAssetService;
import io.github.fasset.fasset.service.impl.FixedAssetServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component("accruedDepreciationJobProxy")
public class AccruedDepreciationUpdateJobProxy {

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationUpdateJobProxy.class);

    @Autowired
    @Qualifier("accruedDepreciationUpdateJob")
    private Job accruedDepreciationUpdateJob;

    @Autowired
    @Qualifier("fixedAssetsJobsActivator")
    private FixedAssetsJobsActivator fixedAssetsJobsActivator;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private FixedAssetService fixedAssetService;


    public void initializeDepreciationUpdates(List<AccruedDepreciationDto> itemsList, int no_of_items, WorkInProgressListener progressListener) {

        LocalDateTime starting_time = LocalDateTime.now();

        log.info("AccruedDepreciationUpdate has begun with {} items at time: {}", no_of_items, starting_time);

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("no_of_items", String.valueOf(no_of_items))
                .addString("starting_time", starting_time.toString());

        log.info("executing MonthlyCategoryDepreciation job : {}", accruedDepreciationUpdateJob);
        executeUpdateJob(itemsList, jobParametersBuilder, accruedDepreciationUpdateJob,progressListener);
    }

    private void executeUpdateJob(List<AccruedDepreciationDto> itemsList, JobParametersBuilder jobParametersBuilder, Job job,WorkInProgressListener progressListener) {

        log.info("Running {} job for a list of : {} items", job, itemsList.size());

        Map parameterMap = jobParametersBuilder.toJobParameters().getParameters();
        parameterMap.put("itemsList", itemsList);

        try {
            log.debug("Bootstrapping job : {}",job);
            fixedAssetsJobsActivator.bootstrap(new JobParameters(parameterMap),jobLauncher, job, fixedAssetService,progressListener);
        } catch (BatchJobExecutionException e) {
            e.printStackTrace();
        }
    }
}
