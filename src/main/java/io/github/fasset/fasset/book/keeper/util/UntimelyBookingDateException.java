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

package io.github.fasset.fasset.book.keeper.util;

/**
 * This exception is thrown when the {@link io.github.fasset.fasset.book.keeper.unit.time.TimePoint} bookingDate
 * of the {@link io.github.fasset.fasset.book.keeper.Entry} is sooner than the
 * {@link io.github.fasset.fasset.book.keeper.unit.time.TimePoint} openingDate attribute of the
 * {@code Account}
 *
 * @author edwin.njeru
 */
public class UntimelyBookingDateException extends Exception {

    private static final long serialVersionUID = -8350399456082375239L;

    /**
     * Constructs a new throwable with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * <p>
     * <p>The {@link #fillInStackTrace()} method is called to initialize
     * the stack trace data in the newly created throwable.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UntimelyBookingDateException(String message) {
        super(message);
    }
}
