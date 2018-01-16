package io.github.fasset.fasset.kernel;

import java.time.Instant;
import java.time.YearMonth;
import java.util.Date;

import io.github.fasset.fasset.kernel.util.ConverterException;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Takes {@link Date} converting it to {@link YearMonth}
 *
 * @author edwin.njeru
 */
@Component("dateToYearMonthConverter")
public class DateToYearMonthConverter implements Converter<Date, YearMonth> {

    private final static Logger log = getLogger(DateToYearMonthConverter.class);

    @Autowired
    @Qualifier("dateToLocalDateConverter")
    private DateToLocalDateConverter dateToLocalDateConverter;

    public DateToYearMonthConverter(DateToLocalDateConverter dateToLocalDateConverter) {
        this.dateToLocalDateConverter = dateToLocalDateConverter;
    }


    public DateToLocalDateConverter getDateToLocalDateConverter() {
        return dateToLocalDateConverter;
    }

    public DateToYearMonthConverter setDateToLocalDateConverter(DateToLocalDateConverter dateToLocalDateConverter) {
        this.dateToLocalDateConverter = dateToLocalDateConverter;
        return this;
    }

    public DateToYearMonthConverter() {
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public YearMonth convert(Date source) {

        YearMonth convertedMonth = null;

        log.debug("Converting {} to YearMonth",source.toString());

        Date convertFrom = nullDateReassignment(source);

        try {

            convertedMonth = YearMonth.from(dateToLocalDateConverter.convert(convertFrom));

        } catch (Throwable e) {
            if(dateToLocalDateConverter == null) {
                throw new ConverterException("The dateToLocalDateConverter is null",e);
            }else if(source == null) {
                throw new ConverterException("The date provided is null, kindly review the source data again...",e);
            } else if(convertFrom == null){
                throw new ConverterException("The month we are converting evaluates to null",e);
            }else {
                throw new ConverterException(String.format("Exception thrown while converting %s to YearMonth", source), e);
            }
        }

        return convertedMonth;
    }

    private Date nullDateReassignment(Date source) {

        if(source == null){

            Date reassignedDate = Date.from(Instant.now());

            log.error("The source data is null.. reassigning to current time : {}",reassignedDate);

            return reassignedDate;
        }

        return source;
    }
}
