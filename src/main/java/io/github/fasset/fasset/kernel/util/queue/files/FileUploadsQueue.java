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

/**
 * Implementation of the MessageQueue interface that allows queueing of messages related to upload of files in the system.
 * <br>
 * The implementation uses the {@link FileUploadService} in the push method to record a file in the records.
 * <br>
 * The {@link OnCompletion} lifecycle method is also called when the file is uploaded successfully but it is only when the client calls the {@link #push(QueueMessage, OnCompletion)}
 * <br>
 * The client could also call the {@link #push(QueueMessage, OnError)} method which will handle both completion and an
 * unexpected messageQueue runtime exception
 * TODO: add validation methods here or create file validation service for the same in another
 * Todo Add duplicate upload checks service, with hashing algorithms interface
 *
 * @author edwin.njeru
 * @since 0.0.1
 * @version 0.0.1
 */
@Component("fileUploadsQueue")
public class FileUploadsQueue extends AbstractMessageQueue<FileUpload> {

    private static final Logger log = LoggerFactory.getLogger(FileUploadsQueue.class);
    private final FileUploadService fileUploadService;
    private final FileValidationService<FileUpload> fileValidationService;
    private volatile boolean allowDuplicates;

    /**
     * <p>Constructor for FileUploadsQueue.</p>
     *
     * @param fileUploadService a {@link io.github.fasset.fasset.kernel.util.queue.files.FileUploadService} object.
     * @param fileValidationService a {@link io.github.fasset.fasset.kernel.util.queue.files.FileValidationService} object.
     */
    @Autowired
    public FileUploadsQueue(@Qualifier("fileUploadService") FileUploadService fileUploadService, @Qualifier("fileValidationService") FileValidationService<FileUpload> fileValidationService) {
        super();
        this.fileUploadService = fileUploadService;
        this.fileValidationService = fileValidationService;
    }

    /**
     * <p>Setter for the field <code>allowDuplicates</code>.</p>
     *
     * @param allowDuplicates a boolean.
     */
    @Value("${allow.duplicate.file.uploads}")
    public void setAllowDuplicates(boolean allowDuplicates) {
        log.debug("Setting AllowDuplicates flag as {} inside the queue id {}", allowDuplicates, this);
        this.allowDuplicates = allowDuplicates;
    }

    /**
     * {@inheritDoc}
     *
     * Adds a message to the queue
     */
    @Override
    public void push(QueueMessage<FileUpload> queueMessage) {

        log.debug("Sending the message {} to the queue, with flag for allowDuplicates set as : {} from queue id : {}", queueMessage, allowDuplicates, this);

        if (allowDuplicates) {

            fileUploadService.recordFileUpload(fileValidationService.allowDuplicates()
                                                                    .validate(queueMessage.message()));

        } else {

            fileUploadService.recordFileUpload(fileValidationService.validate(queueMessage.message()));

        }


    }

    @PostConstruct
    private void init() {
        log.info("File uploads queue initialized with allowDuplicates set as : {}", allowDuplicates);
    }

}
