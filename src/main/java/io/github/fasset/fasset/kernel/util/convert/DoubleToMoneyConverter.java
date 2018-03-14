package io.github.fasset.fasset.kernel.util.convert;

import io.github.fasset.fasset.config.MoneyProperties;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component("doubleToMoneyConverter")
public class DoubleToMoneyConverter implements Converter<Double,Money>{


    private final MoneyProperties moneyProperties;

    @Autowired
    public DoubleToMoneyConverter(MoneyProperties moneyProperties) {
        this.moneyProperties = moneyProperties;
    }

    @Nullable
    @Override
    public Money convert(Double aDouble) {

        return Money.of(aDouble,moneyProperties.getDefaultCurrency());
    }
}
