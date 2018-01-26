package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.batch.BriefingService;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationJobProxy;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.springframework.batch.core.JobExecution;

import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BatchNotifications implements JobExecutionListener {

    private final static Logger LOGGER = getLogger(BatchNotifications.class);

    private final ExcelItemReader excelItemReader;

    private final FixedAssetService fixedAssetService;

    private DepreciationJobProxy depreciationJobProxy;


    private BriefingService briefingService;

    @Autowired
    public BatchNotifications(@Qualifier("excelItemReader") ExcelItemReader excelItemReader,
                              @Qualifier("fixedAssetService") FixedAssetService fixedAssetService,
                              @Qualifier("briefingService") BriefingService briefingService,
                              @Qualifier("depreciationJobProxy") DepreciationJobProxy depreciationJobProxy) {
        this.excelItemReader = excelItemReader;
        this.fixedAssetService = fixedAssetService;
        this.briefingService = briefingService;
        this.depreciationJobProxy = depreciationJobProxy;
    }


    /**
     * Callback before a job executes.
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {

        // Trying to log the FILE_PATH here
        LOGGER.info("reading file from FILE_PATH : {} ", jobExecution.getJobParameters().getString("fileName"));

        // this will reset the nextItem to Zero
        excelItemReader.resetNextItem();
        //excelItemReader.init();

    }

    /**
     * Callback after completion of a job. Called after both both successful and
     * failed executions. To perform logic on a particular status, use
     * "if (jobExecution.getStatus() == BatchStatus.X)".
     *
     * @param jobExecution the current {@link JobExecution}
     */
    @Override
    public void afterJob(JobExecution jobExecution) {

        LOGGER.debug("Step 1 completed : following items have been persisted...");

        //fixedAssetService.fetchAllExistingAssets().forEach(System.out::println);

        briefingService.updateCategoryBriefs();

        briefingService.updateServiceOutletBriefs();

        //TODO trigger depreciation with message
        try {
            depreciationJobProxy.initializeDepreciationRun();
        } catch (BatchJobExecutionException e) {
            e.printStackTrace();
        }

    }

}
