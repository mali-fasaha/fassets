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
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FileUploadValidationTest {

    FileValidationService validationService;
    FileUpload fileUpload = new FileUpload();

    @Before
    public void setUp() throws Exception {

        FileUploadService fileUploadService = Mockito.mock(FileUploadService.class);

        validationService = new FileUploadValidation(fileUploadService);
    }

    @Test
    public void validate() {

        assertEquals(fileUpload, validationService.validate(fileUpload));
    }

    @Test(expected = UploadedFileException.class)
    public void throwUPUploadedFileException() {

        FileUploadService fileUploadService = Mockito.mock(FileUploadService.class);
        when(fileUploadService.theFileIsAlreadyUploaded(fileUpload)).thenReturn(true);
        FileValidationService fileValidationService = new FileUploadValidation(fileUploadService);

        assertEquals(fileUpload, fileValidationService.validate(fileUpload));
    }

    @Test(expected = DeserializedFileException.class)
    public void throwUpDeserializedFileException() {

        fileUpload.setDeserialized(true);

        assertEquals(fileUpload, validationService.validate(fileUpload));
    }
}