package io.github.fasset.fasset.kernel.util;

class ConcurrentListException extends RuntimeException {

    <T> ConcurrentListException(int index, T element) {
        super(String.format("Exception encountered while adding %s to index %s", element, index));
    }
}
