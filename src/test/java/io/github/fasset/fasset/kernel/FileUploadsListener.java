package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.kernel.subscriptions.AbstractSubscriber;
import io.github.fasset.fasset.kernel.subscriptions.Subscriber;
import io.github.fasset.fasset.kernel.subscriptions.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadsListener extends AbstractSubscriber implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(FileUploadsListener.class);

    public FileUploadsListener(String name) {
        super(name);
    }

    @Override
    protected void consumeUpdate(Update update) {

        String fileName = (String) update.getPayload();

        log.info("File name : {} has been received for processing",fileName);
    }
}
