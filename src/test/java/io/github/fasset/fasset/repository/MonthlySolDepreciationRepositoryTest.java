package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MonthlySolDepreciationRepositoryTest {


    @Qualifier("monthlySolDepreciationRepository")
    @Autowired
    private MonthlySolDepreciationRepository solDepreciationRepository;

    @Test
    public void monthlySolDepreciationRepositoryWorks() throws Exception {

        assertNotNull(solDepreciationRepository.save(new MonthlySolDepreciation()));
    }
}