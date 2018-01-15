package io.github.fasset.fasset.kernel.batch;

import org.springframework.batch.core.JobExecutionException;

public class BatchJobExecutionException extends JobExecutionException{

    public BatchJobExecutionException(String msg) {
        super(msg);
    }

    public BatchJobExecutionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
