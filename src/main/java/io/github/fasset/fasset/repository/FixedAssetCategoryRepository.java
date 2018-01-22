package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.FixedAssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("fixedAssetCategoryRepository")
public interface FixedAssetCategoryRepository extends JpaRepository<FixedAssetCategory, Integer>{

}
