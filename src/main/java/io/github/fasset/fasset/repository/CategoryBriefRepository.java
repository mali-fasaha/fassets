package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.brief.CategoryBrief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryBriefRepository")
public interface CategoryBriefRepository extends JpaRepository<CategoryBrief, Integer>{

    CategoryBrief findDistinctByDesignation(String designation);

}
