package io.github.fasset.fasset.kernel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * We are not asserting anything. We are just checking how our patterns will behave
 */
public class FileUploadTopicTest {

    @Test
    public void fileUploadTopicWorks() throws Exception {

        // creating the subject
        FileUploadTopic topic = new FileUploadTopic();

        // create observers
        Observer obj1 = new FileUploadTopicSubscriber("obj1");
        Observer obj2 = new FileUploadTopicSubscriber("obj2");
        Observer obj3 = new FileUploadTopicSubscriber("obj3");

        // register observers to the subject
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        // attach observer to subject
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        // check if any update is available
        obj1.update();

        // now send the file to the topic
        topic.postFile("Data listing 15646");

        // now send a new file to the topic. Expecting observers to not read the former topic
        // only the current one
        topic.postFile("New Data listing 15647");

        // now send a new file to the topic. Expecting observers to not read the former topic
        // only the current one
        topic.postFile("Even Newer Data listing 15648");
    }
}