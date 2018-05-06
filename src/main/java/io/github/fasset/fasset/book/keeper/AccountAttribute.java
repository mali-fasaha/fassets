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

package io.github.fasset.fasset.book.keeper;

/**
 * This enum is used to add additional features to an account which cannot be added
 * through the constructor
 */
public enum AccountAttribute {
    /**
     * The Account that maintains the status of balance for this account
     */
    Contra_Account,

    /**
     * The type classification of the account on the balance sheet, which could be either
     * asset, liability or capital or equity
     */
    Account_Type,

    /**
     * The subtype where one would wish to classify this account
     */
    Account_SubType,

    /**
     * An extra classification layer for the account should one be needed
     */
    Account_Scheme,

    /**
     * General ledger identification of the account should such a system exist with which
     * one would like to integrate this system
     */
    General_Ledger,

    /**
     * Interest which is associated with this account if the same has such considerations according
     * to the application business domain
     */
    Interest_Rate,

    /**
     * Typical narration associated with this account
     */
    Remarks
}
