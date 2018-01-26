package io.github.fasset.fasset.kernel.storage;

import io.github.fasset.fasset.kernel.messaging.UploadNotificationService;
import io.github.fasset.fasset.model.files.FileUpload;
import io.github.fasset.fasset.kernel.util.StorageException;
import io.github.fasset.fasset.kernel.util.StorageFileNotFoundException;
import io.github.fasset.fasset.service.FileUploadService;
import io.github.fasset.fasset.service.impl.FileUploadServiceImpl;
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
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);


    private final Path rootLocation;

    private final FileUploadService fileUploadService;


    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties, @Qualifier("fileUploadService") FileUploadService fileUploadService){
        rootLocation = Paths.get(storageProperties.getLocation());
        this.fileUploadService = fileUploadService;
    }

    /**
     * To store the file into storage
     *
     * @param file
     */
    @Override
    public void store(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("Storing file into the directory : {}",fileName);

        if(file.isEmpty()){

            throw new StorageException("Failed to store empty file : "+fileName);

        } else {

            FileUpload fileUpload = configureFileUploadAttributes(this.rootLocation.resolve(fileName).toString(),"Dec 2017");

            if(fileUploadService.theFileIsAlreadyUploaded(fileUpload)){

                log.info("The file : {} has already been uploaded",fileUpload.getFileName());

            } else {

                if (fileName.contains("..")) {
                    // This is a security check
                    throw new StorageException("Cannot store file with relative path outside current directory " + fileName);
                } else {

                    try {
                        Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new StorageException("Failed to store file " + fileName, e);
                    }

                    fileUploadService.recordFileUpload(fileUpload);
                }
            }
        }

    }

    private FileUpload configureFileUploadAttributes(String fileName, String month) {

        log.info("Getting ready to notify server of the file uploaded : {}",fileName);

        return new FileUpload(fileName,month, LocalDateTime.now().toString());
    }


    /**
     * Loads all files into storage
     *
     * @return
     */
    @Override
    public Stream<Path> loadAll() {

        log.info("Loading all files from storage");

        Stream<Path> filePathStream = null;

        try {
            filePathStream = Files.walk(this.rootLocation,1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

        return filePathStream;
    }

    /**
     * Load the fileName given into storage
     *
     * @param fileName
     * @return
     */
    @Override
    public Path load(String fileName) {

        return rootLocation.resolve(fileName);
    }

    /**
     * Loads the file given as a {@link Resource} object
     *
     * @param fileName
     * @return
     */
    @Override
    public Resource loadAsResource(String fileName) {

        Resource resource = null;

        Path file = load(fileName);

        try {
            Resource temp = new UrlResource(file.toUri());

            if( !temp.exists() || !temp.isReadable()){

                throw new StorageFileNotFoundException("Could not read file: " + fileName);
            } else{

                resource = temp;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return resource;
    }

    /**
     * Deletes all files in the storage
     */
    @Override
    public void deleteAll() {

        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    /**
     * To initialize storage and create storage directory
     */
    @Override
    public void init() {

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
