package io.github.fasset.fasset.kernel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadTopicSubscriber implements Observer {

    private static final Logger log = LoggerFactory.getLogger(FileUploadTopicSubscriber.class);

    // name of subscriber
    private String name;
    private Subject topic;


    public FileUploadTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {

        String fileName = (String) topic.getUpdate(this);

        if(fileName == null)
            log.debug("{}:: No new file",name);
        else{

            log.debug("{}:: Consuming file::{}",name, fileName);

            // file consumption logic
        }


    }

    @Override
    public void setSubject(Subject subject) {

        this.topic=subject;
    }
}
