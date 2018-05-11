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

package io.github.fasset.fasset.managers;

import io.github.fasset.fasset.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;

import java.util.Currency;
import java.util.List;

/**
 * This object will be used to abstract acquisition transactions for an asset, using the Asset account and
 * the Asset acquisition account. This simplifies the story, of how the asset was acquired and, and how much
 * was paid to a vendor, since such, is subject to withholding tax obligations and sometimes contract-related
 * provisions, as such we are only interest with the financial representation of assets in this application.
 * As Accounting goes, each asset is represented by an entry
 *
 * @author edwin.njeru
 */
public interface Acquirer {

    List<AccountingEntry> recognizeAssets(List<FixedAsset> fixedAsset);

}
