package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.files.FileUpload;

public interface FileUploadService {
    boolean theFileIsAlreadyUploaded(FileUpload fileUpload);

    void recordFileUpload(FileUpload fileUpload);
}
