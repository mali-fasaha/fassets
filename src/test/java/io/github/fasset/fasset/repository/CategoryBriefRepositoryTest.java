package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.brief.CategoryBrief;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryBriefRepositoryTest {

    CategoryBrief categoryBrief;
    @Qualifier("categoryBriefRepository")
    @Autowired
    private CategoryBriefRepository categoryBriefRepository;


    @Before
    public void setUp() throws Exception {

        categoryBrief = new CategoryBrief();
        categoryBrief.setAccruedDepreciation(560);
        categoryBrief.setDesignation("COMPUTERS");
        categoryBrief.setPoll(5);
        categoryBrief.setPurchaseCost(1000);
        categoryBrief.setNetBookValue(440);
    }

    @Test
    public void categoryRepositoryWorks() throws Exception {

        assertNotNull(categoryBriefRepository.save(categoryBrief));

        /*CategoryBrief savedCategoryBrief = categoryBriefRepository.findDistinctByDesignation("COMPUTERS");

        //assertEquals(1000,categoryBrief.getPurchaseCost(),0.0);
        assertNotNull(savedCategoryBrief.getId());*/
    }
}