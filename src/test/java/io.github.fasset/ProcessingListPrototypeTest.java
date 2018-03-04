package io.github.fasset;

import io.github.fasset.fasset.kernel.util.ProcessingList;
import io.github.fasset.fasset.kernel.util.ProcessingListImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class ProcessingListPrototypeTest {

    private ProcessingList<Integer> processingList;

    @Before
    public void setUp() throws Exception {
        processingList = new ProcessingListImpl<>();
    }

    @Test
    public void processingListWorks() throws Exception{

        processingList.add(21);
        processingList.add(43);
        processingList.add(32);
        processingList.add(25);
        processingList.add(51);

        System.out.println(processingList.getItemsAdded());
        System.out.println(processingList.getItemsProcessed());
        System.out.println(processingList.getRemainingItems());

        Assert.assertEquals(5, processingList.getItemsAdded());
        Assert.assertEquals(0, processingList.getItemsProcessed());
        Assert.assertEquals(0, processingList.getRemainingItems());

        processingList.forEach(System.out::println);

        Assert.assertEquals(5, processingList.getItemsAdded());
        Assert.assertEquals(5, processingList.getItemsProcessed());
        Assert.assertEquals(0, processingList.getRemainingItems());

    }
}
