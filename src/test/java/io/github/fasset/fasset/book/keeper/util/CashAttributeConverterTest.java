package io.github.fasset.fasset.book.keeper.util;

import io.github.ghacupha.cash.Cash;
import io.github.ghacupha.cash.ReadableHardCash;
import org.junit.Before;
import org.junit.Test;

import static io.github.ghacupha.cash.HardCash.shilling;
import static org.junit.Assert.*;

public class CashAttributeConverterTest {

    private CashAttributeConverter converter;

    @Before
    public void setUp() throws Exception {

        converter = new CashAttributeConverter();
    }

    @Test
    public void convertToDatabaseColumn() {

        assertEquals("KES 15623.45", converter.convertToDatabaseColumn(shilling(15623.45)));
    }

    @Test
    public void convertToEntityAttribute() {

        Cash readableCash = ReadableHardCash.parse("KES 15623.45");

        assertEquals(readableCash, converter.convertToEntityAttribute("KES 15623.45"));
    }
}