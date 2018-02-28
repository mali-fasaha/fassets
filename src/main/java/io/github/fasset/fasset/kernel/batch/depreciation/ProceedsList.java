package io.github.fasset.fasset.kernel.batch.depreciation;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ProceedsList<E> extends FastList<E> implements List<E> {

    private static final Logger log = LoggerFactory.getLogger(ProceedsList.class);

    /**
     * Appends the specified element to the end of this list.
     * <p>
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(E e) {

        log.debug("ProceedsList : adding item : {}",e);

        return super.add(e);
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {

        log.debug("Polling from ProceedsList with consumer : {}",consumer);

        super.forEach(consumer);
    }
}
