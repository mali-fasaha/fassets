package io.github.fasset.fasset.kernel.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConcurrentListTest {

    private List<Integer> testList;

    private List<Integer> intList0 = ConcurrentList.of(1);
    private List<Integer> intList1 = ConcurrentList.of(1, 12);
    private List<Integer> intList2 = ConcurrentList.of(1, 12, 8);
    private List<Integer> intList3 = ConcurrentList.of(1, 12, 8, 17);
    private List<Integer> intList4 = ConcurrentList.of(1, 12, 8, 17, 23);
    private List<Integer> intList5 = ConcurrentList.of(1, 12, 8, 17, 23, 28);
    private List<Integer> intList6 = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13);
    private List<Integer> intList7 = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13, 16);
    private List<Integer> intList8 = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13, 16, 18);
    private List<Integer> intList9 = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13, 16, 18, 21);
    private List<Integer> intList10 = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13, 16, 18, 21, 14);
    private List<Integer> intList11 = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13, 16, 18, 21, 14, 15);

    @Before
    public void setUp() throws Exception {

        testList = ConcurrentList.newList();
    }

    @Test
    public void of() throws Exception {

        assertEquals(1, intList0.size());
        assertEquals(2, intList1.size());
        assertEquals(3, intList2.size());
        assertEquals(4, intList3.size());
        assertEquals(5, intList4.size());
        assertEquals(6, intList5.size());
        assertEquals(7, intList6.size());
        assertEquals(8, intList7.size());
        assertEquals(9, intList8.size());
        assertEquals(10, intList9.size());
        assertEquals(11, intList10.size());
        assertEquals(12, intList11.size());
    }

    @Test(expected = AdditionToEmptyImmutableListException.class)
    public void empty() throws Exception {

        List<String> testEmptyList = ConcurrentList.empty();

        testEmptyList.add("Lazy_Dev");
    }

    @Test
    public void newList() throws Exception {

        List<Integer> testList = ConcurrentList.newList();

        assertTrue(testList instanceof List);
    }

    @Test
    public void stream() throws Exception {

        final int[] i = {0};

        intList11.stream().forEach(integer -> i[0]++);
        assertEquals(12, i[0]);

        intList10.stream().forEach(integer -> i[0]++);
        assertEquals(23, i[0]);

        intList9.stream().forEach(integer -> i[0]++);
        assertEquals(33, i[0]);

        intList8.stream().forEach(integer -> i[0]++);
        assertEquals(42, i[0]);

        intList7.stream().forEach(integer -> i[0]++);
        assertEquals(50, i[0]);

        intList6.stream().forEach(integer -> i[0]++);
        assertEquals(57, i[0]);

        intList5.stream().forEach(integer -> i[0]++);
        assertEquals(63, i[0]);

        intList4.stream().forEach(integer -> i[0]++);
        assertEquals(68, i[0]);

        intList3.stream().forEach(integer -> i[0]++);
        assertEquals(72, i[0]);

        intList2.stream().forEach(integer -> i[0]++);
        assertEquals(75, i[0]);

        intList1.stream().forEach(integer -> i[0]++);
        assertEquals(77, i[0]);

        intList0.stream().forEach(integer -> i[0]++);
        assertEquals(78, i[0]);

    }

    @Test
    public void parallelStream() throws Exception {

        // unstable

        /*final int[] i = {0};

        intList11.parallelStream().forEach(integer -> i[0]++);
        assertEquals(12, i[0]);

        intList10.parallelStream().forEach(integer -> i[0]++);
        assertEquals(23, i[0]);

        intList9.parallelStream().forEach(integer -> i[0]++);
        assertEquals(33, i[0]);

        intList8.parallelStream().forEach(integer -> i[0]++);
        assertEquals(42, i[0]);

        intList7.parallelStream().forEach(integer -> i[0]++);
        assertEquals(50, i[0]);

        intList6.parallelStream().forEach(integer -> i[0]++);
        assertEquals(57, i[0]);

        intList5.parallelStream().forEach(integer -> i[0]++);
        assertEquals(63, i[0]);

        intList4.parallelStream().forEach(integer -> i[0]++);
        assertEquals(68, i[0]);

        intList3.parallelStream().forEach(integer -> i[0]++);
        assertEquals(72, i[0]);

        intList2.parallelStream().forEach(integer -> i[0]++);
        assertEquals(75, i[0]);

        intList1.parallelStream().forEach(integer -> i[0]++);
        assertEquals(77, i[0]);

        intList0.parallelStream().forEach(integer -> i[0]++);
        assertEquals(78, i[0]);*/

    }

    @Test
    public void forEach() throws Exception {

        final int[] i = {0};

        intList11.forEach(integer -> i[0]++);
        assertEquals(12, i[0]);

        intList10.forEach(integer -> i[0]++);
        assertEquals(23, i[0]);

        intList9.forEach(integer -> i[0]++);
        assertEquals(33, i[0]);

        intList8.forEach(integer -> i[0]++);
        assertEquals(42, i[0]);

        intList7.forEach(integer -> i[0]++);
        assertEquals(50, i[0]);

        intList6.forEach(integer -> i[0]++);
        assertEquals(57, i[0]);

        intList5.forEach(integer -> i[0]++);
        assertEquals(63, i[0]);

        intList4.forEach(integer -> i[0]++);
        assertEquals(68, i[0]);

        intList3.forEach(integer -> i[0]++);
        assertEquals(72, i[0]);

        intList2.forEach(integer -> i[0]++);
        assertEquals(75, i[0]);

        intList1.forEach(integer -> i[0]++);
        assertEquals(77, i[0]);

        intList0.forEach(integer -> i[0]++);
        assertEquals(78, i[0]);

    }

    @Test
    public void removeAll() throws Exception {

        intList11.removeAll(Arrays.asList(23,28,13,17));

        assertEquals(8, intList11.size());
        assertTrue(!intList11.contains(23));
        assertTrue(!intList11.contains(28));
        assertTrue(!intList11.contains(13));
        assertTrue(!intList11.contains(17));
    }

    @Test
    public void addAll() throws Exception {

        intList11.addAll(Arrays.asList(78,27,49,65));
        assertEquals(16, intList11.size());
        assertTrue(intList11.contains(78));
        assertTrue(intList11.contains(27));
        assertTrue(intList11.contains(49));
        assertTrue(intList11.contains(65));
    }

    @Test
    public void contains() throws Exception {
        // see above
    }

    @Test
    public void set() throws Exception {

        Integer original5 = intList9.get(5);

        intList9.set(4, 99);

        assertEquals(4, intList9.indexOf(99));
        assertEquals(5, intList9.indexOf(original5));
    }

    @Test
    public void iterator() throws Exception {

        int i = 0;

        Iterator intRator = intList8.iterator();

        while(intRator.hasNext()){
            i ++;
            intRator.next();
        }

        assertEquals(9, i);

        // control test
        for (Object anIntList8 : intList8) {
            i++;
        }

        assertEquals(18, i);
    }
