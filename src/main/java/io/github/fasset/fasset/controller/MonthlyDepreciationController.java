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

/**
 * Controller for MonthlyDepreciation views
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Controller
public class MonthlyDepreciationController {


    private final MonthlyAssetDepreciationService monthlyAssetDepreciationService;
    private final MonthlySolDepreciationService monthlySolDepreciationService;
    private final MonthlyCategoryDepreciationService monthlyCategoryDepreciationService;

    /**
     * <p>Constructor for MonthlyDepreciationController.</p>
     *
     * @param monthlyAssetDepreciationService    a {@link io.github.fasset.fasset.service.MonthlyAssetDepreciationService} object.
     * @param monthlySolDepreciationService      a {@link io.github.fasset.fasset.service.MonthlySolDepreciationService} object.
     * @param monthlyCategoryDepreciationService a {@link io.github.fasset.fasset.service.MonthlyCategoryDepreciationService} object.
     */
    @Autowired
    public MonthlyDepreciationController(@Qualifier("monthlyAssetDepreciationService") MonthlyAssetDepreciationService monthlyAssetDepreciationService,
                                         @Qualifier("monthlySolDepreciationService") MonthlySolDepreciationService monthlySolDepreciationService,
                                         @Qualifier("monthlyCategoryDepreciationService") MonthlyCategoryDepreciationService monthlyCategoryDepreciationService) {
        this.monthlyAssetDepreciationService = monthlyAssetDepreciationService;
        this.monthlySolDepreciationService = monthlySolDepreciationService;
        this.monthlyCategoryDepreciationService = monthlyCategoryDepreciationService;
    }


    /**
     * <p>goToAssetMonthlyDepreciation.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/reports/depreciations/assets")
    public String goToAssetMonthlyDepreciation() {

        return "reports/monthlyAsset";
    }

    /**
     * <p>goToSolMonthlyDepreciation.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/reports/depreciations/sols")
    public String goToSolMonthlyDepreciation() {

        return "reports/monthlySol";
    }

    /**
     * <p>goToCategoryMonthlyDepreciation.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/reports/depreciations/categories")
    public String goToCategoryMonthlyDepreciation() {

        return "reports/monthlyCategory";
    }

    /**
     * <p>monthlyAssetDepreciationData.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @GetMapping("/reports/depreciations/assets/data")
    @ResponseBody
    public List<MonthlyAssetDepreciation> monthlyAssetDepreciationData() {

        return monthlyAssetDepreciationService.fetchAllMonthlyDepreciations();
    }

    /**
     * <p>monthlySolDepreciationData.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @GetMapping("/reports/depreciations/sols/data")
    @ResponseBody
    public List<MonthlySolDepreciation> monthlySolDepreciationData() {

        return monthlySolDepreciationService.fetchAllMonthlySolDepreciations();
    }

    /**
     * <p>monthylCategoryDepreciationData.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @GetMapping("/reports/depreciations/category/data")
    @ResponseBody
    public List<MonthlyCategoryDepreciation> monthylCategoryDepreciationData() {

        return monthlyCategoryDepreciationService.fetchAllMonthlyCategoryDepreciations();
    }
}
