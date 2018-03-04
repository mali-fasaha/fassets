package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.AccruedDepreciation;
import org.apache.activemq.broker.BrokerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccruedDepreciationRepositoryTest {



    @Autowired
    @Qualifier("accruedDepreciationRepository")
    private AccruedDepreciationRepository accruedDepreciationRepository;
    @Autowired
    private BrokerService brokerService;

    @Before
    public void setUp() throws Exception {

        brokerService.start();
    }

    @Test
    public void accruedDepreciationRepoIsWorking() throws Exception{

        accruedDepreciationRepository.save(new AccruedDepreciation());

        assertNotNull(accruedDepreciationRepository.findById(1).get());
    }
}