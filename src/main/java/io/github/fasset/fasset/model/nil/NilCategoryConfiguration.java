package io.github.fasset.fasset.model.nil;

import io.github.fasset.fasset.model.CategoryConfiguration;

public class NilCategoryConfiguration {

    private String designation = "Category";

    /**
     * The name of the depreciation logic
     */
    private String depreciationLogic="DECLININGBALANCE";

    /**
     * This is the item on which the depreciation rate is applied, as in either the cost
     * or the net book value
     */
    private String deprecant = "NETBOOKVALUE";

    private double depreciationRate = 0.00;

    private String categoryLedgerId = "0000000000";

    public CategoryConfiguration getCategoryConfiguration() {
        return new CategoryConfiguration(designation,depreciationLogic,deprecant,depreciationRate,categoryLedgerId);
    }
}
