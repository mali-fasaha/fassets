package io.github.fasset.fasset.config;

import io.github.fasset.fasset.kernel.batch.ExcelUploadJob;
import io.github.fasset.fasset.kernel.storage.FileSystemStorageService;
import io.github.fasset.fasset.kernel.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FileUploadsSubscriptionsConfig {

    @Qualifier("fileSystemStorageService")
    @Autowired
    private StorageService fileSystemStorageService;

    @Qualifier("excelUploadJob")
    @Autowired
    private ExcelUploadJob excelUploadJob;


    @PostConstruct
    private void registerSubscribers(){

        excelUploadJob.addSubscription(fileSystemStorageService);
        fileSystemStorageService.registerSubscriber(excelUploadJob);
    }
}
