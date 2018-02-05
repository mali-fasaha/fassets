package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("monthlySolDepreciationRepository")
public interface MonthlySolDepreciationRepository extends JpaRepository<MonthlySolDepreciation,Integer> {
}
