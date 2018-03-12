package io.github.fasset.fasset.kernel.batch;

import io.github.fasset.fasset.kernel.util.ConverterException;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component("moneyToDoubleConverter")
public class MoneyToDoubleConverter implements Converter<Money, Double>{

    private static final Logger log = LoggerFactory.getLogger(MoneyToDoubleConverter.class);

    @Nullable
    @Override
    public Double convert(Money money) {

        log.debug("Converting monetaryAmount : {} to double",money);

        Double doubleAmount = null;

        try {
            doubleAmount = money.getNumber().doubleValue();
        } catch (Throwable e) {
            String message = String.format("Exception encountered while converting %s to double type",money);

            throw new ConverterException(message,e);
        }

        return doubleAmount;
    }
}
