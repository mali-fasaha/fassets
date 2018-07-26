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
package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

import java.util.List;

/**
 * Service for data retrieval from database for {@link io.github.fasset.fasset.model.brief.ServiceOutletBrief}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface ServiceOutletBriefService {

    /**
     * <p>fetchAllServiceOutletBriefs.</p>
     *
     * @return {@link java.util.List} of {@link io.github.fasset.fasset.model.brief.ServiceOutletBrief} items from repository
     */
    List<ServiceOutletBrief> fetchAllServiceOutletBriefs();

    /**
     * <p>fetchServiceOutletBriefGivenId.</p>
     *
     * @param id of the serviceOutletBrief
     * @return {@link io.github.fasset.fasset.model.brief.ServiceOutletBrief} of the nomenclature given as parameter
     */
    ServiceOutletBrief fetchServiceOutletBriefGivenId(int id);

    /**
     * Save all ServiceOutletBrief items in the collection
     *
     * @param serviceOutletBriefs entities to be saved into the repository
     */
    void saveAllServiceOutletBriefItems(Iterable<ServiceOutletBrief> serviceOutletBriefs);
}
