package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.notifications.UploadNotificationService;
import io.github.fasset.fasset.model.files.FileUpload;
import io.github.fasset.fasset.repository.FileUploadRepository;
import io.github.fasset.fasset.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger log  = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private FileUploadRepository fileUploadRepository;

    /*private UploadNotificationService notificationService;*/

    @Autowired
    @Qualifier("fileUploadRepository")
    public FileUploadServiceImpl setFileUploadRepository(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
        return this;
    }

    /*@Autowired
    @Qualifier("notificationService")
    public FileUploadServiceImpl setNotificationService(UploadNotificationService notificationService) {
        this.notificationService = notificationService;
        return this;
    }*/

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
            String fileName= fileUpload.getFileName();
            String month=fileUpload.getMonth().toString();
            String timeUploaded=fileUpload.getTimeUploaded().toString();
            //FIXME : To implement observer pattern here
            //notificationService.sendNotification(new FileUploadNotification(fileName,month,timeUploaded));
        } else {

            log.info("The file : {} is already uploaded and will not be duplicated",fileUpload.getFileName());
        }
    }
}
