/**
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.accounts.id;


/**
 * This interface resolves names for accounts to be posted in the {@code CREDIT} side for any transaction. Individual transaction
 * types are implemented accordingly each in a class of its own. This being because different transactions would require
 * different accounts, acquisition would require a sundry creditors account, in disposals we are going to credit, the
 * FixedAsset's cost account, while in depreciation we are either crediting the Cost account or the cost account's contra
 * account (Accumulated depreciation account)
 */
public interface CreditAccountIDResolver extends DebitAccountIDResolver {

}
