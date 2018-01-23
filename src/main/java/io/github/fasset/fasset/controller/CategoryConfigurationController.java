package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.CategoryConfiguration;
import io.github.fasset.fasset.service.CategoryConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CategoryConfigurationController {

    private static final Logger log = LoggerFactory.getLogger(CategoryConfigurationController.class);

    @Autowired
    @Qualifier("categoryConfigurationService")
    private CategoryConfigurationService categoryConfigurationService;

    @GetMapping("/categories")
    public String categories(Model model){

        model.addAttribute("categories", categoryConfigurationService.getAllCategoryConfigurations());

        model.addAttribute("category",new CategoryConfiguration());

        return "forms/category";
    }

    @PostMapping(value="/categories")
    public String newCategory(@Valid @ModelAttribute CategoryConfiguration category, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            log.error("{} error(s) when saving the user-generated data {}",bindingResult.getErrorCount(),bindingResult.getAllErrors());

            return "forms/category";
        }else {
            categoryConfigurationService.saveCategoryConfiguration(category);
        }

        return "redirect:/categories";
    }

    @GetMapping(value="/categories/{id}/edit")
    public String handleEditGet(@PathVariable int id,Model model){

        log.info("Editing categaory id : {}",id);

        model.addAttribute("categories", categoryConfigurationService.getAllCategoryConfigurations());

        model.addAttribute("category",categoryConfigurationService.getCategoryConfigurationById(id));

        return "forms/category";
    }

    /*@RequestMapping(value="/category/{id}/edit", method = RequestMethod.POST)
    public String handleEdit(@Valid @ModelAttribute CategoryConfiguration category, BindingResult bindingResult){

        categoryConfigurationService.updateCategoryConfiguration(category);

        return "redirect:/categories";
    }*/
}
