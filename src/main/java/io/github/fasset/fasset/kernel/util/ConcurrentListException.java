package io.github.fasset.fasset.kernel.util;

class ConcurrentListException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    ConcurrentListException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified location (index) where the element is being added
     * and the element itself
     * @param index where we are attempting to add an element
     * @param element The element being added to the list
     * @param <T> Type of element being added to the list
     */
    <T> ConcurrentListException(int index, T element) {
        super(String.format("Exception encountered while adding %s to index %s", element, index));
    }
}
