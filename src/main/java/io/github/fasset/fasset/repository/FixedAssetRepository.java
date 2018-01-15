package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("fixedAssetRepository")
public interface FixedAssetRepository extends JpaRepository<FixedAsset,Integer>{
}
