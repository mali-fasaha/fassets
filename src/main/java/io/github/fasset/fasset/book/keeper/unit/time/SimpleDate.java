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

package io.github.fasset.fasset.book.keeper.unit.time;

import java.time.LocalDate;

/**
 * A representation of a point in time to the accuracy of a day
 *
 * @author edwin.njeru
 */
public class SimpleDate implements TimePoint {

    static final SimpleDate PAST = new SimpleDate(LocalDate.MIN);
    static final SimpleDate FUTURE = new SimpleDate(LocalDate.MAX);

    private LocalDate base;

    private SimpleDate(LocalDate arg) {
        initialize(arg);
    }

    public SimpleDate(int year, int month, int day) {
        initialize(LocalDate.of(year, month, day));
    }

    public SimpleDate() {
        initialize(LocalDate.now());
    }

    public static TimePoint newMoment(int year, int month, int day) {
        return new SimpleDate(year, month, day);
    }

    public static TimePoint now() {

        return new SimpleDate();
    }

    public static TimePoint on(int year, int month, int dayOfMonth) {

        return new SimpleDate(year, month, dayOfMonth);
    }

    private void initialize(LocalDate arg) {
        this.base = arg;
    }

    @Override
    public boolean after(TimePoint arg) {
        return this.base.isAfter(getDay(arg));
    }

    @Override
    public boolean before(TimePoint arg) {
        return this.base.isBefore(getDay(arg));
    }

    @Override
    public SimpleDate addDays(int arg) {
        return new SimpleDate(this.base.plusDays(arg));
    }

    @Override
    public SimpleDate minusDays(int arg) {
        return new SimpleDate(this.base.minusDays(arg));
    }

    @Override
    public int compareTo(TimePoint arg) {
        SimpleDate other = (SimpleDate) arg;
        return this.base.compareTo(other.base);
    }

    @Override
    public boolean equals(Object arg) {
        if (!(arg instanceof SimpleDate)) {
            return false;
        }
        SimpleDate other = (SimpleDate) arg;
        return (base.equals(other.base));
    }

    @Override
    public int hashCode() {
        return base != null ? base.hashCode() : 0;
    }

    @Override
    public String toString() {
        return base.toString();
    }

    private LocalDate getDay(TimePoint arg) {
        SimpleDate simpleDate = (SimpleDate) arg;
        return simpleDate.base;
    }
}
