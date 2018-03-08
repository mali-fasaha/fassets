package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository("monthlyCategoryDepreciationRepository")
public interface MonthlyCategoryDepreciationReposiory extends JpaRepository<MonthlyCategoryDepreciation, Integer> {
}
