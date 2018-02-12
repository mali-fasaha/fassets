package io.github.fasset.fasset.kernel.messaging;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**
 * This configuration settings configure an additional factory to handle traffic comming from the
 * depreciation algorithm
 *
 * @author edwin.njeru
 */
@Configuration
public class DepreciationJMSFactory {

    @Bean
    public JmsListenerContainerFactory<?> depreciationMessageFactory(ConnectionFactory connectionFactory,
                                                         DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        configurer.configure(factory,connectionFactory);

        return factory;
    }

    @Bean
    public MessageConverter depreciationMessageConverter(){

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }
}
