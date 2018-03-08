package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository("monthlyAssetDepreciationRepository")
public interface MonthlyAssetDepreciationRepository extends JpaRepository<MonthlyAssetDepreciation,Integer>{

    /**
     * Returns an asset that corresponds the assetId and year given
     * @param assetId
     * @param year
     * @return
     */
    MonthlyAssetDepreciation findFirstByAssetIdAndYearEquals(int assetId, int year);


}
