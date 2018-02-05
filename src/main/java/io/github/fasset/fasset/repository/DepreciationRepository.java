package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlyAssetDepreciationDTO;
import io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlySolDepreciationDTO;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
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
            "(SELECT DISTINCT e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=1)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=2)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=3)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=4)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=5)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=6)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=7)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=8)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=9)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=10)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=11)," +
            "(SELECT DISTINCT  e.depreciation FROM Depreciation e WHERE e.fixedAssetId =:assetId AND e.year=:year AND e.month=12)" +
            ") " +
            "FROM Depreciation e " +
            "WHERE e.fixedAssetId = :assetId AND e.year = :year")
    List<MonthlyAssetDepreciationDTO> getMonthlyAssetDepreciation(@Param("assetId") Integer assetId, @Param("year") Integer year);

    @Query("SELECT NEW io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlySolDepreciationDTO(" +
            "e.solId,e.year," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 1)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 2)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 3)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 4)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 5)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 6)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 7)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 8)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 9)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 10)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 11)," +
            "(SELECT SUM(e.depreciation) FROM Depreciation e WHERE e.solId = :solId AND e.year = :year AND e.month = 12)" +
            ")" +
            "FROM Depreciation e " +
            "WHERE e.solId = :solId AND e.year = :year")
    List<MonthlySolDepreciationDTO> getMonthlySolDepreciation(@Param("solId") String solId, @Param("year") Integer year);

    /**
     *
     * @return The number of distinct solIds
     */
    @Query("SELECT  COUNT(DISTINCT e.solId) " +
            "FROM Depreciation e")
    int countDistinctSolIds();
}
