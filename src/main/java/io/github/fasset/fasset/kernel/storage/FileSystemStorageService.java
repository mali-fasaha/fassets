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
package io.github.fasset.fasset.kernel.storage;

import io.github.fasset.fasset.config.StorageProperties;
import io.github.fasset.fasset.kernel.util.StorageException;
import io.github.fasset.fasset.kernel.util.StorageFileNotFoundException;
import io.github.fasset.fasset.kernel.util.queue.MessageQueue;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;
import io.github.fasset.fasset.kernel.util.queue.files.FileUploadsQueue;
import io.github.fasset.fasset.model.files.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;
import java.util.stream.Stream;

import static io.github.fasset.fasset.kernel.util.FileSecurityChecks.relativePathCheck;

/**
 * Implements storageService interface while at the same time implementing the pushing messages to the {@link MessageQueue} to asynchronously return to the controller once the file has been verified
 * to be okay. The file is checked for obvious defects such as a substring in the filename that might affect the relative position of file access in the server. The file will also be checked against a
 * record of all such files uploaded just in case the same is a duplication. The amount of time it will take to do that is unsubstantial in the eyes of the user as a notification will be returned by
 * the controller almost immediately he pressed the upload key. When that fails to be the case, this service will be scrapped and rewritten again.
 * <br>Usage : <br> This class will enable clients to <br> - List uploaded files: <br>  storageService.loadAll() <br> - Store uploaded files in root location: <br>
 * storageService.store(file); <br> - Generate API containing uploaded files <br>  storageService.loadAsResource(fileName); <br>
 *
 * @author edwin.njeru
 * @version 0.0.1
 * @since 0.0.1-SNAPSHOT
 */
@Service("fileSystemStorageService")
public class FileSystemStorageService implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path rootLocation;

    private MessageQueue fileUploadsQueue;

    /**
     * <p>Constructor for FileSystemStorageService.</p>
     *
     * @param storageProperties a {@link io.github.fasset.fasset.config.StorageProperties} object.
     * @param fileUploadsQueue  a {@link io.github.fasset.fasset.kernel.util.queue.files.FileUploadsQueue} object.
     */
    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties, @Qualifier("fileUploadsQueue") FileUploadsQueue fileUploadsQueue) {
        rootLocation = Paths.get(storageProperties.getLocation());
        //Todo remove this from this class
        this.fileUploadsQueue = fileUploadsQueue;

    }

    /**
     * {@inheritDoc}
     * <p>
     * To store the file into storage
     */
    @Override
    public void store(MultipartFile file) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        log.info("Storing file into the directory : {}", fileName);

        if (file.isEmpty()) {

            throw new StorageException("Failed to store empty file : " + fileName);

        } else {

            relativePathCheck(fileName);

            FileUpload fileUpload = configureFileUploadAttributes(this.rootLocation.resolve(fileName).toString(), YearMonth.of(2017, 12));

            try {
                // copy file to the file system
                Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new StorageException("Failed to store file " + fileName, e);
            }

            // TODO review the need for the FileUploadNotification DTO
            /*fileUploadsQueue.push(() -> new FileUploadNotification(fileUpload.getFileName(), fileUpload.getMonth().toString(), fileUpload.getTimeUploaded().toString()), (e) -> {
                //TODO check if this try again method is shooting the hypothetical foot
                store(file);
            }, () -> {
                fileUpload.setTimeUploaded(LocalDateTime.now());
                log.debug("The file {} has been uploaded", fileUpload.getFileName());
            });*/

            // Add allow duplicates option in property configuration
            fileUploadsQueue.push(new QueueMessage() {
                @Override
                public Object message() {
                    return fileUpload;
                }

                @Override
                public String toString() {
                    return fileUpload.toString();
                }

            }, (e) -> {
                throw new FileSystemStorageException(file, fileUpload, e);
            }, () -> {
                fileUpload.setTimeUploaded(LocalDateTime.now());
                log.debug("The file {} has been uploaded", fileUpload.getFileName());
            });

            //TODO do away with this synchronized approach
            //postUpdate(() -> new FileUploadNotification(fileUpload.getFileName(), fileUpload.getMonth().toString(), fileUpload.getTimeUploaded().toString()));
        }

    }

    private FileUpload configureFileUploadAttributes(String fileName, YearMonth month) {

        LocalDateTime uploadTime = LocalDateTime.now();

        log.info("Configuring notification to server of the file uploaded : {} for the month : {} at time :{}" + "", fileName, month, uploadTime);

        return new FileUpload(fileName, month, uploadTime);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Loads all files into storage
     */
    @Override
    public Stream<Path> loadAll() {

        log.info("Checking for files in the directory...");

        Stream<Path> filePathStream;

        try {
            filePathStream = Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

        return filePathStream;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Load the fileName given into storage
     */
    @Override
    public Path load(String fileName) {

        log.info("Loading file : {} from storage", fileName);

        return rootLocation.resolve(fileName);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Loads the file given as a {@link Resource} object
     */
    @Override
    public Resource loadAsResource(String fileName) {

        log.debug("Loading fileName : {} as resource", fileName);

        Resource resource = null;

        Path file = load(fileName);

        try {
            Resource temp = new UrlResource(file.toUri());

            if (!temp.exists() || !temp.isReadable()) {

                throw new StorageFileNotFoundException("Could not read file: " + fileName);
            } else {

                resource = temp;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return resource;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deletes all files in the storage
     */
    @Override
    public void deleteAll() {

        log.info("Deleting all files from the file system storage");

        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    /**
     * {@inheritDoc}
     * <p>
     * To initialize storage and create storage directory
     */
    @Override
    public void init() {

        log.info("Initializing file storage system...");

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
