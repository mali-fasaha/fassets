package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.CategoryConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository("categoryConfigurationRepository")
public interface CategoryConfigurationRepository extends JpaRepository<CategoryConfiguration, Integer>{

    CategoryConfiguration findFirstByDesignation(String designation);

}
