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
package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.CategoryConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This Repository extends the Spring JPA Template and has runtime-implentation depending on the nature of the {@code Entity}
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Repository("categoryConfigurationRepository")
public interface CategoryConfigurationRepository extends JpaRepository<CategoryConfiguration, Integer> {

    /**
     * <p>findFirstByDesignation.</p>
     *
     * @param designation a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.CategoryConfiguration} object.
     */
    CategoryConfiguration findFirstByDesignation(String designation);

}
