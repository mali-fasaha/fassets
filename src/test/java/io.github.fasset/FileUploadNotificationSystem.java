package io.github.fasset;

import io.github.fasset.fasset.kernel.messaging.FileUploadNotification;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadNotificationSystem {

    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Test
    public void jmsTemplate_loads_in_context() throws Exception{

        assertNotNull(jmsTemplate);
    }

    @Test
    public void jmsNotifierWorks() throws Exception{

        ActiveMQQueue destination = new ActiveMQQueue();

        jmsTemplate.setDefaultDestination(destination);

        jmsTemplate.convertAndSend(new FileUploadNotification("testFile","jan 2016","1424"));
    }
}
