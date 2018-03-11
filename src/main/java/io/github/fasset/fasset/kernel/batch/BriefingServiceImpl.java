package io.github.fasset.fasset.kernel.batch;

import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
import io.github.fasset.fasset.repository.FixedAssetRepository;
import io.github.fasset.fasset.service.CategoryBriefService;
import io.github.fasset.fasset.service.FixedAssetService;
import io.github.fasset.fasset.service.ServiceOutletBriefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("briefingService")
public class BriefingServiceImpl implements BriefingService {

    private static final Logger log = LoggerFactory.getLogger(BriefingServiceImpl.class);

    private final FixedAssetService fixedAssetService;

    private final FixedAssetRepository fixedAssetRepository;

    private final ServiceOutletBriefService serviceOutletBriefService;

    private final CategoryBriefService categoryBriefService;

    private final MoneyToDoubleConverter moneyToDoubleConverter;

    @Autowired
    public BriefingServiceImpl(@Qualifier("fixedAssetService") FixedAssetService fixedAssetService, @Qualifier("fixedAssetRepository") FixedAssetRepository fixedAssetRepository, @Qualifier("serviceOutletBriefService") ServiceOutletBriefService serviceOutletBriefService, @Qualifier("categoryBriefService") CategoryBriefService categoryBriefService, @Qualifier("moneyToDoubleConverter")MoneyToDoubleConverter moneyToDoubleConverter) {
        this.fixedAssetService = fixedAssetService;
        this.fixedAssetRepository = fixedAssetRepository;
        this.serviceOutletBriefService = serviceOutletBriefService;
        this.categoryBriefService = categoryBriefService;
        this.moneyToDoubleConverter=moneyToDoubleConverter;
    }


    private List<String> getAllCategoriesInRepo(){

        return fixedAssetService.getAllCategories();
    }

    private List<String> getAllSolIds(){

        return fixedAssetService.getAllSolIds();
    }

    private ServiceOutletBrief createServiceOutletBrief(String serviceOutlet){

        ServiceOutletBrief brief = new ServiceOutletBrief();
        brief.setDesignation(serviceOutlet);
        brief.setPurchaseCost(fixedAssetRepository.getTotalSolPurchaseCost(serviceOutlet));
        brief.setNetBookValue(fixedAssetRepository.getTotalSolNetBookValue(serviceOutlet));
        brief.setAccruedDepreciation(brief.getPurchaseCost().subtract(brief.getNetBookValue()));
        brief.setPoll(fixedAssetRepository.getTotalSolCount(serviceOutlet));

        return brief;
    }

    private CategoryBrief createCategoryBrief(String category){
        CategoryBrief brief = new CategoryBrief();
        brief.setDesignation(category);
        brief.setPurchaseCost(fixedAssetRepository.getTotalCategoryPurchaseCost(category));
        brief.setNetBookValue(fixedAssetRepository.getTotalCategoryNetBookValue(category));
        brief.setAccruedDepreciation(brief.getPurchaseCost().subtract(brief.getNetBookValue()));
        brief.setPoll(fixedAssetRepository.getTotalCategoryCount(category));

        return brief;
    }

    /**
     * Updates the summary for ServiceOutlets using data queried from the fixed assets
     * repository
     *
     */
    @Override
    public void updateServiceOutletBriefs(){

        List<ServiceOutletBrief> briefs = new ArrayList<>();

        getAllSolIds()
                .stream()
                .map(this::createServiceOutletBrief)
                .forEach(briefs::add);

        log.info("Adding a list of : {} serviceOutletBrief items into repo",briefs.size());

        serviceOutletBriefService.saveAllServiceOutletBriefItems(briefs);

    }

    @Override
    public void updateCategoryBriefs(){
        List<CategoryBrief> briefs = new ArrayList<>();

        getAllCategoriesInRepo()
                .stream()
                .map(this::createCategoryBrief)
                .forEach(briefs::add);

        log.info("Adding a list of : {} categoryBrief items into repo",briefs.size());

        categoryBriefService.saveAllCategoryBriefItems(briefs);
    }
}
