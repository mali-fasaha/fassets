package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.service.CategoryBriefService;
import io.github.fasset.fasset.service.ServiceOutletBriefService;
import io.github.fasset.fasset.service.impl.CategoryBriefServiceImpl;
import io.github.fasset.fasset.service.impl.ServiceOutletBriefServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getCategoryBriefs(Model model){

        return "briefs/categoryBriefs";
    }

    @GetMapping("/briefs/serviceOutlets")
    public String getServiceOutletBriefs(Model model){

        return "briefs/serviceOutletBriefs";
    }

    //TODO include rest controllers
}
