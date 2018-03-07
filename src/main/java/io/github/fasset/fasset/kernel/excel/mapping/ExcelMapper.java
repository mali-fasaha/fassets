package io.github.fasset.fasset.kernel.excel.mapping;

import io.github.fasset.fasset.config.MapperOptions;

import java.util.List;

/**
 * This object reads connect from an excel file and maps the same connect to a list of the parameter DTO
 *
 * @author - Edwin Njeru
 */
public interface ExcelMapper {

    /**
     * This return a list of all items as they are read from the library
     * @param c class defining the mapped object
     * @param path path of the excel file
     * @return list of mapped objects
     */
    List mappedList(Class<?> c, String path);

    /**
     *
     * @param mapperOptions for excel poiji reader
     */
    void setMapperOptions(MapperOptions mapperOptions);
}
