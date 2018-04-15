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

package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository("monthlyAssetDepreciationRepository")
public interface MonthlyAssetDepreciationRepository extends JpaRepository<MonthlyAssetDepreciation,Integer>{

    /**
     * Returns an asset that corresponds the assetId and year given
     * @param assetId
     * @param year
     * @return
     */
    MonthlyAssetDepreciation findFirstByAssetIdAndYearEquals(int assetId, int year);


}
