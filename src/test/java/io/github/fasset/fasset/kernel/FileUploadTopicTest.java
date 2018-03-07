package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.kernel.notifications.FileUploadNotification;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * We are not asserting anything. We are just checking how the observer pattern will behave
 */
public class FileUploadTopicTest {

    @Test
    public void fileUploadTopicWorks() throws Exception {

        // creating the subject
        SimpleSubscription topic = new SimpleSubscription();

        // create observers
        Subscriber obj1 = new FileUploadsListener("FileUploadListener 1");
        Subscriber obj2 = new FileUploadsListener("FileUploadListener 2");
        Subscriber obj3 = new FileUploadsListener("FileUploadListener 3");

        // registerSubscriber observers to the subject
        topic.registerSubscriber(obj1);
        topic.registerSubscriber(obj2);
        topic.registerSubscriber(obj3);

        // attach observer to subject
        obj1.addSubscription(topic);
        obj2.addSubscription(topic);
        obj3.addSubscription(topic);

        // check if any update is available
        obj1.update();

        // now send the file to the topic
        //topic.postUpdate("Data listing 15646");
        topic.postUpdate(() -> "Data listing 15646");

        // now send a new file to the topic. Expecting observers to not read the former topic
        // only the current one
        //topic.postUpdate("New Data listing 15647");
        topic.postUpdate(() -> "New Data listing 15647");

        // now send a new file to the topic. Expecting observers to not read the former topic
        // only the current one
        //topic.postUpdate("Even Newer Data listing 15648");
        topic.postUpdate(() -> "Even Newer Data listing 15648");

    }

    @Test
    public void subscriptionForFileUploadsShouldWork() throws Exception {

        Subscription fileUploadsSubscritionService = new SimpleSubscription();

        Subscriber fileUploadsSubscriber = new CompositeFileUploadsSubscriber("FileUploadsSubscriber1");

        fileUploadsSubscritionService.registerSubscriber(fileUploadsSubscriber);

        fileUploadsSubscriber.addSubscription(fileUploadsSubscritionService);


        fileUploadsSubscritionService.postUpdate(() -> new FileUploadNotification("Data listing 15482","Jan 2018", LocalDateTime.now().toString()));
    }
}