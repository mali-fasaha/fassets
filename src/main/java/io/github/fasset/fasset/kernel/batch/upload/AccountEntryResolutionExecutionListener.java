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

import org.slf4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Callback interface for execution of accountEntryResolutionJob
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("accountEntryResolutionExecutionListener")
public class AccountEntryResolutionExecutionListener implements JobExecutionListener {

    private static final Logger log = getLogger(AccountEntryResolutionExecutionListener.class);

    /** {@inheritDoc} */
    @Override
    public void beforeJob(final JobExecution jobExecution) {

        log.debug("Initializing accountEntryResolutionJob, with parameters: {}", jobExecution.getJobParameters());

    }

    /** {@inheritDoc} */
    @Override
    public void afterJob(final JobExecution jobExecution) {

        log.debug("AccountEntryResolution has ended with status {}", jobExecution.getStatus());

    }
}
