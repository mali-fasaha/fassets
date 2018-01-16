package io.github.fasset.fasset.kernel.batch.upload;

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

@Component("netBookValueWriter")
public class NetBookValueWriter implements ItemWriter<NetBookValue> {

    private static final Logger log = LoggerFactory.getLogger(NetBookValueWriter.class);


    @Autowired
    @Qualifier("netBookValueService")
    private NetBookValueService netBookValueService;

    /**
     * Process the supplied data element. Will not be called with any null items
     * in normal operation.
     *
     * @param netBookValues to be written
     * @throws Exception if there are errors. The framework will catch the
     *                   exception and convert or rethrow it as appropriate.
     */
    @Override
    public void write(List<? extends NetBookValue> netBookValues) throws Exception {

        log.info("Writing a list of {} netBookValue items to the repository",netBookValues.size());

        netBookValueService.saveAllNetBookValueItems(netBookValues);
    }
}
