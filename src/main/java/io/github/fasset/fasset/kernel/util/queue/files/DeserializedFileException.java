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
 * This exception occurs if the file being uploaded is already flagged off as deserialized. This could mean that the system
 * has already opened and consumed the contents of the file
 */
class DeserializedFileException extends InvalidFileException {

    DeserializedFileException(FileUpload fileUpload) {
        super(String.format("Sorry the file uploaded: %s appears to already have been consumed, please refer to the manual" + "for further clarification", fileUpload));
    }
}
