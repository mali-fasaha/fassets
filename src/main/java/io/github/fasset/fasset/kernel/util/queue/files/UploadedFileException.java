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
package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.model.files.FileUpload;

/**
 * This exception is thrown when the system tries to upload a file which is already uploaded
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class UploadedFileException extends InvalidFileException {

    /**
     * <p>Constructor for UploadedFileException.</p>
     *
     * @param fileUpload a {@link io.github.fasset.fasset.model.files.FileUpload} object.
     */
    public UploadedFileException(FileUpload fileUpload) {

        super(String.format("The file named : %s has already been uploaded and has therefore failed duplicity" + "checks. See manual for further details", fileUpload.getFileName()));
    }
}
