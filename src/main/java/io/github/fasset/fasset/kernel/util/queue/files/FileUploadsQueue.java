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
package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.kernel.util.PropertiesUtils;
import io.github.fasset.fasset.kernel.util.queue.AbstractMessageQueue;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;
import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;
import io.github.fasset.fasset.model.files.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Implementation of the MessageQueue interface that allows queueing of messages related
 * to upload of files in the system.
 * <br> The implementation uses the {@link FileUploadService} in the push method to record
 * a file in the database.
 * <br> The lifecycle method is also called when the file is uploaded successfully but it is
 * only when the client calls the method {@link this#push(QueueMessage, OnCompletion)}
 * <br> The client could also call the method {@link this#push(QueueMessage, OnError, OnCompletion)} which will handle
 * both completion and an unexpected messageQueue runtime exception
 * <p>
 * TODO: add validation methods here or create file validation service for the same in another
 * // Todo Add duplicate upload checks service, with hashing algorithms
 * interface
 */
@Component("fileUploadsQueue")
public class FileUploadsQueue extends AbstractMessageQueue<FileUpload> {

    private static final Logger log = LoggerFactory.getLogger(FileUploadsQueue.class);

    private boolean allowDuplicates;

    private final FileUploadService fileUploadService;
    private final FileValidationService<FileUpload> fileValidationService;

    @Autowired
    public FileUploadsQueue(@Qualifier("fileUploadService") FileUploadService fileUploadService) {
        super();
        this.fileUploadService = fileUploadService;
        this.fileValidationService = new FileUploadValidation(fileUploadService);
    }

    @Value("${allow.duplicate.file.uploads}")
    public void setAllowDuplicates(boolean allowDuplicates) {
        log.debug("Setting AllowDuplicates flag as {}", allowDuplicates);
        this.allowDuplicates = allowDuplicates;
    }

    /**
     * Adds a message to the queue
     *
     * @param queueMessage Item to be added to the queue
     */
    @Override
    public void push(QueueMessage<FileUpload> queueMessage) {

        log.debug("Sending the message {} to the queue, with flag for allowDuplicates set as : {}", queueMessage, allowDuplicates);

        if (allowDuplicates) {

            fileUploadService.recordFileUpload(fileValidationService.allowDuplicates().validate(queueMessage.message()));

        } else {

            fileUploadService.recordFileUpload(fileValidationService.validate(queueMessage.message()));

        }


    }

    @PostConstruct
    private void init() {
        log.info("File uploads queue initialized with allowDuplicates set as : {}", allowDuplicates);
    }

}
