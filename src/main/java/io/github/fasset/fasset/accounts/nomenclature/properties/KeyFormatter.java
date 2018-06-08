package io.github.fasset.fasset.accounts.nomenclature.properties;

import io.github.fasset.fasset.accounts.definition.AccountNumberSegment;
import io.github.fasset.fasset.accounts.definition.Posting;
import io.github.fasset.fasset.accounts.definition.TransactionType;

/**
 * Utility methods for formatting the keys before search into the properties object
 */
public class KeyFormatter {

    static String formatKey(String category, String transaction, String element) {
        return cleanString(String.format("%s.%s.%s", category.toLowerCase(), transaction, element));
    }

    static String cleanString(String toClean){

        return toClean.replace(" ", "-").replace("&", "and");
    }

    static String formatKey(String category, TransactionType transactionType, Posting posting) {

        return cleanString(String.format("%s.%s.%s",category, transactionType, posting)); // e.g. computers.acquisition.posting
    }

    static String formatKey(String category, TransactionType transactionType, Posting posting, AccountNumberSegment segment) {

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
