package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.model.files.FileUpload;

/**
 * This exception is thrown when the system tries to upload a file which is already uploaded
 */
public class UploadedFileException extends InvalidFileException {

    public UploadedFileException(FileUpload fileUpload) {

        super(String.format("The file named : %s has already been uploaded and has therefore failed duplicity" +
            "checks. See manual for further details", fileUpload.getFileName()));
    }
}
