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
 * Exception thrown when uploaded file cannot be obtained by the server
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
public class StorageFileNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1832798591585236987L;

    /**
     * <p>Constructor for StorageFileNotFoundException.</p>
     */
    public StorageFileNotFoundException() {
        super();
    }

    /**
     * <p>Constructor for StorageFileNotFoundException.</p>
     *
     * @param message            a {@link java.lang.String} object.
     * @param cause              a {@link java.lang.Throwable} object.
     * @param enableSuppression  a boolean.
     * @param writableStackTrace a boolean.
     */
    public StorageFileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <p>Constructor for StorageFileNotFoundException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause   a {@link java.lang.Throwable} object.
     */
    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Constructor for StorageFileNotFoundException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for StorageFileNotFoundException.</p>
     *
     * @param cause a {@link java.lang.Throwable} object.
     */
    public StorageFileNotFoundException(Throwable cause) {
        super(cause);
    }


}
