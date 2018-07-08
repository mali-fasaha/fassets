package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.model.files.FileUpload;

public class FileUploadValidation implements FileValidationService<FileUpload> {

    private final FileUploadService fileUploadService;

    public FileUploadValidation(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @Override
    public FileUpload validate(FileUpload fileUpload) throws InvalidFileException {

        if( fileUploadService.theFileIsAlreadyUploaded(fileUpload)) {
            throw new InvalidFileException()
        }

        return fileUpload;
    }
}
