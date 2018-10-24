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
 * Exception thrown during upload of the program's data files
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 1569324982377679466L;

    /**
     * <p>Constructor for StorageException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for StorageException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause   a {@link java.lang.Throwable} object.
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
