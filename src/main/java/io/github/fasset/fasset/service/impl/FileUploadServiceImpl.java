package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.messaging.UploadNotificationService;
import io.github.fasset.fasset.model.files.FileUpload;
import io.github.fasset.fasset.repository.FileUploadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fileUploadService")
public class FileUploadServiceImpl implements io.github.fasset.fasset.service.FileUploadService {

    private static final Logger log  = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private final FileUploadRepository fileUploadRepository;

    private final UploadNotificationService notificationService;

    @Autowired
    public FileUploadServiceImpl(@Qualifier("fileUploadRepository") FileUploadRepository fileUploadRepository, @Qualifier("notificationService") UploadNotificationService notificationService) {
        this.fileUploadRepository = fileUploadRepository;
        this.notificationService = notificationService;
    }

    @Override
    public boolean theFileIsAlreadyUploaded(FileUpload fileUpload){

        if(fileUploadRepository.countAllByFileName(fileUpload.getFileName()) <= 0){

            return false;
        } else {

            return true;
        }
    }

    @Override
    public void recordFileUpload(FileUpload fileUpload) {

        if(!theFileIsAlreadyUploaded(fileUpload)) {
            fileUploadRepository.save(fileUpload);
            notificationService.sendNotification(fileUpload);
        } else {

            log.info("The file : {} is already uploaded and will not be duplicated",fileUpload.getFileName());
        }
    }
}
