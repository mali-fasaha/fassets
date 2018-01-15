package io.github.fasset.fasset.Service;

import io.github.fasset.fasset.model.NetBookValue;
import io.github.fasset.fasset.repository.NetBookValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implements the {@link NetBookValueService} interface saving and retrieving {@link NetBookValue}
 * records into the {@link io.github.fasset.fasset.repository.NetBookValueRepository}
 *
 * @author edwin.njeru
 */
@Service("netBookValueService")
@Transactional
public class NetBookValueServiceImpl implements NetBookValueService {


    @Qualifier("netBookValueRepository")
    @Autowired
    private NetBookValueRepository netBookValueRepository;

    /**
     * Saves the {@link NetBookValue} object in the param
     *
     * @param netBookValue
     */
    @Override
    public void saveNetBookValue(NetBookValue netBookValue) {

        netBookValueRepository.save(netBookValue);
    }
}
