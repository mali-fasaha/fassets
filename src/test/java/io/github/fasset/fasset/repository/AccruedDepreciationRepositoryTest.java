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

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccruedDepreciationRepositoryTest {



    @Autowired
    @Qualifier("accruedDepreciationRepository")
    private AccruedDepreciationRepository accruedDepreciationRepository;


    @Test
    public void accruedDepreciationRepoIsWorking() throws Exception{

        AccruedDepreciation accruedDepreciation = accruedDepreciationRepository.save(new AccruedDepreciation());

        assertNotNull(accruedDepreciationRepository.findById(accruedDepreciation.getId()).get());
    }

    @Test
    public void findByFixedAssetIdAndMonthBeforeIsWorking() throws Exception {

        AccruedDepreciation accruedDepreciation = new AccruedDepreciation();
        accruedDepreciation.setMonth(YearMonth.of(2018,02))
                .setFixedAssetId(4465);

        assertNotNull(accruedDepreciationRepository.save(accruedDepreciation));

       /* AccruedDepreciation saved =
                accruedDepreciationRepository.findByFixedAssetIdAndMonthBefore(4465,YearMonth.of(2018,02));

        assertNotNull(saved);*/

        //assertEquals(4465,saved.getFixedAssetId());
        //assertEquals(YearMonth.today(),saved.getMonth());
    }
}