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

package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;

import java.time.YearMonth;

public interface NetBookValueAgent extends Agent<NetBookValue> {

    /**
     * Upon invocation the implementation will return the netBoookValue item for the relevant month
     * in which depreciation has occured
     *
     * @param asset FixedAsset item whose Net Book Value we are tracking
     * @param month YearMonth in which depreciation has occured
     * @return The relevant NetBookValue item
     */
    NetBookValue invoke(FixedAsset asset, YearMonth month, DepreciationProceeds proceeds);
}
