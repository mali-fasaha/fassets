package io.github.fasset.fasset.kernel.batch;

import io.github.fasset.fasset.kernel.util.BatchJobExecutionException;
import org.slf4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@Component("briefUpdateJob")
public class BriefUpdateJob {

    private final static Logger log = getLogger(BriefUpdateJob.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("briefUploadJob")
    private Job briefUploadJob;

    public void updateBriefs() throws BatchJobExecutionException {

        log.info("Updating briefs...");

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", LocalDateTime.now().toString())
                .toJobParameters();

        try {
            jobLauncher.run(briefUploadJob,jobParameters);
        } catch (Throwable e) {

            String message = String.format("Exception encountered %s caused by %s,while launching job" +
                            "id %s, while processing fixedAssets at time %s",
                    e.getMessage(),e.getCause(),briefUploadJob.getName(),jobParameters.getString("time"));

            throw new BatchJobExecutionException(message,e);

        }

    }
}
