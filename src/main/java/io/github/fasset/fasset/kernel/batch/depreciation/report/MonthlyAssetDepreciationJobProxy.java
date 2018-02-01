package io.github.fasset.fasset.kernel.batch.depreciation.report;

import com.google.common.collect.ImmutableSet;
import io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationRelay;
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
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Component("monthlyAssetDepreciationJobProxy")
public class MonthlyAssetDepreciationJobProxy {

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationJobProxy.class);

    private final JobLauncher jobLauncher;

    private final Job monthlyAssetDepreciation;

    private final FixedAssetService fixedAssetService;

    private final FixedAssetsJobsActivator fixedAssetsJobsActivator;

    private final DepreciationRelay depreciationRelay;


    @Autowired
    public MonthlyAssetDepreciationJobProxy(JobLauncher jobLauncher, @Qualifier("monthlyAssetDepreciation") Job monthlyAssetDepreciation, @Qualifier("fixedAssetService") FixedAssetService fixedAssetService, FixedAssetsJobsActivator fixedAssetsJobsActivator, DepreciationRelay depreciationRelay) {
        this.jobLauncher = jobLauncher;
        this.monthlyAssetDepreciation = monthlyAssetDepreciation;
        this.fixedAssetService = fixedAssetService;
        this.fixedAssetsJobsActivator = fixedAssetsJobsActivator;
        this.depreciationRelay = depreciationRelay;
    }

    private List<Integer> annualRelay(){

        List<Integer> annualList = depreciationRelay
                .getMonthlyDepreciationSequence()
                .parallelStream()
                .map(YearMonth::getYear)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        //Just to ensure only unique items are returned
        return ImmutableSet.copyOf(annualList).asList();
    }

    public void initializeMonthlyAssetDepreciationReporting(){

        int no_of_assets = fixedAssetService.getPoll();
        LocalDateTime starting_time = LocalDateTime.now();

        log.info("Depreciation has begun with {} items at time: {}", no_of_assets, starting_time);

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("no_of_assets", String.valueOf(no_of_assets))
                .addString("starting_time", LocalDateTime.now().toString());

        log.info("MonthlyAssetDepreciation job has been triggered");

        annualRelay().forEach(year ->{
            log.debug("Running monthlyAssetDepreciation job for the year : {}",year);
            jobParametersBuilder.addString("year", year.toString()).toJobParameters();
            try {
                fixedAssetsJobsActivator.bootstrap(jobParametersBuilder.addLong("year", Long.valueOf(year)).toJobParameters(),jobLauncher, monthlyAssetDepreciation,fixedAssetService);
            } catch (BatchJobExecutionException e) {
                e.printStackTrace();
            }
        });


    }



}
