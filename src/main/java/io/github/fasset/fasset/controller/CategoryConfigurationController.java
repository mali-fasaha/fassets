package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.FixedAssetCategory;
import io.github.fasset.fasset.service.FixedAssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryConfigurationController {

    @Autowired
    @Qualifier("fixedAssetCategoryService")
    private FixedAssetCategoryService fixedAssetCategoryService;

    @GetMapping("/categories")
    public String categories(Model model){

        model.addAttribute("categories",fixedAssetCategoryService.getAllFixedAssetCategories());

        return "forms/category";
    }

    @PostMapping("/categories")
    public String newCategory(@ModelAttribute("category") FixedAssetCategory category, BindingResult errors){

        FixedAssetCategory fixedAssetCategory = new FixedAssetCategory();
        fixedAssetCategory.setDesignation(category.getDesignation());
        fixedAssetCategory.setDepreciationLogic(category.getDepreciationLogic());
        fixedAssetCategory.setDepreciationRate(category.getDepreciationRate());
        fixedAssetCategory.setDeprecant(category.getDeprecant());

        fixedAssetCategoryService.saveFixedAssetCategory(fixedAssetCategory);

        return "redirect:/categories";
    }
}
