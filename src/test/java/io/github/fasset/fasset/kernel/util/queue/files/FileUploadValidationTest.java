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