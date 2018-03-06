package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.AccruedDepreciation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class AccruedDepreciationRepositoryTest {



    @Autowired
    @Qualifier("accruedDepreciationRepository")
    private AccruedDepreciationRepository accruedDepreciationRepository;


    @Test
    public void accruedDepreciationRepoIsWorking() throws Exception{

        accruedDepreciationRepository.save(new AccruedDepreciation());

        assertNotNull(accruedDepreciationRepository.findById(1).get());
    }
}