/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.dto.CategoryBriefResponseDto;
import io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.brief.CategoryBrief;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
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

/**
 * This controller serves up to the view data containing{@link io.github.fasset.fasset.model.brief.CategoryBrief} and {@link io.github.fasset.fasset.model.brief.ServiceOutletBrief} items from the data
 * sink
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Controller
public class BriefController {


    private final CategoryBriefService categoryBriefService;


    private final ServiceOutletBriefService serviceOutletBriefService;


    /**
     * <p>Constructor for BriefController.</p>
     *
     * @param categoryBriefService      a {@link io.github.fasset.fasset.service.CategoryBriefService} object.
     * @param serviceOutletBriefService a {@link io.github.fasset.fasset.service.ServiceOutletBriefService} object.
     */
    @Autowired
    public BriefController(@Qualifier("categoryBriefService") CategoryBriefService categoryBriefService, @Qualifier("serviceOutletBriefService") ServiceOutletBriefService serviceOutletBriefService) {
        this.categoryBriefService = categoryBriefService;
        this.serviceOutletBriefService = serviceOutletBriefService;
    }

    /**
     * <p>getCategoryBriefsTemplate.</p>
     *
     * @param model a {@link org.springframework.ui.Model} object.
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/briefs/categories")
    public String getCategoryBriefsTemplate(Model model) {

        return "briefs/categoryBriefs";
    }

    /**
     * <p>getServiceOutletBriefsTemplate.</p>
     *
     * @param model a {@link org.springframework.ui.Model} object.
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/briefs/serviceOutlets")
    public String getServiceOutletBriefsTemplate(Model model) {

        return "briefs/serviceOutletBriefs";
    }

    /**
     * <p>getServiceOutletBriefs.</p>
     *
     * @param model a {@link org.springframework.ui.Model} object.
     * @return a {@link java.util.List} object.
     */
    @GetMapping("/briefs/serviceOutlets/data")
    @ResponseBody
    public List<ServiceOutletBriefResponseDto> getServiceOutletBriefs(Model model) {

        //TODO update other brief controllers
        return serviceOutletBriefService.fetchAllServiceOutletBriefs().parallelStream().map(ServiceOutletBriefResponseDto::new).collect(ImmutableListCollector.toImmutableList());
    }

    /**
     * <p>getServiceOutletBriefGivenId.</p>
     *
     * @param id a int.
     * @return a {@link io.github.fasset.fasset.dto.ServiceOutletBriefResponseDto} object.
     */
    @GetMapping("/briefs/serviceOutlets/data/{nomenclature}")
    @ResponseBody
    public ServiceOutletBriefResponseDto getServiceOutletBriefGivenId(@PathVariable("nomenclature") int id) {

        return new ServiceOutletBriefResponseDto(serviceOutletBriefService.fetchServiceOutletBriefGivenId(id));
    }

    /**
     * <p>getCategoryBriefs.</p>
     *
     * @param model a {@link org.springframework.ui.Model} object.
     * @return a {@link java.util.List} object.
     */
    @GetMapping("/briefs/categories/data")
    @ResponseBody
    public List<CategoryBriefResponseDto> getCategoryBriefs(Model model) {

        return categoryBriefService.fetchAllCategoryBriefs().parallelStream().map(CategoryBriefResponseDto::new).collect(ImmutableListCollector.toImmutableList());
    }

    /**
     * <p>getCategoryGivenId.</p>
     *
     * @param id a int.
     * @return a {@link io.github.fasset.fasset.dto.CategoryBriefResponseDto} object.
     */
    @GetMapping("/briefs/categories/data/{nomenclature}")
    @ResponseBody
    public CategoryBriefResponseDto getCategoryGivenId(@PathVariable("nomenclature") int id) {

        return new CategoryBriefResponseDto(categoryBriefService.fetchCategoryBriefGivenId(id));
    }
}
