package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import io.github.fasset.fasset.service.impl.MonthlyAssetDepreciationServiceImpl;
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


    @GetMapping("/reports/depreciations/assets")
    public String goToAssetMonthlyDepreciation(){

        return "reports/monthlyAsset";
    }

    @GetMapping("/reports/depreciations/assets/data")
    @ResponseBody
    public List<MonthlyAssetDepreciation> monthlyAssetDepreciationData(){

        return monthlyAssetDepreciationService.fetchAllMonthlyDepreciations();
    }
}
