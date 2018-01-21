package io.github.fasset.fasset.kernel.batch.brief;

import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.service.CategoryBriefService;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("categoryBriefWriter")
public class CategoryBriefWriter implements ItemWriter<CategoryBrief>{

    private final static Logger log = LoggerFactory.getLogger(CategoryBriefWriter.class);

    @Autowired
    @Qualifier("fixedAssetService")
    private FixedAssetService fixedAssetService;

    @Autowired
    @Qualifier("categoryBriefService")
    private CategoryBriefService categoryBriefService;

    @Override
    public void write(List<? extends CategoryBrief> list) throws Exception {

        List<CategoryBrief> categoryBriefs = new ArrayList<>();

        list.forEach(
                i ->
                    categoryBriefs.add(fixedAssetService.getCategoryBrief(i.getDesignation()))

        );

        log.info("Saving a list of  {} categoryBrief to the categoryBrief repo",list.size());

        categoryBriefService.saveAllCategoryBriefItems(categoryBriefs);
    }
}
