package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.CategoryConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryConfigurationRepository")
public interface CategoryConfigurationRepository extends JpaRepository<CategoryConfiguration, Integer>{

    CategoryConfiguration findFirstByDesignation(String designation);
}
