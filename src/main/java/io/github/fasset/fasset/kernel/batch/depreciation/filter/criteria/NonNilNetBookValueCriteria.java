package io.github.fasset.fasset.kernel.batch.depreciation.filter.criteria;

import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("nonNillNetBookValueCriteria")
public class NonNilNetBookValueCriteria implements Criteria {

    private static final Logger log = LoggerFactory.getLogger(NonNilNetBookValueCriteria.class);

    /**
     * Filtered object which is returned meets the criteria desired
     *
     * @param object Object being filtered
     * @return Object successfully meeting criteria
     */
    @Override
    public boolean meetsCriteria(Object object) {

        // crickets

        return false;
    }
}
