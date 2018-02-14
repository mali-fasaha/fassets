package io.github.fasset.fasset.kernel.util;

/**
 * Exception thrown during depreciation process when an item is sent through the mediator(DepreciationUpdateDispatcher)
 *
 * @author edwin.njeru
 */
public class DepreciationUpdatesException extends RuntimeException {

    public DepreciationUpdatesException(String errorMessage, Throwable e) {
    }
}
