package io.github.fasset.fasset.kernel.util;

import org.apache.activemq.command.ActiveMQMessage;

import javax.jms.JMSException;

public class ActiveMQMessageExt extends ActiveMQMessage {
    @Override
    public long getJMSDeliveryTime() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSDeliveryTime(long l) throws JMSException {

    }

    @Override
    public <T> T getBody(Class<T> aClass) throws JMSException {
        return null;
    }

    @Override
    public boolean isBodyAssignableTo(Class aClass) throws JMSException {
        return false;
    }
}
