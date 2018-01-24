package io.github.fasset.fasset.kernel.batch.depreciation.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository("depreciationJobInstance")
public interface DepreciationJobInstanceRepository extends JpaRepository<DepreciationJobInstance,Integer>{

    List<DepreciationJobInstance> findAllByMonthBefore(YearMonth month);
}
