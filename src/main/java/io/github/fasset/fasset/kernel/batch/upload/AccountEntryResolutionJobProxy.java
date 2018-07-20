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

import io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator;
import io.github.fasset.fasset.kernel.batch.JobProxy;
import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import io.github.fasset.fasset.service.FixedAssetService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Proxy for launching the accountEntryResolutionJob
 */
@Component("accountEntryResolutionJobProxy")
public class AccountEntryResolutionJobProxy implements JobProxy {

    private final JobLauncher jobLauncher;

    private final FixedAssetService fixedAssetService;

    private final Job accountEntryResolution;

    private final FixedAssetsJobsActivator fixedAssetsJobsActivator;

    @Autowired
    AccountEntryResolutionJobProxy(final JobLauncher jobLauncher, @Qualifier("fixedAssetService") final FixedAssetService fixedAssetService, @Qualifier("accountEntryResolutionJob") final Job accountEntryResolution,
                                   final FixedAssetsJobsActivator fixedAssetsJobsActivator) {
        this.jobLauncher = jobLauncher;
        this.fixedAssetService = fixedAssetService;
        this.accountEntryResolution = accountEntryResolution;
        this.fixedAssetsJobsActivator = fixedAssetsJobsActivator;
    }

    @Override
    public void initializeJobRun() throws BatchJobExecutionException {

        fixedAssetsJobsActivator.bootstrap(jobLauncher, accountEntryResolution, fixedAssetService);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            initializeJobRun();
        } catch (BatchJobExecutionException e) {
            e.printStackTrace();
        }
    }
}
