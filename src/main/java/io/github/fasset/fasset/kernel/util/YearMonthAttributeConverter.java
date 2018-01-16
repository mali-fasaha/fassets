package io.github.fasset.fasset.kernel.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class enables the JPA specification to convert a {@link java.time.YearMonth} variable
 * into database-friendly format that is not persisted as a blob through implementation of
 * JPA 2.1's {@link javax.persistence.AttributeConverter<java.time.YearMonth,java.sql.Date>}
 * interface.
 * It is applied automatically to any {@link YearMonth} variable that is being persisted to the
 * database, just before the persistence engines flushes it to the database.
 * Again just before the model is rehydrated with database values, the data is converted back from
 * {@link Date} to {@link YearMonth} object maintaining user accessibility in both the database and
 * the application.
 *
 * @author edwin.njeru
 */
@Converter(autoApply = true)
public class YearMonthAttributeConverter implements AttributeConverter<YearMonth,Date> {

    /**
     * Converts the value stored in the entity attribute into the
     * data representation to be stored in the database.
     *
     * @param attribute the entity attribute value to be converted
     * @return the converted data to be stored in the database column
     */
    @Override
    public Date convertToDatabaseColumn(YearMonth attribute) {
        Date dbDate = null;

        try {
            dbDate = Date.from(
                    attribute.atDay(1)
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant()
            );
        } catch(Throwable e) {
            String message = String.format("Exception thrown while converting %s to java.sql.Date", attribute);
            throw new ConverterException(message, e);
        }
        return dbDate;
    }

    /**
     * Converts the data stored in the database column into the
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct dbData type for the corresponding column
     * for use by the JDBC driver: i.e., persistence providers are
     * not expected to do such type conversion.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted value to be stored in the entity attribute
     */
    @Override
    public YearMonth convertToEntityAttribute(Date dbData) {

        YearMonth retval = null;

        try {
            retval = YearMonth.from(dbData.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        } catch (Throwable e){
            String message = String.format("Exception thrown while converting date: %s to yearMonth",dbData);
            throw new ConverterException(message,e);
        }

        return retval;
    }
}
