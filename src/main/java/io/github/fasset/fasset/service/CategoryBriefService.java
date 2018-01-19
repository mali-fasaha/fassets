package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.brief.CategoryBrief;

import java.util.List;

public interface CategoryBriefService {

    /**
     *
     * @return {@link List<CategoryBrief>} fetched from the repository
     */
    List<CategoryBrief> fetchAllCategoryBriefs();

    /**
     *
     * @param id of the CategoryBrief
     * @return {@link CategoryBrief} of the id given as param
     */
    CategoryBrief fetchCategoryBriefGivenId(int id);

    /**
     *
     * @param categoryBriefs items to be saved to repository
     */
    void saveAllCategoryBriefItems(List<? extends CategoryBrief> categoryBriefs);
}
