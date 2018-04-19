/*
 *     This file is part of fassets
 *     Copyright (C) 2018 Edwin Njeru
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

import java.util.List;

/**
 * Service for data retrieval from database for {@link ServiceOutletBrief}
 */
public interface ServiceOutletBriefService {

    /**
     * @return {@link List<ServiceOutletBrief>} of service outlets from repository
     */
    List<ServiceOutletBrief> fetchAllServiceOutletBriefs();

    /**
     * @param id of the serviceOutletBrief
     * @return {@link ServiceOutletBrief} of the id given as parameter
     */
    ServiceOutletBrief fetchServiceOutletBriefGivenId(int id);

    /**
     * Save all ServiceOutletBrief items in the collection
     *
     * @param serviceOutletBriefs entities to be saved into the repository
     */
    void saveAllServiceOutletBriefItems(Iterable<ServiceOutletBrief> serviceOutletBriefs);
}
