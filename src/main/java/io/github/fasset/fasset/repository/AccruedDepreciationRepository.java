package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.AccruedDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accruedDepreciationRepository")
public interface AccruedDepreciationRepository extends JpaRepository<AccruedDepreciation,Integer>{
}
