package io.github.fasset.fasset.model.nil;

import io.github.fasset.fasset.model.FixedAsset;
import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NilFixedAsset extends FixedAsset {

    public NilFixedAsset() {
        super();
    }

    @Override
    public String getSolId() {
        return "998";
    }

    @Override
    public String getBarcode() {
        return "ABC000####";
    }

    @Override
    public String getAssetDescription() {
        return "Nil Asset Item. Please add details";
    }

    @Override
    public LocalDate getPurchaseDate() {
        return LocalDate.now();
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public Money getPurchaseCost() {
        return Money.of(0.00,"KES");
    }

    @Override
    public Money getNetBookValue() {
        return Money.of(0.00,"KES");
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getModifiedAt() {
        return LocalDateTime.now();
    }

    @Override
    public String getCreatedBy() {
        return "developer";
    }

    @Override
    public String getLastModifiedBy() {
        return "developer";
    }
}
