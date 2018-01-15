package io.github.fasset.fasset.kernel.batch;

import io.github.fasset.fasset.kernel.messaging.model.FileUploadNotification;
import org.slf4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This object bootstraps an excel upload job after the file has been uploaded to the server
 */
@Component("excelUploadJob")
public class ExcelUploadJob {

    private final static Logger log = getLogger(ExcelUploadJob.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("importExcelJob")
    private Job importExcelJob;

    public ExcelUploadJob() {
    }

    /**
     * Systematically processes connect that can be extracted from an excel file provided as argument
     *
     * @param filePath from which we are reading the business domain connect
     */
    public void uploadExcelFile(String filePath,String month) throws BatchJobExecutionException {

        log.info("Uploading excel file on the path : {}", filePath);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("fileName",filePath)
                .addString("month",month)
                .addString("time", LocalDateTime.now().toString())
                .toJobParameters();

        try {
            jobLauncher.run(importExcelJob,jobParameters);
        } catch (Throwable e) {

            String message = String.format("Exception encountered %s caused by %s,while launching job" +
                            "id %s, reading from excel file : %s at time %s",
                    e.getMessage(),e.getCause(),importExcelJob.getName(),jobParameters.getString("fileName"),jobParameters.getString("time"));

            throw new BatchJobExecutionException(message,e);

        }

    }

    /**
     * Listens for messages from the queue
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination = "fileUploads", containerFactory = "messageFactory")
    public void listenForMessages(FileUploadNotification message){

        String fileName = message.getFileName();
        String month = message.getMonth();

        log.debug("File : {} has been received on the server side and is about to be actioned",fileName);

        try {
            uploadExcelFile(fileName,month);
        } catch (BatchJobExecutionException e) {
            log.error("Exception encountered while uploading excel file from : {}",fileName);
        }
    }
}
