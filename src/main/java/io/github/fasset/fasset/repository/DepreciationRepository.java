package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlyAssetDepreciationDTO;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
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

    @Query("SELECT NEW io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlyAssetDepreciationDTO(" +
            "e.fixedAssetId,e.year," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=1)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=2)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=3)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=4)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=5)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=6)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=7)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=8)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=9)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=10)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=11)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=12)" +
            ") " +
            "FROM Depreciation e " +
            "WHERE e.fixedAssetId = :assetId AND e.year = :year")
    MonthlyAssetDepreciationDTO getMonthlyAssetDepreciation(@Param("assetId") int assetId, @Param("year") int year);
}
