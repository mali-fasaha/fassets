package io.github.fasset.fasset.kernel.util;

import com.google.common.collect.ForwardingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This is a list backed by a concurrent hashmap making it effectively concurrent. The list is also substantially
 * logged with 'tracer' methods to effectively log, trace and debug complex operations.
 * <br> If the list is supposed to remain empty, invoking the list through the {@link ConcurrentList#empty()} method
 * ensures the {@link ConcurrentList#add(Object)} and the {@link ConcurrentList#add(int, Object)} methods do not
 * throw an excetion when called
 *
 * @param <T> Type of elements to be stored in  the list
 */
public class ConcurrentList<T> extends ForwardingList<T> implements List<T> {

    private static final Logger log = LoggerFactory.getLogger(ConcurrentList.class);

    private final Map<Integer, T> mapList = new ConcurrentHashMap<>();

    private int index;

    // means the list is supposed to remain empty
    private static boolean shouldRemainEmpty = false;

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

        shouldRemainEmpty = true;

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
        return new CopyOnWriteArrayList<>();
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

        if(shouldRemainEmpty){
            return false;
        }

        return collection.stream().allMatch(this::remove);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {

        if(shouldRemainEmpty){
            throw new AdditionToEmptyImmutableListException(index, collection.stream().findFirst().get());
        }

        return collection.stream().allMatch(this::add);
    }

    @Override
    public boolean contains(Object object) {

        if(shouldRemainEmpty){
            return false;
        }

        T val = (T) object;

        log.trace("Checking if the list contains element : {}", val);

        return mapList.containsValue(val);
    }

    @Override
    public T set(int index, T element){

        if(shouldRemainEmpty){
            throw new AdditionToEmptyImmutableListException(index, element);
        }

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

        if (shouldRemainEmpty){
            throw new ElementNotFoundException(element);
        }

        log.trace("Checking the index of the element {}", element);

        int index = 0;

        if (!mapList.containsValue(element)) {
            log.trace("The list does not contain the element {}", element);
            throw new ElementNotFoundException(element);
        }

        for (int i = 0; i < mapList.size(); i++) {

            if (mapList.get(i) == element) {
                index = i;
                log.trace("Element {} found at index {}", element, index);
            }
        }

        log.trace("Element {} found at index {}", element, index);

        return index;
    }

    @Override
    public int size() {

        log.trace("Returning size of the concurrent list {}", mapList.size());

        return mapList.size();
    }

    @Override
    public boolean isEmpty() {

        return mapList.isEmpty();
    }

    @Override
    public void add(int index, T element) {

        if (shouldRemainEmpty){
            throw new AdditionToEmptyImmutableListException(index, element);
        }

        log.trace("Adding the element {} to index position {}", element, index);

        this.mapList.put(index, element);
    }

    @Override
    public boolean remove(Object object) {

        if (shouldRemainEmpty){
            throw new ElementNotFoundException((T)object);
        }

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

        if (shouldRemainEmpty){
            throw new ElementNotFoundException(index);
        }

        T removable = mapList.remove(index);

        log.trace("Element {} has been removed from index {}", removable, index);

        return removable;
    }

    @Override
    public T get(int index) {

        if (shouldRemainEmpty){
            throw new ElementNotFoundException(index);
        }

        T atIndex = mapList.get(index);

        log.trace("The element {} has been fetched at index {}", atIndex, index);

        return atIndex;
    }

    @Override
    public boolean add(T element) {

        if(shouldRemainEmpty){
            throw new AdditionToEmptyImmutableListException(index, element);
        }

        try {
            mapList.put(index++, element);
        } catch (RuntimeException e) {
            throw new ConcurrentListException(index, element);
        }

        log.trace("The element {} has been added at index {}", element, index);

        return mapList.size() >= index;
    }

    /**
     * Removes all of the elements of this collection that satisfy the given
     * predicate.  Errors or runtime exceptions thrown during iteration or by
     * the predicate are relayed to the caller.
     *
     * @param filter a predicate which returns {@code true} for elements to be
     *               removed
     * @return {@code true} if any elements were removed
     * @throws NullPointerException          if the specified filter is null
     * @throws UnsupportedOperationException if elements cannot be removed
     *                                       from this collection.  Implementations may throw this exception if a
     *                                       matching element cannot be removed or if, in general, removal is not
     *                                       supported.
     * @implSpec The default implementation traverses all elements of the collection using
     * its {@link #iterator}.  Each matching element is removed using
     * {@link Iterator#remove()}.  If the collection's iterator does not
     * support removal then an {@code UnsupportedOperationException} will be
     * thrown on the first matching element.
     * @since 1.8
     */
    @Override
    public boolean removeIf(Predicate<? super T> filter) {

        return this.mapList.values().removeIf(filter);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> elements) {

        if (index > this.index) {
            throw new IndexBeyondBoundsException(index, this.index, elements.stream().findFirst().get());
        }

        log.trace("Adding {} elements from index {}", elements.size(), index);

        return elements.stream().allMatch(element -> this.add0(index, element));
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {

        final List<T> subList = ConcurrentList.newList();

        int sublistSize = toIndex - fromIndex + 1;

        for (int i = 0; i < sublistSize; i++) {

            subList.add(mapList.get(fromIndex++));
        }

        return subList;
    }

    @Override
    public int hashCode() {

        final int[] hashcode = {0};

        mapList.values().forEach(i -> hashcode[0] =+ i.hashCode() * 31);

        return hashcode[0];
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof List)){
            return false;
        }

        List<T> equiv;

        try {
            equiv = (List<T>) object;
        } catch (Exception e) {
            return false;
        }

        return equiv.containsAll(this.mapList.values());
    }

    @Override
    public boolean containsAll(Collection<?> collection) {

        return collection.stream().allMatch(this::contains);
    }

    @Override
    public void clear() {

        if(shouldRemainEmpty) {

            return;
        }

        this.index = 0;

        this.mapList.clear();
    }

    @Override
    public ListIterator<T> listIterator() {

        return new CopyOnWriteArrayList<T>(this.mapList.values()).listIterator();
    }

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.
     * <p>
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     * <p>
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     * <p>
     * <p>This list must be modifiable, but need not be resizable.
     *
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @throws ClassCastException            if the list contains elements that are not
     *                                       <i>mutually comparable</i> using the specified comparator
     * @throws UnsupportedOperationException if the list's list-iterator does
     *                                       not support the {@code set} operation
     * @throws IllegalArgumentException      (<a href="Collection.html#optional-restrictions">optional</a>)
     *                                       if the comparator is found to violate the {@link Comparator}
     *                                       contract
     * @implSpec The default implementation obtains an array containing all elements in
     * this list, sorts the array, and iterates over this list resetting each
     * element from the corresponding position in the array. (This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.)
     * @implNote This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is
     * partially sorted, while offering the performance of a traditional
     * mergesort when the input array is randomly ordered.  If the input array
     * is nearly sorted, the implementation requires approximately n
     * comparisons.  Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     * <p>
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     * <p>
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     * @since 1.8
     */
    @Override
    public void sort(Comparator<? super T> c) {

        List<T> originalValues = new CopyOnWriteArrayList<>(this.mapList.values());

        originalValues.sort(c);

        this.clear();

        this.addAll(originalValues);
    }

    @Override
    public Object[] toArray() {

        return this.mapList.values().toArray();
    }

    private boolean add0(int index, T element) {
        try {
            add(index, element);
        } catch (Exception e){
            return false;
        }

        return true;
    }
}
