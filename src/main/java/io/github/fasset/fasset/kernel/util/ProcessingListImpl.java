package io.github.fasset.fasset.kernel.util;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple data-structure that allows additional Fassets-specific functionality like logs
 * and polling of items processed
 *
 * @param <E> Type of item contained by this data structure
 *
 * @author edwin.njeru
 */
public class ProcessingListImpl<E> extends FastList<E> implements ProcessingList<E>{

    private static final Logger log = LoggerFactory.getLogger(ProcessingListImpl.class);

    private int itemsAdded = 0;
    private int itemsProcessed = 0;
    private int remainingItems =0;

    /**
     * Appends the specified element to the end of this list.
     * <p>
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(E e) {

        itemsAdded++;

        log.debug("ProceedsList : adding item : {}",e);

        return super.add(e);
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {

        log.debug("Polling from ProceedsList with consumer : {}",consumer);

        remainingItems = super.size();

        super.forEach(proceed -> {itemsProcessed++; --remainingItems; consumer.accept(proceed);});
    }

    @Override
    public int getItemsAdded() {
        return itemsAdded;
    }

    @Override
    public int getItemsProcessed() {
        return itemsProcessed;
    }

    @Override
    public int getRemainingItems() {
        return remainingItems;
    }
}
