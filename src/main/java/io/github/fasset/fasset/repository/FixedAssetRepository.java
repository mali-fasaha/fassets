package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository("fixedAssetRepository")
public interface FixedAssetRepository extends JpaRepository<FixedAsset,Integer>{

    /**
     * Return total purchase cost for a given category
     *
     * @param category of fixed asset
     * @return
     */
    @Query("Select "+
            "SUM(e.purchaseCost) " +
            "FROM FixedAsset e " +
            "WHERE e.category = :category " +
            "GROUP BY e.category " +
            "ORDER BY e.category desc")
    double getTotalCategoryPurchaseCost(String category);

    /**
     * Return total net book value for a given category
     *
     * @param category of fixed asset
     * @return
     */
    @Query("Select "+
            "SUM(e.netBookValue) " +
            "FROM FixedAsset e " +
            "WHERE e.category = :category " +
            "GROUP BY e.category " +
            "ORDER BY e.category desc")
    double getTotalCategoryNetBookValue(String category);

    /**
     * Return total no. of items for a given category
     *
     * @param category of fixed asset
     * @return
     */
    @Query("Select "+
            "COUNT(e.category) " +
            "FROM FixedAsset e " +
            "WHERE e.category = :category " +
            "GROUP BY e.category " +
            "ORDER BY e.category desc")
    double getTotalCategoryCount(String category);


}
