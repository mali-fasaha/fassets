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
package io.github.fasset.fasset.kernel.util;

import org.springframework.batch.core.JobExecutionException;

/**
 * Exception encountered during execution of any Batch process
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class BatchJobExecutionException extends JobExecutionException {

    /**
     * <p>Constructor for BatchJobExecutionException.</p>
     *
     * @param msg a {@link java.lang.String} object.
     */
    public BatchJobExecutionException(String msg) {
        super(msg);
    }

    /**
     * <p>Constructor for BatchJobExecutionException.</p>
     *
     * @param msg   a {@link java.lang.String} object.
     * @param cause a {@link java.lang.Throwable} object.
     */
    public BatchJobExecutionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
