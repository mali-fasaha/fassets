package io.github.fasset.fasset.kernel.batch.depreciation.report;

import com.google.common.collect.ImmutableSet;
import io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationRelay;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.service.DepreciationService;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Component("monthlyDepreciationJobProxy")
public class MonthlyDepreciationJobProxy {

    private static final Logger log = LoggerFactory.getLogger(MonthlyDepreciationJobProxy.class);

    private final JobLauncher jobLauncher;

    private final Job monthlyAssetDepreciation;

    private final FixedAssetService fixedAssetService;

    private final DepreciationService depreciationService;

    private final FixedAssetsJobsActivator fixedAssetsJobsActivator;

    private final DepreciationRelay depreciationRelay;

    private final Job monthlySolDepreciationJob;


    @Autowired
    public MonthlyDepreciationJobProxy(JobLauncher jobLauncher, @Qualifier("monthlyAssetDepreciation") Job monthlyAssetDepreciation, @Qualifier("fixedAssetService") FixedAssetService fixedAssetService, DepreciationService depreciationService, FixedAssetsJobsActivator fixedAssetsJobsActivator, DepreciationRelay depreciationRelay, @Qualifier("monthlySolDepreciationJob") Job monthlySolDepreciationJob) {
        this.jobLauncher = jobLauncher;
        this.monthlyAssetDepreciation = monthlyAssetDepreciation;
        this.fixedAssetService = fixedAssetService;
        this.depreciationService = depreciationService;
        this.fixedAssetsJobsActivator = fixedAssetsJobsActivator;
        this.depreciationRelay = depreciationRelay;
        this.monthlySolDepreciationJob = monthlySolDepreciationJob;
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

    /*public void initializeMonthlySolDepreciationReporting(){

        int no_of_sols = depreciationService.getDistinctSolIds();
        LocalDateTime starting_time = LocalDateTime.now();
        log.info("MonthlySolDepreciation job has been triggered with the paramters... {} items at time : {}",
                no_of_sols,starting_time);

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("no_of_assets", String.valueOf(no_of_sols))
                .addString("starting_time", starting_time.toString());

        log.info("executing MonthlySolDepreciation job...");

        executeMonthlyJob(jobParametersBuilder,monthlySolDepreciationJob);
    }*/



    public void initializeMonthlyAssetDepreciationReporting(){

        int no_of_assets = fixedAssetService.getPoll();
        LocalDateTime starting_time = LocalDateTime.now();

        log.info("Depreciation has begun with {} items at time: {}", no_of_assets, starting_time);

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder()
                .addString("no_of_assets", String.valueOf(no_of_assets))
                .addString("starting_time", starting_time.toString());

        log.info("executing MonthlyAssetDepreciation job...");

        executeMonthlyJob(jobParametersBuilder,monthlyAssetDepreciation);

    }

    private void executeMonthlyJob(JobParametersBuilder jobParametersBuilder,Job job) {
        annualRelay().forEach(year ->{

            log.info("Running {} job for the year : {}",year,job);
            jobParametersBuilder.addString("year", year.toString()).toJobParameters();
            try {
                fixedAssetsJobsActivator.bootstrap(jobParametersBuilder.addLong("year", Long.valueOf(year)).toJobParameters(),jobLauncher, job,fixedAssetService);
            } catch (BatchJobExecutionException e) {
                e.printStackTrace();
            }
        });
    }



}
