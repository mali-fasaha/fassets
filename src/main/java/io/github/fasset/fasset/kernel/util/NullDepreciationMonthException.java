package io.github.fasset.fasset.kernel.util;

/**
 * Exception is thrown when the month of depreciation for an asset evaluates to null
 */
public class NullDepreciationMonthException extends RuntimeException {

    public NullDepreciationMonthException(String message) {
        super(message);
    }
}
