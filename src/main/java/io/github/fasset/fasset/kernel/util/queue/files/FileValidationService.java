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
 * This validation service will check for common errors like duplicity, wrong extension types and so on and so forth
 *
 * @param <T> Type used to represent a file
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface FileValidationService<T> {

    /**
     * <p>validate.</p>
     *
     * @param fileUpload a {@link io.github.fasset.fasset.model.files.FileUpload} object.
     * @return a T object.
     * @throws io.github.fasset.fasset.kernel.util.queue.files.InvalidFileException if any.
     * @throws io.github.fasset.fasset.kernel.util.queue.files.InvalidFileException if any.
     */
    T validate(FileUpload fileUpload) throws InvalidFileException;

    /**
     * <p>allowDuplicates.</p>
     *
     * @return a {@link io.github.fasset.fasset.kernel.util.queue.files.FileValidationService} object.
     */
    FileValidationService<T> allowDuplicates();

}
