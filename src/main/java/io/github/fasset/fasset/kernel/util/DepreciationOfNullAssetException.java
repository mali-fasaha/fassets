package io.github.fasset.fasset.kernel.util;

/**
 * Exception is thrown when a null asset item is brought for depreciation
 */
public class DepreciationOfNullAssetException extends RuntimeException {

    public DepreciationOfNullAssetException(String message) {
        super(message);
    }
}
