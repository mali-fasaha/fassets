package io.github.fasset.fasset.kernel.batch.nbv;

import io.github.fasset.fasset.model.NetBookValue;
import io.github.fasset.fasset.service.NetBookValueService;
import io.github.fasset.fasset.service.impl.NetBookValueServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("netBookValueItemWriter")
public class NetBookValueItemWriter implements ItemWriter<NetBookValue> {

    private final static Logger log = LoggerFactory.getLogger(NetBookValueItemWriter.class);

    private final NetBookValueService netBookValueService;

    @Autowired
    public NetBookValueItemWriter(@Qualifier("netBookValueService") NetBookValueService netBookValueService) {
        this.netBookValueService = netBookValueService;
    }

    /**
     * Process the supplied data element. Will not be called with any null items
     * in normal operation.
     *
     * @param items items to be written
     * @throws Exception if there are errors. The framework will catch the
     *                   exception and convert or rethrow it as appropriate.
     */
    @Override
    public void write(List<? extends NetBookValue> items) throws Exception {

        log.info("Writing {} NetBookValue items to the NetBookValueRepository",items.size());

        netBookValueService.saveAllNetBookValueItems(items);
    }
}
