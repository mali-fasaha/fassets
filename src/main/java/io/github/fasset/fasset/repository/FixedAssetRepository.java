package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository("fixedAssetRepository")
public interface FixedAssetRepository extends JpaRepository<FixedAsset,Integer>{

    @Query("SELECT " +
            "DISTINCT e.category " +
            "FROM FixedAsset e")
    List<String> getDistinctCategories();

    @Query("SELECT " +
            "DISTINCT e.solId " +
            "FROM FixedAsset e")
    List<String> getDistinctSolIds();

    /**
     * Return total purchase cost for a given category
     *
     * @param category of fixed asset
     * @return
     */
    @Query("Select "+
            "SUM(e.purchaseCost) " +
            "FROM FixedAsset e " +
            "WHERE e.category = :category ")
    double getTotalCategoryPurchaseCost(@Param("category") String category);

    @Query("SELECT " +
            "SUM(e.purchaseCost) " +
            "FROM FixedAsset e " +
            "WHERE e.solId = :solId ")
    double getTotalSolPurchaseCost(@Param("solId") String solId);

    /**
     * Return total net book value for a given category
     *
     * @param category of fixed asset
     * @return
     */
    @Query("Select "+
            "SUM(e.netBookValue) " +
            "FROM FixedAsset e " +
            "WHERE e.category = :category ")
    double getTotalCategoryNetBookValue(@Param("category") String category);

    @Query("SELECT " +
            "SUM(e.netBookValue) " +
            "FROM FixedAsset e " +
            "WHERE e.solId = :solId ")
    double getTotalSolNetBookValue(@Param("solId") String solId);

    /**
     * Return total no. of items for a given category
     *
     * @param category of fixed asset
     * @return
     */
    @Query("Select "+
            "COUNT(e.category) " +
            "FROM FixedAsset e " +
            "WHERE e.category = :category ")
    int getTotalCategoryCount(@Param("category") String category);

    @Query("SELECT " +
            "COUNT(e.solId) " +
            "FROM FixedAsset e " +
            "WHERE e.solId = :solId ")
    int getTotalSolCount(@Param("solId") String solId);


}
