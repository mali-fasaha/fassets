package io.github.fasset.fasset.kernel.batch.depreciation.filter.criteria;

public interface Criteria {

    /**
     * Filtered object which is returned meets the criteria desired
     *
     * @param object Object being filtered
     * @return Object successfully meeting criteria
     */
    boolean meetsCriteria(Object object);
}
