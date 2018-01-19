package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.util.DataRetrievalFromControllerException;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.repository.CategoryBriefRepository;
import io.github.fasset.fasset.service.CategoryBriefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Service("categoryBriefService")
public class CategoryBriefServiceImpl implements CategoryBriefService {

    private static final Logger log = LoggerFactory.getLogger(CategoryBriefServiceImpl.class);


    @Qualifier("categoryBriefRepository")
    @Autowired
    private CategoryBriefRepository categoryBriefRepository;

    /**
     * @return {@link List < CategoryBrief >} fetched from the repository
     */
    @Override
    public List<CategoryBrief> fetchAllCategoryBriefs() {

        List<CategoryBrief> categoryBriefs;

        log.info("Fetching with categoryBriefs from repo...");

        try {
            categoryBriefs = categoryBriefRepository.findAll();
        } catch (Throwable e) {
            String message = "Error fetching categoryBriefs from repository";
            throw new DataRetrievalFromControllerException(message,e);
        }

        log.info("Returning a list of : {} from the repository",categoryBriefs.size());

        return categoryBriefs;
    }

    /**
     * @param id of the CategoryBrief
     * @return {@link CategoryBrief} of the id given as param
     */
    @Override
    public CategoryBrief fetchCategoryBriefGivenId(int id) {

        CategoryBrief categoryBrief = null;

        try {
            categoryBrief = categoryBriefRepository.findById(id).get();
        } catch (Throwable e) {
            String message = "Error fetching categoryBriefs from repository";
            throw new DataRetrievalFromControllerException(message,e);
        }

        log.debug("Returning category : {} from repository",categoryBrief);

        return categoryBrief;
    }

    /**
     * @param categoryBriefs items to be saved to repository
     */
    @Override
    @Transactional
    public void saveAllCategoryBriefItems(List<? extends CategoryBrief> categoryBriefs) {

        Collection<CategoryBrief> briefs = categoryBriefRepository.findAll();

        Set<CategoryBrief> briefSet = new ConcurrentSkipListSet<>();

        briefSet.addAll(briefs);

        categoryBriefRepository.deleteAll();

        categoryBriefRepository.saveAll(briefSet);
    }
}
