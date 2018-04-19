package io.github.fasset.fasset.kernel.util;

/**
 * Exception thrown when null year string value is passed to depreciation processors
 */
public class NullYearException extends RuntimeException {

    public NullYearException(String message) {
        super(message);
    }
}
