package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import io.github.fasset.fasset.kernel.util.queue.MQException;
import io.github.fasset.fasset.kernel.util.queue.MessageQueue;
import io.github.fasset.fasset.model.files.FileUpload;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.time.LocalDateTime;
import java.time.YearMonth;

import static org.junit.Assert.*;

public class FileUploadsQueueTest {

    private MessageQueue<FileUpload> messageQueue;

    private FileUploadService fileUploadService;

    private FileUploadNotification fileUploadNotification;

    private FileUpload fileUpload = new FileUpload();

    private boolean uploaded;

    @Before
    public void setUp() throws Exception {

        fileUploadService = Mockito.mock(FileUploadService.class);

        Mockito.doAnswer(this::setUploadedTrue).when(fileUploadService).recordFileUpload(fileUpload);

        messageQueue = new FileUploadsQueue(fileUploadService);
    }

    @Test
    public void push() {

        messageQueue.push(() -> fileUpload);

        assertTrue(uploaded);
    }

    @Test(expected = MQException.class)
    public void throwMQException() {

        messageQueue.push(
            () -> fileUpload, () -> {throw new MQException();}
        );
    }

    @Test(expected = DeserializedFileException.class)
    public void throwDeserializedFileException() {

        FileUpload fileUpload = new FileUpload("Upload File Test",YearMonth.now(), LocalDateTime.now());
        fileUpload.setDeserialized(true);

        messageQueue.push(
            () -> fileUpload,
            (e) -> System.out.println(String.format("Exception thrown: %s", e)),
            () -> fileUpload.setDeserialized(false));

        // Due to exception in 1st param, Queue will never reset the isDeserialized variable
        assertTrue(fileUpload.isDeserialized());
    }

    private Object setUploadedTrue(InvocationOnMock invocation) {

        uploaded = true;

        return null;
    }
}