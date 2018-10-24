/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.batch.JobProxy;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.service.BriefingService;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * Raises notifications during start and end of a batch or to interface between two batches
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component
public class BatchNotifications implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(BatchNotifications.class);

    private final ExcelItemReader excelItemReader;

    private final FixedAssetService fixedAssetService;

    private JobProxy depreciationJobProxy;

    private JobProxy accountEntryResolutionJobProxy;

    private final Executor asynchExecutor;


    private BriefingService briefingService;

    /**
     * <p>Constructor for BatchNotifications.</p>
     *
     * @param excelItemReader                a {@link io.github.fasset.fasset.kernel.batch.upload.ExcelItemReader} object.
     * @param fixedAssetService              a {@link io.github.fasset.fasset.service.FixedAssetService} object.
     * @param briefingService                a {@link io.github.fasset.fasset.service.BriefingService} object.
     * @param depreciationJobProxy           a {@link io.github.fasset.fasset.kernel.batch.JobProxy} object.
     * @param accountEntryResolutionJobProxy a {@link io.github.fasset.fasset.kernel.batch.JobProxy} object.
     * @param asynchExecutor                 a {@link java.util.concurrent.Executor} object.
     */
    @Autowired
    public BatchNotifications(@Qualifier("excelItemReader") ExcelItemReader excelItemReader, @Qualifier("fixedAssetService") FixedAssetService fixedAssetService,
                              @Qualifier("briefingService") BriefingService briefingService, @Qualifier("depreciationJobProxy") JobProxy depreciationJobProxy,
                              @Qualifier("accountEntryResolutionJobProxy") JobProxy accountEntryResolutionJobProxy, @Qualifier("asynchExecutor") final Executor asynchExecutor) {
        this.excelItemReader = excelItemReader;
        this.fixedAssetService = fixedAssetService;
        this.briefingService = briefingService;
        this.depreciationJobProxy = depreciationJobProxy;
        this.accountEntryResolutionJobProxy = accountEntryResolutionJobProxy;
        this.asynchExecutor = asynchExecutor;
    }


    /**
     * {@inheritDoc}
     * <p>
     * Callback before a job executes.
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {

        // Trying to log the filePath here
        log.info("reading file from filePath : {} ", jobExecution.getJobParameters().getString("fileName"));

        // this will reset the nextItem to Zero
        excelItemReader.resetNextItem();
        //excelItemReader.init();

    }

    /**
     * {@inheritDoc}
     * <p>
     * Callback after completion of a job. Called after both both successful and failed executions. To perform logic on a particular status, use "if (jobExecution.getStatus() == BatchStatus.X)".
     */
    @Override
    public void afterJob(JobExecution jobExecution) {

        log.debug("Step 1 completed : following items have been persisted...");

        //fixedAssetService.fetchAllExistingAssets().forEach(System.out::println);

        briefingService.updateCategoryBriefs();

        briefingService.updateServiceOutletBriefs();

        //TODO trigger depreciation with message

        // todo confirm this actually works
        // todo this execution failed spectalularly, please remove
        asynchExecutor.execute(accountEntryResolutionJobProxy);
        asynchExecutor.execute(depreciationJobProxy);


        // disabling depreciation for now
        //depreciationJobProxy.initializeJobRun();
        //accountEntryResolutionJobProxy.initializeJobRun();

    }

}