@Test
    public void listIterator() throws Exception {

        int i = 0;

        ListIterator intRator = intList8.listIterator();

        while(intRator.hasNext()){
            i ++;
            intRator.next();
        }

        assertEquals(9, i);

        // control test
        for (Object anIntList8 : intList8) {
            i++;
        }

        // Todo test other list iterator methods
        assertEquals(18, i);
    }

    @Test
    public void indexOf() throws Exception {

        // see set test
    }

    @Test
    public void size() throws Exception {

        assertEquals(1, intList0.size());
        assertEquals(2, intList1.size());
        assertEquals(3, intList2.size());
        assertEquals(4, intList3.size());
        assertEquals(5, intList4.size());
        assertEquals(6, intList5.size());
        assertEquals(7, intList6.size());
        assertEquals(8, intList7.size());
        assertEquals(9, intList8.size());
        assertEquals(10, intList9.size());
        assertEquals(11, intList10.size());
        assertEquals(12, intList11.size());
    }

    @Test
    public void add() throws Exception {
        // see above
        assertTrue(intList4.add(20));
        //assertTrue(intList4.add(20, 43));
    }

    @Test
    public void sublist() throws Exception {

        List<Integer> newSubList = intList9.subList(3, 6);

        assertEquals(4, newSubList.size());
        assertTrue(!newSubList.contains(8));
        assertTrue(newSubList.contains(17));
        assertTrue(newSubList.contains(23));
        assertTrue(newSubList.contains(28));
        assertTrue(newSubList.contains(13));
        assertTrue(!newSubList.contains(16));

    }

    @Test
    public void sort() throws Exception {

        List<Integer> intList = ConcurrentList.of(1, 12, 8, 17, 23, 28, 13);

        intList.sort(Comparator.comparing(Integer::intValue));

        List<Integer> expected = new LinkedList<>();

        expected.add(1);
        expected.add(8);
        expected.add(12);
        expected.add(13);
        expected.add(17);
        expected.add(23);
        expected.add(28);

        assertEquals(expected, intList);
    }
}