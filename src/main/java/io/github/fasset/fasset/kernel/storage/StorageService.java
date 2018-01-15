package io.github.fasset.fasset.kernel.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    /**
     * To initialize storage and create storage directory
     */
    void init();

    /**
     * To store the file into storage
     * @param file
     */
    void store(MultipartFile file);

    /**
     * Loads all files into storage
     * @return
     */
    Stream<Path> loadAll();

    /**
     * Load the fileName given into storage
     * @param fileName
     * @return
     */
    Path load(String fileName);

    /**
     * Loads the file given as a {@link Resource} object
     * @param fileName
     * @return
     */
    Resource loadAsResource(String fileName);

    /**
     * Deletes all files in the storage
     */
    void deleteAll();
}
