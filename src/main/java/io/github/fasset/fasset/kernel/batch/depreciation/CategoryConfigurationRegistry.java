package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.model.nil.NilCategoryConfiguration;
import io.github.fasset.fasset.model.CategoryConfiguration;
import io.github.fasset.fasset.service.CategoryConfigurationService;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Using spring frameworks IOC container, this bean has a map into which the
 * categoryConfigurations are added as they are in the database. The bean also
 * exposes logic for programmatically adding more configurations or editing
 * existing ones
 *
 * @author edwin.njeru
 */
@Component("categoryConfigurationRegistry")
public class CategoryConfigurationRegistry {

    private static final Logger log = LoggerFactory.getLogger(CategoryConfigurationRegistry.class);

    private Map<String,CategoryConfiguration> categoryConfigurationMap = new ConcurrentHashMap<>();

    private final CategoryConfigurationService categoryConfigurationService;

    @Autowired
    public CategoryConfigurationRegistry(CategoryConfigurationService categoryConfigurationService) {
        this.categoryConfigurationService = categoryConfigurationService;
    }


    public CategoryConfiguration getCategoryConfiguration(String categoryName) {

        CategoryConfiguration categoryConfiguration;

        if(categoryConfigurationRegistryDoesNotContain(categoryName))
            updateConfigurationRegistry();

        if(categoryConfigurationRegistryDoesNotContain(categoryName)){
            log.error("The category Named : {} has not been configured in the category configuration repo. Please " +
                    "check your category configurations listing...",categoryName);
            categoryConfiguration = new NilCategoryConfiguration().getCategoryConfiguration();
        }

        categoryConfiguration = categoryConfigurationMap.get(categoryName);

        return categoryConfiguration;
    }

    void addCategoryConfiguration(String categoryName,CategoryConfiguration categoryConfiguration){
        categoryConfigurationMap.put(categoryName,categoryConfiguration);
    }

    boolean categoryConfigurationRegistryDoesNotContain(String categoryName){
        return !categoryConfigurationMap.containsKey(categoryName);
    }

    boolean categoryConfigurationRegistryIsEmpty(){
        return categoryConfigurationMap.isEmpty();
    }

    @PostConstruct
    private void updateConfigurationRegistry() {

        if(categoryConfigurationMap.isEmpty()) {

            log.trace("Refreshing the category configuration mapping...");

            categoryConfigurationService
                    .getAllCategoryConfigurations()
                    .stream()
                    .map(CategoryConfiguration::getDesignation)
                    .forEach(categoryName -> {
                        log.trace("Registering category : {}",categoryName);
                        categoryConfigurationMap.put(categoryName, categoryConfigurationService.getCategoryByName(categoryName));
                    });
        }

    }
}
