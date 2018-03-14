package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import io.github.fasset.fasset.service.MonthlyCategoryDepreciationService;
import io.github.fasset.fasset.service.MonthlySolDepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MonthlyDepreciationController {


    private final MonthlyAssetDepreciationService monthlyAssetDepreciationService;
    private final MonthlySolDepreciationService monthlySolDepreciationService;
    private final MonthlyCategoryDepreciationService monthlyCategoryDepreciationService;

    @Autowired
    public MonthlyDepreciationController(@Qualifier("monthlyAssetDepreciationService") MonthlyAssetDepreciationService monthlyAssetDepreciationService, @Qualifier("monthlySolDepreciationService") MonthlySolDepreciationService monthlySolDepreciationService, @Qualifier("monthlyCategoryDepreciationService") MonthlyCategoryDepreciationService monthlyCategoryDepreciationService) {
        this.monthlyAssetDepreciationService = monthlyAssetDepreciationService;
        this.monthlySolDepreciationService = monthlySolDepreciationService;
        this.monthlyCategoryDepreciationService = monthlyCategoryDepreciationService;
    }


    @GetMapping("/reports/depreciations/assets")
    public String goToAssetMonthlyDepreciation(){

        return "reports/monthlyAsset";
    }

    @GetMapping("/reports/depreciations/sols")
    public String goToSolMonthlyDepreciation(){

        return "reports/monthlySol";
    }

    @GetMapping("/reports/depreciations/categories")
    public String goToCategoryMonthlyDepreciation(){

        return "reports/monthlyCategory";
    }

    @GetMapping("/reports/depreciations/assets/data")
    @ResponseBody
    public List<MonthlyAssetDepreciation> monthlyAssetDepreciationData(){

        return monthlyAssetDepreciationService.fetchAllMonthlyDepreciations();
    }

    @GetMapping("/reports/depreciations/sols/data")
    @ResponseBody
    public List<MonthlySolDepreciation> monthlySolDepreciationData(){

        return monthlySolDepreciationService.fetchAllMonthlySolDepreciations();
    }

    @GetMapping("/reports/depreciations/category/data")
    @ResponseBody
    public List<MonthlyCategoryDepreciation> monthylCategoryDepreciationData(){

        return monthlyCategoryDepreciationService.fetchAllMonthlyCategoryDepreciations();
    }
}
