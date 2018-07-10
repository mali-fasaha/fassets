package io.github.fasset.fasset.kernel.util;

public class AdditionToEmptyImmutableListException extends ConcurrentListException {

    <T> AdditionToEmptyImmutableListException(int index, T element) {
        super(index++, element);
    }
}
