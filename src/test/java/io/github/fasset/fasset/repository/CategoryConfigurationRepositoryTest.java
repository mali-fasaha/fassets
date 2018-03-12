package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.CategoryConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryConfigurationRepositoryTest {


    @Qualifier("categoryConfigurationRepository")
    @Autowired
    private CategoryConfigurationRepository categoryConfigurationRepository;

    @Test
    public void categoryConfigurationRepositoryWorks() throws Exception {

        assertNotNull(categoryConfigurationRepository.save(new CategoryConfiguration()));
    }
}