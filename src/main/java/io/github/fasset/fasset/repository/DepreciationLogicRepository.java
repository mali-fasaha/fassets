package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.DepreciationLogic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("depreciationLogicRepository")
public interface DepreciationLogicRepository extends JpaRepository<DepreciationLogic,Integer> {
}
