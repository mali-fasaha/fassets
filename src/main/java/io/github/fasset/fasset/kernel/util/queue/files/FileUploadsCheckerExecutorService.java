package io.github.fasset.fasset.kernel.util.queue.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This Object exists solely to bootstrap the {@link FileUploadsChecker}
 */
@Service("fileUploadsExecutorService")
public class FileUploadsCheckerExecutorService {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);


    private final FileUploadsChecker fileUploadsChecker;

    @Autowired
    public FileUploadsCheckerExecutorService(@Qualifier("fileUploadsChecker") FileUploadsChecker fileUploadsChecker) {
        this.fileUploadsChecker = fileUploadsChecker;
    }

    @PostConstruct
    private void init(){

        //executorService.submit(fileUploadsChecker);
        fileUploadsChecker.run();

    }

}
