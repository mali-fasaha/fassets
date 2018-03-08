package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.service.NetBookValueService;
import io.github.fasset.fasset.model.NetBookValue;
import io.github.fasset.fasset.repository.NetBookValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implements the {@link NetBookValueService} interface saving and retrieving {@link NetBookValue}
 * records into the {@link io.github.fasset.fasset.repository.NetBookValueRepository}
 *
 * @author edwin.njeru
 */
@Service("netBookValueService")
@Transactional
public class NetBookValueServiceImpl implements NetBookValueService {


    private final NetBookValueRepository netBookValueRepository;

    @Autowired
    public NetBookValueServiceImpl(@Qualifier("netBookValueRepository") NetBookValueRepository netBookValueRepository) {
        this.netBookValueRepository = netBookValueRepository;
    }

    /**
     * Saves the {@link NetBookValue} object in the param
     *
     * @param netBookValue
     */
    @Override
    public void saveNetBookValue(NetBookValue netBookValue) {

        netBookValueRepository.save(netBookValue);
    }

    /**
     * Saves a {@link List} collection of all items passed in the parameter to the
     * {@link NetBookValueRepository}
     *
     * @param netBookValues
     */
    @Override
    public void saveAllNetBookValueItems(List<? extends NetBookValue> netBookValues) {

        netBookValueRepository.saveAll(netBookValues);

    }

}
