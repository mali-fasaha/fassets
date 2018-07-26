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
package io.github.fasset.fasset.kernel.book.keeper.util;

/**
 * Exception thrown when some detail is queried from an account or entry and it does not exist
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class UnEnteredDetailsException extends Exception {

    private static final long serialVersionUID = 7464030628723759039L;

    /**
     * <p>Constructor for UnEnteredDetailsException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public UnEnteredDetailsException(String message) {
        super(message);
    }
}
