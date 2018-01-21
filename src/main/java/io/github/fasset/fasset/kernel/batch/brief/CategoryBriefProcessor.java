package io.github.fasset.fasset.kernel.batch.brief;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("categoryBriefProcessor")
public class CategoryBriefProcessor implements ItemProcessor<FixedAsset,CategoryBrief> {

    @Override
    public CategoryBrief process(FixedAsset fixedAsset) throws Exception {

        CategoryBrief categoryBrief = new CategoryBrief();
        categoryBrief.setDesignation(fixedAsset.getCategory());

        return categoryBrief;
    }
}
