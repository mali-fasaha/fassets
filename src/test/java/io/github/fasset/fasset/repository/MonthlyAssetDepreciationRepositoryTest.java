package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MonthlyAssetDepreciationRepositoryTest {


    @Qualifier("monthlyAssetDepreciationRepository")
    @Autowired
    private MonthlyAssetDepreciationRepository depreciationRepository;

    @Test
    public void monthlyAssetDepreciationWorks() throws Exception {

        assertNotNull(depreciationRepository.save(new MonthlyAssetDepreciation()));
    }
}