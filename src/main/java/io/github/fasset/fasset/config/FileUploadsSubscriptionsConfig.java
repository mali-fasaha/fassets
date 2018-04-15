/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.config;

import io.github.fasset.fasset.kernel.batch.ExcelUploadJob;
import io.github.fasset.fasset.kernel.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FileUploadsSubscriptionsConfig {


    private StorageService fileSystemStorageService;


    private ExcelUploadJob excelUploadJob;


    @Qualifier("fileSystemStorageService")
    @Autowired
    public FileUploadsSubscriptionsConfig setFileSystemStorageService(StorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
        return this;
    }

    @Qualifier("excelUploadJob")
    @Autowired
    public FileUploadsSubscriptionsConfig setExcelUploadJob(ExcelUploadJob excelUploadJob) {
        this.excelUploadJob = excelUploadJob;
        return this;
    }

    @PostConstruct
    private void registerSubscribers() {

        excelUploadJob.addSubscription(fileSystemStorageService);
        fileSystemStorageService.registerSubscriber(excelUploadJob);
    }
}
