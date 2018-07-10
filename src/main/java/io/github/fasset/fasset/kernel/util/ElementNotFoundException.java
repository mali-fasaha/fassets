package io.github.fasset.fasset.kernel.util;

class ElementNotFoundException extends ConcurrentListException {

    ElementNotFoundException(int index) {
        super(String.format("The element inquired was not found at index %s", index));
    }

    ElementNotFoundException(Object element) {
        super(String.format("The element %s was not found in the list", element));
    }
}
