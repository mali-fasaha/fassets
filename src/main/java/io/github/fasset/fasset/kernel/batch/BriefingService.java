package io.github.fasset.fasset.kernel.batch;

public interface BriefingService {
    /**
     * Updates the summary for ServiceOutlets using data queried from the fixed assets
     * repository
     */
    void updateServiceOutletBriefs();


    /**
     * Updates the summary for CategoryBriefs using data queried from the fixed assets
     * repository
     */
    void updateCategoryBriefs();
}
