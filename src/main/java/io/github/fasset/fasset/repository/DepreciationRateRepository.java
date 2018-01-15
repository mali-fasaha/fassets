package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.DepreciationRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("depreciationRateRepository")
public interface DepreciationRateRepository extends JpaRepository<DepreciationRate,Integer> {
}
