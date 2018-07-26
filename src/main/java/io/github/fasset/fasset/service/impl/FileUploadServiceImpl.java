/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.util.ConcurrentList;
import io.github.fasset.fasset.kernel.util.queue.files.FileUploadService;
import io.github.fasset.fasset.model.files.FileUpload;
import io.github.fasset.fasset.repository.FileUploadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link io.github.fasset.fasset.kernel.util.queue.files.FileUploadService} implementation
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Transactional
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private FileUploadRepository fileUploadRepository;

    /*private UploadNotificationService notificationService;*/

    /**
     * <p>Setter for the field <code>fileUploadRepository</code>.</p>
     *
     * @param fileUploadRepository a {@link io.github.fasset.fasset.repository.FileUploadRepository} object.
     * @return a {@link io.github.fasset.fasset.service.impl.FileUploadServiceImpl} object.
     */
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

    /** {@inheritDoc} */
    @Cacheable("hasFileBeenUploaded")
    @Override
    public boolean theFileIsAlreadyUploaded(FileUpload fileUpload) {

        if (fileUploadRepository.countAllByFileName(fileUpload.getFileName()) <= 0) {

            return false;
        } else {

            return true;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void recordFileUpload(FileUpload fileUpload) {

        if (!theFileIsAlreadyUploaded(fileUpload)) {
            fileUploadRepository.save(fileUpload);
            /*String fileName = fileUpload.getFileName();
            String month = fileUpload.getMonth().toString();
            String timeUploaded = fileUpload.getTimeUploaded().toString();*/
            //FIXME : To implement observer pattern here
            //notificationService.sendNotification(new FileUploadNotification(fileName,month,timeUploaded));
        } else {

            log.info("The file : {} is already uploaded and will not be duplicated", fileUpload.getFileName());
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<FileUpload> uploadedFiles() {

        return ConcurrentList.of(fileUploadRepository.findAll());
    }
}
