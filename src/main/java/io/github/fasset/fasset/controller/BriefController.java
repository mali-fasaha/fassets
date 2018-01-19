package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
import io.github.fasset.fasset.service.CategoryBriefService;
import io.github.fasset.fasset.service.ServiceOutletBriefService;
import io.github.fasset.fasset.service.impl.CategoryBriefServiceImpl;
import io.github.fasset.fasset.service.impl.ServiceOutletBriefServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BriefController {


    private final CategoryBriefService categoryBriefService;


    private final ServiceOutletBriefService serviceOutletBriefService;


    @Autowired
    public BriefController(@Qualifier("categoryBriefService") CategoryBriefService categoryBriefService, @Qualifier("serviceOutletBriefService") ServiceOutletBriefService serviceOutletBriefService) {
        this.categoryBriefService = categoryBriefService;
        this.serviceOutletBriefService = serviceOutletBriefService;
    }

    @GetMapping("/briefs/categories")
    public String getCategoryBriefsTemplate(Model model){

        return "briefs/categoryBriefs";
    }

    @GetMapping("/briefs/serviceOutlets")
    public String getServiceOutletBriefsTemplate(Model model){

        return "briefs/serviceOutletBriefs";
    }

    @GetMapping("/briefs/serviceOutlets/data")
    @ResponseBody
    public List<ServiceOutletBrief> getServiceOutletBriefs(Model model){

        return serviceOutletBriefService.fetchAllServiceOutletBriefs();
    }

    @GetMapping("/briefs/serviceOutlets/data/{id}")
    @ResponseBody
    public ServiceOutletBrief getServiceOutletBriefGivenId(@PathVariable("id") int id){

        return serviceOutletBriefService.fetchServiceOutletBriefGivenId(id);
    }

    @GetMapping("/briefs/categories/data")
    @ResponseBody
    public List<CategoryBrief> getCategoryBriefs(Model model){

        return categoryBriefService.fetchAllCategoryBriefs();
    }

    @GetMapping("/briefs/categories/data/{id}")
    @ResponseBody
    public CategoryBrief getCategoryGivenId(@PathVariable("id") int id){

        return categoryBriefService.fetchCategoryBriefGivenId(id);
    }
}
