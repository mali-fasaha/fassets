package io.github.fasset.fasset.kernel;

import io.github.fasset.fasset.kernel.util.ConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;

@Component("localDateToYearMonthConverter")
public class LocalDateToYearMonthConverter implements Converter<LocalDate,YearMonth> {

    private static final Logger log = LoggerFactory.getLogger(LocalDateToYearMonthConverter.class);

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Nullable
    @Override
    public YearMonth convert(LocalDate source) {

        YearMonth convertedMonth = null;

        log.debug("Converting {} to YearMonth",source.toString());

        try {

            convertedMonth = YearMonth.from(source);

        } catch (Throwable e) {
            if(source == null) {
                throw new ConverterException("The date provided is null, kindly review the source data again...", e);
            }else {
                throw new ConverterException(String.format("Exception thrown while converting %s to YearMonth", source), e);
            }
        }

        return convertedMonth;
    }
}
