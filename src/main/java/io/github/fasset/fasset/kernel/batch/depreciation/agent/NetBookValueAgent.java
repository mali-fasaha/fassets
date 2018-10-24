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
package io.github.fasset.fasset.kernel.batch.depreciation.agent;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.NetBookValue;

import java.time.YearMonth;

/**
 * Another Agent in the depreciation chain which calculates and generates the appropriate NetBookValue for a FixedAsset at a given time period in months
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public interface NetBookValueAgent extends Agent<NetBookValue> {

    /**
     * {@inheritDoc}
     * <p>
     * Upon invocation the implementation will return the netBoookValue item for the relevant month in which depreciation has occured
     */
    NetBookValue invoke(FixedAsset asset, YearMonth month, DepreciationProceeds proceeds);
}
