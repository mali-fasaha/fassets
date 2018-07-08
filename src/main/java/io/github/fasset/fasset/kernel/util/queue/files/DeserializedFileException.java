package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.model.files.FileUpload;

/**
 * This exception occurs if the file being uploaded is already flagged off as deserialized. This could mean that the system
 * has already opened and consumed the contents of the file
 */
class DeserializedFileException extends InvalidFileException {

    DeserializedFileException(FileUpload fileUpload) {
        super(String.format("Sorry the file uploaded: %s appears to already have been consumed, please refer to the manual" +
            "for further clarification", fileUpload));
    }
}
