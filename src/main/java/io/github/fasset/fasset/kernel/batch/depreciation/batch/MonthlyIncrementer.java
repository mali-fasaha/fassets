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
package io.github.fasset.fasset.kernel.batch.depreciation.batch;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationJobInstance;
import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationJobInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This object takes a month and generates the appropriate consequent month while recording the month for which depreciation has already occurred
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("MonthlyIncrementer")
public class MonthlyIncrementer {

    private final DepreciationJobInstanceRepository depreciationJobInstanceRepository;

    /**
     * <p>Constructor for MonthlyIncrementer.</p>
     *
     * @param depreciationJobInstanceRepository a {@link io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationJobInstanceRepository} object.
     */
    @Autowired
    public MonthlyIncrementer(@Qualifier("depreciationJobInstanceRepository") DepreciationJobInstanceRepository depreciationJobInstanceRepository) {
        this.depreciationJobInstanceRepository = depreciationJobInstanceRepository;
    }

    /**
     * <p>getJobInstanceSequence.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DepreciationJobInstance> getJobInstanceSequence() {

        return depreciationJobInstanceRepository.findAll().parallelStream().sorted().collect(Collectors.toList());
    }

    /**
     * <p>getMonthSequence.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<YearMonth> getMonthSequence() {

        return getJobInstanceSequence().stream().map(DepreciationJobInstance::getMonth).collect(Collectors.toList());
    }

    /**
     * <p>getLatest.</p>
     *
     * @return a {@link java.time.YearMonth} object.
     */
    public YearMonth getLatest() {

        List<DepreciationJobInstance> jobInstanceList = getJobInstanceSequence();

        int listSize = jobInstanceList.size();

        if (listSize != 0) {
            return null;
        } else {
            return jobInstanceList.get(listSize - 1).getMonth();
        }
    }

    /**
     * <p>getNext.</p>
     *
     * @param month a {@link java.time.YearMonth} object.
     * @return a {@link java.time.YearMonth} object.
     */
    public YearMonth getNext(YearMonth month) {

        /*if(depreciationJobInstanceRepository.findAllByMonthBefore(month)==null){
            //TODO implement month when months are empty
        }*/

        depreciationJobInstanceRepository.save(new DepreciationJobInstance(month));

        return month.plusMonths(1);
    }
}
