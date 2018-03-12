package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.config.MoneyProperties;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import org.javamoney.moneta.Money;
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
    @Autowired
    private MoneyProperties moneyProperties;


    @Before
    public void setUp() throws Exception {

        categoryBrief = new CategoryBrief();
        categoryBrief.setAccruedDepreciation(Money.of(560,moneyProperties.getDefaultCurrency()));
        categoryBrief.setDesignation("COMPUTERS");
        categoryBrief.setPoll(5);
        categoryBrief.setPurchaseCost(Money.of(1000,moneyProperties.getDefaultCurrency()));
        categoryBrief.setNetBookValue(Money.of(440,moneyProperties.getDefaultCurrency()));
    }

    @Test
    public void categoryRepositoryWorks() throws Exception {

        assertNotNull(categoryBriefRepository.save(categoryBrief));

        /*CategoryBrief savedCategoryBrief = categoryBriefRepository.findDistinctByDesignation("COMPUTERS");

        //assertEquals(1000,categoryBrief.getPurchaseCost(),0.0);
        assertNotNull(savedCategoryBrief.getId());*/
    }
}