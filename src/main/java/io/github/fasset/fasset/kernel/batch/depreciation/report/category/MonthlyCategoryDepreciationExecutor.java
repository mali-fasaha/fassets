package io.github.fasset.fasset.kernel.batch.depreciation.report.category;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;

public interface MonthlyCategoryDepreciationExecutor {

    /**
     *
     * @param categoryName Name of the category we wish to summarise
     * @param year Year of the depreciation
     * @return {@link MonthlyCategoryDepreciation } item relevant to the categoryName given
     * and the year
     *
     */
    MonthlyCategoryDepreciation getMonthlyDepreciation(String categoryName, Integer year);
}
