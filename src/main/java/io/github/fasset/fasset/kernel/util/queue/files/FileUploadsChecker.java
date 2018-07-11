package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.kernel.util.ConcurrentList;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.kernel.util.queue.MQException;
import io.github.fasset.fasset.kernel.util.queue.MessageConsumer;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;
import io.github.fasset.fasset.model.files.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is a utility for checking if files have been uploaded, and if so, trigger actions
 * using the appropriate file uploads batch configuration
 */
@Component("fileUploadsChecker")
public class FileUploadsChecker implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(FileUploadsChecker.class);

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final MessageConsumer<List<FileUpload>> fileUploadsConsumer;


    //Todo Import and add ExcelUploadJob to execution

    @Autowired
    public FileUploadsChecker(MessageConsumer<List<FileUpload>> fileUploadsConsumer) {
        this.fileUploadsConsumer = fileUploadsConsumer;
    }

    private static void handleError(MQException e) {
        throw new FileMessageReadingException(e);
    }

    private static void handleCompletion() {
        log.info("A file has been inquired successfully from the system");
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        List<FileUpload> fileUploads = ConcurrentList.newList();

        try {
            fileUploadsConsumer.checkMessages(FileUploadsChecker::handleError, FileUploadsChecker::handleCompletion)
                .subscribe(
                    (Optional<QueueMessage<List<FileUpload>>> f) -> fileUploads.addAll(
                        f.get()
                            .message()
                            .stream()
                            .peek(fileUpload -> {
                                lock.writeLock().lock();
                                fileUpload.setDeserialized(true);
                            })
                            .collect(ImmutableListCollector.toImmutableFastList())

                    ));
        } finally {

            lock.writeLock().unlock();

        }

    }
}
