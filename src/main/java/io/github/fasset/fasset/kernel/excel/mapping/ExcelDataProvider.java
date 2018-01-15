package io.github.fasset.fasset.kernel.excel.mapping;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * This is a delegate for generating a {@link List} of {@code T}
 * Given a FILE_PATH and a class it can generate a list of the objects of the class when
 * class and the excel file resonate with each other
 *
 * @param <T>
 */
@Component("excelDataProvider")
public class ExcelDataProvider<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDataProvider.class);

    private ExcelMapper<T> excelMapper;


    @Autowired
    public ExcelDataProvider(@Qualifier("excelMapper") ExcelMapper<T> excelMapper) {
        this.excelMapper = excelMapper;
    }

    /**
     * Generates a {@link List} of the given type when needed from an implementation using only
     * the known path which is provided at runtime
     *
     * @param filePath where the excel file is temporarily stored
     * @param clazz class into which we map the excel row
     * @return {@link List} of type {@code T}
     */
    public List<T> generateMappedList(String filePath,Class<T> clazz) {

        LOGGER.info("Generating a List of mapped objects of type : {} from file path :{}",clazz,filePath);

        List<T> dtoList = new LinkedList<>();

        if(filePath != null) {

            try {
                dtoList.addAll(excelMapper.mappedList(clazz,filePath));
            } catch (Throwable e) {
                String message = String.format("Unable to read from excel file : %s for class : %S",
                        filePath, clazz);

                throw new ExcelMapperException(message, e);
            }

            LOGGER.info("Returning generated list of : {} items generated of type : {} from file path :{}",
                    dtoList.size(), clazz, filePath);
        } else {

            LOGGER.error("The FILE_PATH given is null...");
        }

        return Collections.unmodifiableList(dtoList);
    }
}
