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
package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import io.github.fasset.fasset.repository.MonthlyCategoryDepreciationReposiory;
import io.github.fasset.fasset.service.MonthlyCategoryDepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * {@link io.github.fasset.fasset.service.MonthlyCategoryDepreciationService} implementation
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Service("monthlyCategoryDepreciationService")
@Transactional
public class MonthlyCategoryDepreciationServiceImpl implements MonthlyCategoryDepreciationService {


    private final MonthlyCategoryDepreciationReposiory depreciationReposiory;

    /**
     * <p>Constructor for MonthlyCategoryDepreciationServiceImpl.</p>
     *
     * @param depreciationReposiory a {@link io.github.fasset.fasset.repository.MonthlyCategoryDepreciationReposiory} object.
     */
    @Autowired
    public MonthlyCategoryDepreciationServiceImpl(@Qualifier("monthlyCategoryDepreciationRepository") MonthlyCategoryDepreciationReposiory depreciationReposiory) {
        this.depreciationReposiory = depreciationReposiory;
    }


    /** {@inheritDoc} */
    @Override
    public List<MonthlyCategoryDepreciation> fetchAllMonthlyCategoryDepreciations() {

        return depreciationReposiory.findAll()
                                    .parallelStream()
                                    .sorted(Comparator.comparing(MonthlyCategoryDepreciation::getCategoryName))
                                    .collect(ImmutableListCollector.toImmutableList());
    }

    /**
     * {@inheritDoc}
     *
     * Save all items in the parameter to the monthlyCategoryDepreciationRepository
     */
    @Override
    public void saveAllMonthlyCategoryDepreciations(List<? extends MonthlyCategoryDepreciation> items) {

        depreciationReposiory.saveAll(items);
    }
}
