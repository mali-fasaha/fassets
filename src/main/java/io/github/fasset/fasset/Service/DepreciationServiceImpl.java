package io.github.fasset.fasset.Service;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.repository.DepreciationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("depreciationService")
public class DepreciationServiceImpl implements DepreciationService {

    private static final Logger log = LoggerFactory.getLogger(DepreciationServiceImpl.class);


    @Qualifier("depreciationRepository")
    @Autowired
    private DepreciationRepository depreciationRepository;

    /**
     * Saves the {@link Depreciation} object given as parameter to the {@link DepreciationRepository}
     *
     * @param depreciation
     */
    @Override
    public void saveDepreciation(Depreciation depreciation) {

        log.debug("Saving depreciation : {} into the depreciationRepository",depreciation);

        depreciationRepository.save(depreciation);
    }
}
