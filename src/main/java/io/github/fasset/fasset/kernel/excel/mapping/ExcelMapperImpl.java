package io.github.fasset.fasset.kernel.excel.mapping;

import com.poiji.bind.Poiji;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.github.fasset.fasset.config.MapperOptions;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Thread-safe implementation of the {@link ExcelMapper} 
 * The client must specify the parameter type
 * 
 *
 * @author edwin.njeru
 */
@Component("excelMapper")
public class ExcelMapperImpl implements ExcelMapper {

    private final static Logger LOGGER = getLogger(ExcelMapperImpl.class);


    private MapperOptions mapperOptions;


    @Override
    public void setMapperOptions(MapperOptions mapperOptions) {
        this.mapperOptions = mapperOptions;
    }

    @Autowired
    public ExcelMapperImpl(@Qualifier("mapperOptions") MapperOptions mapperOptions) {
        this.mapperOptions = mapperOptions;
    }

    /**
     * This return a list of all items of type @param <M>  as they are read from the library
     *
     * @param clazz   class defining the mapped object
     * @param path    path of the excel file
     * @return list of mapped objects
     */
    @SuppressWarnings("unchecked")
	@Override
    public List mappedList(Class<?> clazz, String path) {

        LOGGER.info("Creating a mapped list for class : {}, using file from the path : {}",
                clazz,path);

        List mappedlist = new LinkedList<>();

        try {

            mappedlist.addAll(Poiji.fromExcel(new File(path), clazz, mapperOptions.getPoijiOptions()));

        } catch (Throwable e) {

            String message = String.format("Unable to read from excel file : %s for class : %S with options : %s",
                    path,clazz,mapperOptions);

            throw new ExcelMapperException(message, e);
        }

        LOGGER.info("{} items have been inserted into a list...", mappedlist.size());

        return Collections.unmodifiableList(mappedlist);
    }

}
