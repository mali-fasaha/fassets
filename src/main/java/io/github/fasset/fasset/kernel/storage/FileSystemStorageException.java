package io.github.fasset.fasset.kernel.storage;

import io.github.fasset.fasset.kernel.util.queue.MQException;
import io.github.fasset.fasset.model.files.FileUpload;
import org.springframework.web.multipart.MultipartFile;

/**
 * Exception thrown when trying to add a file to the queue
 */
class FileSystemStorageException extends RuntimeException {

    FileSystemStorageException(MultipartFile file, FileUpload fileUpload, MQException e) {

        super(String.format("The file uploaded as %s, translated as %s, could not be uploaded due to exception: %s",
            file, fileUpload, e.getMessage()), e);
    }
}
