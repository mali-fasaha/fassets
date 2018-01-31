package io.github.fasset.fasset.kernel.batch.depreciation.report;

import io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("monthlyAssetDepreciationJobProxy")
public class MonthlyAssetDepreciationJobProxy {

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationJobProxy.class);

    private final JobLauncher jobLauncher;

    private final Job monthlyAssetDepreciation;

    private final FixedAssetService fixedAssetService;

    private final FixedAssetsJobsActivator fixedAssetsJobsActivator;


    @Autowired
    public MonthlyAssetDepreciationJobProxy(JobLauncher jobLauncher, @Qualifier("monthlyAssetDepreciation") Job monthlyAssetDepreciation, @Qualifier("fixedAssetService") FixedAssetService fixedAssetService, FixedAssetsJobsActivator fixedAssetsJobsActivator) {
        this.jobLauncher = jobLauncher;
        this.monthlyAssetDepreciation = monthlyAssetDepreciation;
        this.fixedAssetService = fixedAssetService;
        this.fixedAssetsJobsActivator = fixedAssetsJobsActivator;
    }

    public void initializeMonthlyAssetDepreciationReporting(){

        try {
            log.info("MonthlyAssetDepreciation job has been triggered");
            fixedAssetsJobsActivator.bootstrap(jobLauncher, monthlyAssetDepreciation,fixedAssetService);
        } catch (BatchJobExecutionException e) {
            e.printStackTrace();
        }
    }



}
