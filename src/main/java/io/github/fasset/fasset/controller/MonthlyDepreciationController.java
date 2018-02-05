package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import io.github.fasset.fasset.service.MonthlySolDepreciationService;
import io.github.fasset.fasset.service.impl.MonthlyAssetDepreciationServiceImpl;
import io.github.fasset.fasset.service.impl.MonthlySolDepreciationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MonthlyDepreciationController {


    @Autowired
    @Qualifier("monthlyAssetDepreciationService")
    private MonthlyAssetDepreciationService monthlyAssetDepreciationService;

    @Autowired
    @Qualifier("monthlySolDepreciationService")
    private MonthlySolDepreciationService monthlySolDepreciationService;


    @GetMapping("/reports/depreciations/assets")
    public String goToAssetMonthlyDepreciation(){

        return "reports/monthlyAsset";
    }

    @GetMapping("/reports/depreciations/sols")
    public String goToSolMonthlyDepreciation(){

        return "reports/monthlySol";
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
}
