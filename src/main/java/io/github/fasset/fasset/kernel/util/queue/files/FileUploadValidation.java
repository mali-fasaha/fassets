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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides validate method in which we can include re-usable file validation methods.
 * <br> Any of the checks the {@link FileValidationService#validate(FileUpload)} will throw
 * an {@link InvalidFileException}.
 */
public class FileUploadValidation implements FileValidationService<FileUpload> {

    private final FileUploadService fileUploadService;

    private static final Logger log = LoggerFactory.getLogger(FileUploadValidation.class);

    FileUploadValidation(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @Override
    public FileUpload validate(FileUpload fileUpload) throws InvalidFileException {

        log.debug("Validating file uploaded : {}", fileUpload);

        if (fileUpload == null) {
            throw new InvalidFileException("Sorry, the file provided for upload into the system is null");
        }

        if (fileUploadService.theFileIsAlreadyUploaded(fileUpload)) {
            throw new UploadedFileException(fileUpload);
        } else if (fileUpload.isDeserialized()) {
            throw new DeserializedFileException(fileUpload);
        }

        return fileUpload;
    }
}
