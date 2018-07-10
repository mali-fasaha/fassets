package io.github.fasset.fasset.kernel.util;

class IndexBeyondBoundsException extends ArrayIndexOutOfBoundsException {

    IndexBeyondBoundsException(int index, int listIndex, Object element) {

        super(String.format("Could not add Element %s at index: %s, since the structure is only filled up to index : %s", element, index, listIndex));
    }
}
