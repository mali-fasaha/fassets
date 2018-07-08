package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.model.files.FileUpload;

/**
 *
 * This validation service will check for common errors like duplicity, wrong extension types and so on and so forth
 *
 * @param <T> Type used to represent a file
 */
public interface FileValidationService<T> {

    T validate(FileUpload fileUpload) throws InvalidFileException;
}
