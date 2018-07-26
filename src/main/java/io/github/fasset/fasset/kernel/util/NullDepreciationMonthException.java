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
package io.github.fasset.fasset.kernel.util;

/**
 * Exception is thrown when the month of depreciation for an asset evaluates to null
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class NullDepreciationMonthException extends RuntimeException {

    /**
     * <p>Constructor for NullDepreciationMonthException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public NullDepreciationMonthException(String message) {
        super(message);
    }
}
