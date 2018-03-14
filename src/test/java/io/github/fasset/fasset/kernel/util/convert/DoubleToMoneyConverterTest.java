package io.github.fasset.fasset.kernel.util.convert;

import io.github.fasset.fasset.config.MoneyProperties;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleToMoneyConverterTest {

    private DoubleToMoneyConverter doubleToMoneyConverter;

    @Before
    public void setUp() throws Exception {
        doubleToMoneyConverter = new DoubleToMoneyConverter(new MoneyProperties());
    }

    @Test
    public void convertMethodWorks() throws Exception {

        assertEquals(Money.of(100.00,"KES"),doubleToMoneyConverter.convert(100.00));
    }
}