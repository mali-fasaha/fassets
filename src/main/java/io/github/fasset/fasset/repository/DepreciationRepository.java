package io.github.fasset.fasset.repository;

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

    @Query("SELECT NEW io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation(" +
            "d.fixedAssetId,d.year," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=1)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=2)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=3)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=4)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=5)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=6)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=7)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=8)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=9)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=10)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=11)," +
            "(SELECT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND d.month=12)" +
            ") " +
            "FROM Depreciation d " +
            "WHERE d.fixedAssetId = :assetId AND d.year = :year")
    MonthlyAssetDepreciation getMonthlyAssetDepreciation(@Param("assetId") int assetId,@Param("year") int year);
}
