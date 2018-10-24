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
package io.github.fasset.fasset.kernel.batch.depreciation;

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
 * Interface for launching the depreciation job. This abstraction enables the launch to be carried out from any object
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("depreciationJobProxy")
public class DepreciationJobProxy implements JobProxy {

    private final JobLauncher jobLauncher;

    private final FixedAssetService fixedAssetService;

    private final Job depreciationRun;

    private final FixedAssetsJobsActivator fixedAssetsJobsActivator;

    /**
     * <p>Constructor for DepreciationJobProxy.</p>
     *
     * @param jobLauncher              a {@link org.springframework.batch.core.launch.JobLauncher} object.
     * @param fixedAssetService        a {@link io.github.fasset.fasset.service.FixedAssetService} object.
     * @param depreciationRun          a {@link org.springframework.batch.core.Job} object.
     * @param fixedAssetsJobsActivator a {@link io.github.fasset.fasset.kernel.batch.FixedAssetsJobsActivator} object.
     */
    @Autowired
    public DepreciationJobProxy(JobLauncher jobLauncher, @Qualifier("fixedAssetService") FixedAssetService fixedAssetService, @Qualifier("depreciationJob") Job depreciationRun,
                                FixedAssetsJobsActivator fixedAssetsJobsActivator) {
        this.jobLauncher = jobLauncher;
        this.fixedAssetService = fixedAssetService;
        this.depreciationRun = depreciationRun;
        this.fixedAssetsJobsActivator = fixedAssetsJobsActivator;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeJobRun() throws BatchJobExecutionException {

        fixedAssetsJobsActivator.bootstrap(jobLauncher, depreciationRun, fixedAssetService);
    }

    /**
     * {@inheritDoc}
     * <p>
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

