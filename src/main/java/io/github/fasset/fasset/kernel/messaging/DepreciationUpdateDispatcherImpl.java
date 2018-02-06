package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.model.AccruedDepreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("depreciationUpdateDispatcher")
public class DepreciationUpdateDispatcherImpl implements DepreciationUpdateDispatcher{

    private static final Logger log = LoggerFactory.getLogger(DepreciationUpdateDispatcherImpl.class);


    private final JmsTemplate jmsTemplate;

    private MessageConverter messageConverter;

    @Autowired
    public DepreciationUpdateDispatcherImpl(JmsTemplate jmsTemplate,MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setMessageConverter(messageConverter);
    }

    /**
     * Send netBookValue to messaging service
     *
     * @param netBookValueMessage
     */
    @Override
    public void sendNetBookValue(Message<NetBookValue> netBookValueMessage) {

        log.debug("Sending netBookValue : {} to the netBookValueQueue",netBookValueMessage);

        jmsTemplate.convertAndSend("netBookValueQueue",netBookValueMessage);
    }

    /**
     * Send accruedDepreciation to messaging service
     *
     * @param accruedDepreciationMessage
     */
    @Override
    public void sendAccruedDepreciation(Message<AccruedDepreciation> accruedDepreciationMessage) {

        log.debug("Sending accruedDepreciation : {} to the accruedDepreciationQueue",accruedDepreciationMessage);

        jmsTemplate.convertAndSend("accruedDepreciationQueue",accruedDepreciationMessage);
    }

    /**
     * Send fixedAsset item for further processing
     *
     * @param fixedAsset
     */
    @Override
    public void sendFixedAssetItem(Message<FixedAsset> fixedAsset) {

        log.debug("Sending fixedAsset item : {} to the fixedAssetItemsQueue",fixedAsset);

        jmsTemplate.convertAndSend("fixedAssetItemsQueue",fixedAsset);
    }
}
