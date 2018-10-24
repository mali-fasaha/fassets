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
import io.github.fasset.fasset.model.depreciation.MonthlySolDepreciation;
import io.github.fasset.fasset.repository.MonthlySolDepreciationRepository;
import io.github.fasset.fasset.service.MonthlySolDepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * {@link io.github.fasset.fasset.service.MonthlySolDepreciationService} implementation
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Service("monthlySolDepreciationService")
@Transactional
public class MonthlySolDepreciationServiceImpl implements MonthlySolDepreciationService {


    private final MonthlySolDepreciationRepository monthlySolDepreciationRepository;

    /**
     * <p>Constructor for MonthlySolDepreciationServiceImpl.</p>
     *
     * @param monthlySolDepreciationRepository a {@link io.github.fasset.fasset.repository.MonthlySolDepreciationRepository} object.
     */
    @Autowired
    public MonthlySolDepreciationServiceImpl(@Qualifier("monthlySolDepreciationRepository") MonthlySolDepreciationRepository monthlySolDepreciationRepository) {
        this.monthlySolDepreciationRepository = monthlySolDepreciationRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MonthlySolDepreciation> fetchAllMonthlySolDepreciations() {

        return monthlySolDepreciationRepository.findAll().parallelStream().sorted(Comparator.comparing(MonthlySolDepreciation::getSolId)).collect(ImmutableListCollector.toImmutableList());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Save all the items in the parameter into the MonthlySolDepreciationRepository
     */
    @Override
    public void saveAllMonthlyDepreciationItems(List<? extends MonthlySolDepreciation> items) {

        monthlySolDepreciationRepository.saveAll(items);
    }
}
