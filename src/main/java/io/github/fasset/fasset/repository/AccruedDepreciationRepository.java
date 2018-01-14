package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.AccruedDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository("accruedDepreciationRepository")
public interface AccruedDepreciationRepository extends JpaRepository<AccruedDepreciation,Integer>{

    /**
     * Will return the AccruedDepreciation for a given fixedAssetId and the month before the month given
     * @param fixedAssetId
     * @param month
     * @return
     */
    AccruedDepreciation findByFixedAssetIdAndMonthBefore(int fixedAssetId, YearMonth month);
}
