package io.github.fasset.fasset.kernel;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component()
public class StringToDoubleConverter implements Converter<String,Double>{

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Nullable
    @Override
    public Double convert(String source) {

        return (Double)Double.parseDouble(source.replace(",",""));
    }
}
