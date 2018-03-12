package io.github.fasset.fasset.dto;

import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FixedAssetResponseDtoTest {

    private FixedAsset fixedAsset;
    private LocalDate purchaseDate;

    @Before
    public void setUp() throws Exception {

        purchaseDate = LocalDate.now();

        fixedAsset = new FixedAsset()
                .setSolId("001")
                .setAssetDescription("Desktop")
                .setBarcode("ACB52645")
                .setCategory("Furniture & Fittings")
                .setPurchaseDate(purchaseDate)
                .setPurchaseCost(Money.of(200000.23,"KES"))
                .setNetBookValue(Money.of(90000.05,"KES"));
    }

    @Test
    public void dtoConvertsAccurately() throws Exception {

        FixedAssetResponseDto fixedAssetResponseDto = new FixedAssetResponseDto(fixedAsset);

        assertEquals(200000.23, fixedAssetResponseDto.getPurchaseCost(),0);
        assertEquals(90000.05, fixedAssetResponseDto.getNetBookValue(),0);
        assertEquals("001", fixedAssetResponseDto.getSolId());
        assertEquals("Desktop", fixedAssetResponseDto.getAssetDescription());
        assertEquals("ACB52645", fixedAssetResponseDto.getBarcode());
        assertEquals("Furniture & Fittings", fixedAssetResponseDto.getCategory());
        assertEquals(purchaseDate, fixedAssetResponseDto.getPurchaseDate());
    }
}