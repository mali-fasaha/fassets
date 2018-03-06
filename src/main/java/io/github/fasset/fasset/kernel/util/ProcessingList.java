package io.github.fasset.fasset.kernel.util;

import java.util.List;

public interface ProcessingList<E> extends List<E> {

    /**
     *
     * @return # of items added in the list
     */
    int getItemsAdded();

    /**
     *
     * @return # of items processed
     */
    int getItemsProcessed();

    /**
     *
     * @return # of items remaining to be processed
     */
    int getRemainingItems();
}
