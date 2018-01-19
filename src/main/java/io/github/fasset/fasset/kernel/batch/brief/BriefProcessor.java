package io.github.fasset.fasset.kernel.batch.brief;

import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.service.FixedAssetService;
import io.github.fasset.fasset.service.impl.FixedAssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

public class BriefProcessor {


    private final FixedAssetService fixedAssetService;

    @Autowired
    public BriefProcessor(@Qualifier("fixedAssetService") FixedAssetService fixedAssetService) {
        this.fixedAssetService = fixedAssetService;
    }

    public Set<CategoryBrief> getAllCategoryBrief(){
        
    }
}
