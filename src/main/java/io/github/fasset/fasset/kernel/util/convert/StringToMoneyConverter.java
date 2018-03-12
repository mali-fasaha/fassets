package io.github.fasset.fasset.kernel.util.convert;

import io.github.fasset.fasset.config.MoneyProperties;
import io.github.fasset.fasset.kernel.util.ConverterException;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component("stringToMoneyConverter")
public class StringToMoneyConverter implements Converter<String,Money> {

    private static final Logger log = LoggerFactory.getLogger(StringToMoneyConverter.class);

    private final MoneyProperties monetaryProperties;

    private final StringToDoubleConverter stringToDoubleConverter;


    @Autowired
    public StringToMoneyConverter(MoneyProperties monetaryProperties, @Qualifier("stringToDoubleConverter") StringToDoubleConverter stringToDoubleConverter) {
        this.monetaryProperties = monetaryProperties;
        this.stringToDoubleConverter = stringToDoubleConverter;
    }

    @Nullable
    @Override
    public Money convert(@Nullable final String stringMoney) {

        log.debug("Converting the amount : {} from string to {} money amount",stringMoney,monetaryProperties.getDefaultCurrency());

        Money converted;

        try {
            double moneyAmount = 0;
            if (stringMoney != null) {
                moneyAmount = stringToDoubleConverter.convert(stringMoney);
            } else {
                log.error("The string money amount passed is null");
            }
            converted =  Money.of(moneyAmount,monetaryProperties.getDefaultCurrency());
        } catch (Throwable e) {
            String message = String.format("Exception encountered while converting amount %stringMoney to Money amount",stringMoney);
            throw new ConverterException(message,e);
        }

        return converted;

    }
}
