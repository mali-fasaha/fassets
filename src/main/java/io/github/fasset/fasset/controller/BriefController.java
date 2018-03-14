package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.dto.CategoryBriefResponseDto;
import io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.service.CategoryBriefService;
import io.github.fasset.fasset.service.ServiceOutletBriefService;
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
    public List<ServiceOutletBriefResponseDto> getServiceOutletBriefs(Model model){

        //TODO update other brief controllers
        return serviceOutletBriefService.fetchAllServiceOutletBriefs()
                .parallelStream()
                .map(ServiceOutletBriefResponseDto::new)
                .collect(ImmutableListCollector.toImmutableList());
    }

    @GetMapping("/briefs/serviceOutlets/data/{id}")
    @ResponseBody
    public ServiceOutletBriefResponseDto getServiceOutletBriefGivenId(@PathVariable("id") int id){

        return new ServiceOutletBriefResponseDto(serviceOutletBriefService.fetchServiceOutletBriefGivenId(id));
    }

    @GetMapping("/briefs/categories/data")
    @ResponseBody
    public List<CategoryBriefResponseDto> getCategoryBriefs(Model model){

        return categoryBriefService.fetchAllCategoryBriefs()
                .parallelStream()
                .map(CategoryBriefResponseDto::new)
                .collect(ImmutableListCollector.toImmutableList());
    }

    @GetMapping("/briefs/categories/data/{id}")
    @ResponseBody
    public CategoryBriefResponseDto getCategoryGivenId(@PathVariable("id") int id){

        return new CategoryBriefResponseDto(categoryBriefService.fetchCategoryBriefGivenId(id));
    }
}
