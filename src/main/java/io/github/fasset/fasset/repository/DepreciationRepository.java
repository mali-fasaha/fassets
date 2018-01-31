package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.Depreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.YearMonth;
import java.util.List;

@Repository("depreciationRepository")
public interface DepreciationRepository extends JpaRepository<Depreciation,Integer> {

    @Query("SELECT " +
            "DISTINCT e.depreciationPeriod " +
            "FROM Depreciation e")
    List<YearMonth> getDistinctDepreciationPeriods();

    Depreciation getDepreciationByDepreciationPeriodAndFixedAssetId(YearMonth depreciationPeriod,int fixedAssetId);
}
