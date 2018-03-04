package io.github.fasset;

import org.apache.activemq.broker.BrokerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class TestContextConfiguration {

    @Bean
    public BrokerService brokerService(){
        BrokerService broker = new BrokerService();

       // configure the broker
        try {
            broker.addConnector("tcp://localhost:61616");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //broker.start();

        return broker;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return Mockito.mock(JmsTemplate.class);
    }
}
