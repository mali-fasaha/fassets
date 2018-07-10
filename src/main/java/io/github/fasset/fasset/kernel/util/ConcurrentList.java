package io.github.fasset.fasset.kernel.util;

import com.google.common.collect.ForwardingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * This is a list backed by a concurrent hashmap making it effectively concurrent. The list is also substantially
 * logged with 'tracer' methods to effectively log, trace and debug complex operations
 *
 * @param <T> Type of elements to be stored in  the list
 */
public class ConcurrentList<T> extends ForwardingList<T> implements List<T> {

    private static final Logger log = LoggerFactory.getLogger(ConcurrentList.class);

    private final Map<Integer, T> mapList = new ConcurrentHashMap<>();

    private int index;

    public ConcurrentList() {

        index = 0;

        log.trace("New list initialised {}", this);
    }

    @SafeVarargs
    public static<T> List<T> of(T... elements){

        final List<T> newList = new ConcurrentList<>();

        newList.addAll(Arrays.asList(elements));

        return newList;
    }

    public static<T> List<T> empty(){

        return new ConcurrentList<>();
    }

    public static<T> List<T> of(T t1){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4, T t5){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);
        newList.add(t5);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);
        newList.add(t5);
        newList.add(t6);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6, T t7){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);
        newList.add(t5);
        newList.add(t6);
        newList.add(t7);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6, T t7, T t8){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);
        newList.add(t5);
        newList.add(t6);
        newList.add(t7);
        newList.add(t8);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);
        newList.add(t5);
        newList.add(t6);
        newList.add(t7);
        newList.add(t8);
        newList.add(t9);

        return newList;
    }

    public static<T> List<T> of(T t1, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9, T t10){

        final List<T> newList = new ConcurrentList<>();

        newList.add(t1);
        newList.add(t2);
        newList.add(t3);
        newList.add(t4);
        newList.add(t5);
        newList.add(t6);
        newList.add(t7);
        newList.add(t8);
        newList.add(t9);
        newList.add(t10);

        return newList;
    }

    public static<T> List<T> newList(){

        return new ConcurrentList<>();
    }

    @Override
    protected List<T> delegate() {
        return this;
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     * <p>
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a sequential {@code Stream} over the elements in this collection
     * @implSpec The default implementation creates a sequential {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    @Override
    public Stream<T> stream() {

        return this.mapList.values().stream();
    }

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     * <p>
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a possibly parallel {@code Stream} over the elements in this
     * collection
     * @implSpec The default implementation creates a parallel {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    @Override
    public Stream<T> parallelStream() {

        return this.mapList.values().parallelStream();
    }

    /**
     * Creates a {@link Spliterator} over the elements in this list.
     * <p>
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Implementations should document the
     * reporting of additional characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @implSpec The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the list's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the list's iterator.
     * @implNote The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     * @since 1.8
     */
    @Override
    public Spliterator<T> spliterator() {

        return this.mapList.values().spliterator();
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super T> action) {

        this.mapList.values().forEach(action);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {

        return collection.stream().allMatch(this::remove);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {

        return collection.stream().allMatch(this::add);
    }

    @Override
    public boolean contains(Object object) {

        T val = (T) object;

        log.trace("Checking if the list contains element : {}", val);

        return mapList.containsValue(val);
    }

    @Override
    public T set(int index, T element){

        log.trace("Adding element {} @ index {}", element, index);

        this.mapList.put(index, element);

        return element;
    }

    @Override
    public Iterator<T> iterator() {

        return stream().iterator();
    }

    /**
     * Returns zero id the element is missing in the list
     *
     * @param element for which we inquire from the list
     * @return index of the element
     */
    @Override
    public int indexOf(Object element) {

        log.trace("Checking the index of the element {}", element);

        int index = 0;

        if (!mapList.containsValue(element)) {
            log.trace("The list does not contain the element {}", element);
            return 0;
        }

        for (int i = 0; i < mapList.size(); i++) {

            if (mapList.get(i) == element) {
                index = i;
                log.trace("Element {} found at index {}", element, index);
            }
        }

        log.trace("");

        return index;
    }

    @Override
    public int size() {

        log.trace("Returning size of the concurrent list {}", mapList.size());

        return mapList.size();
    }

    @Override
    public void add(int index, T element) {

        log.trace("Adding the element {} to index position {}", element, index);

        this.mapList.put(index, element);
    }

    @Override
    public boolean remove(Object object) {

        if(!mapList.containsValue((T)object)){
            return false;
        }

        Integer ok = mapList.keySet()
            .stream()
            .filter(k -> {
                return mapList.get(k) == (T)object;
            })
            .findFirst().orElse(0);

        mapList.remove(ok);

        return true;
    }

    @Override
    public T remove(int index) {

        T removable = mapList.remove(index);

        log.trace("Element {} has been removed from index {}", removable, index);

        return removable;
    }

    @Override
    public T get(int index) {

        T atIndex = mapList.get(index);

        log.trace("The element {} has been fetched at index {}", atIndex, index);

        return atIndex;
    }

    @Override
    public boolean add(T element) {

        try {
            mapList.put(index++, element);
        } catch (RuntimeException e) {
            throw new ConcurrentListException(index, element);
        }

        log.trace("The element {} has been added at index {}", element, index);

        return mapList.size() >= index;
    }
}
