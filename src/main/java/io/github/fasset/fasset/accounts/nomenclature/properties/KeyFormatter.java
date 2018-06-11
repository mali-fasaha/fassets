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
package io.github.fasset.fasset.accounts.nomenclature.properties;

import io.github.fasset.fasset.accounts.definition.AccountNumberSegment;
import io.github.fasset.fasset.accounts.definition.Posting;
import io.github.fasset.fasset.accounts.definition.TransactionType;

/**
 * Utility methods for formatting the keys before search into the properties object
 */
public class KeyFormatter {

    private static String formatKey(String category, String transaction, String element) {
        return cleanString(String.format("%s.%s.%s", category.toLowerCase(), transaction, element));
    }

    private static String cleanString(String toClean){

        return toClean.replace(" ", "-").replace("&", "and");
    }

    public static String formatKey(String category, TransactionType transactionType, Posting posting) {

        return cleanString(String.format("%s.%s.%s",category.toLowerCase(), transactionType, posting)); // e.g. computers.acquisition.posting
    }

    public static String formatKey(String category, TransactionType transactionType, Posting posting, AccountNumberSegment segment) {

        String formattedCategory = cleanString(category.toLowerCase());

        return cleanString(String.format("%s.%S.%s.", formattedCategory, transactionType.toString(), posting.toString()).toLowerCase()) + segment;
    }

    static String formatKey(String propertyKey) {
        return formatKey(propertyKey, "acquisition", "gl.code");
    }

    static String formatKey(String propertyKey, String element) {
        return formatKey(propertyKey, "acquisition", element);
    }
}
