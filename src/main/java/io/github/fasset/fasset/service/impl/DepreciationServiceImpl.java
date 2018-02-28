package io.github.fasset.fasset.service.impl;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationProceeds;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.repository.NetBookValueRepository;
import io.github.fasset.fasset.service.AccruedDepreciationService;
import io.github.fasset.fasset.service.DepreciationService;
import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.repository.DepreciationRepository;
import io.github.fasset.fasset.service.NetBookValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("depreciationService")
public class DepreciationServiceImpl implements DepreciationService {

    private static final Logger log = LoggerFactory.getLogger(DepreciationServiceImpl.class);


    @Qualifier("depreciationRepository")
    @Autowired
    private DepreciationRepository depreciationRepository;

    @Autowired
    private AccruedDepreciationService accruedDepreciationService;

    @Autowired
    private NetBookValueService netBookValueService;


    /**
     * Saves the {@link Depreciation} object given as parameter to the {@link DepreciationRepository}
     *
     * @param depreciation
     */
    @Override
    public void saveDepreciation(Depreciation depreciation) {

        log.debug("Saving depreciation : {} into the depreciationRepository", depreciation);

        depreciationRepository.save(depreciation);
    }

    /**
     * Saves all items in the list
     *
     * @param depreciationList
     */
    @Override
    public void saveAllDepreciationItems(List<Depreciation> depreciationList) {

        log.debug("Saving {} depreciation items to repository", depreciationList.size());

        depreciationRepository.saveAll(depreciationList);
    }

    /**
     * @return Return the number of distinct sols
     */
    @Override
    public int getDistinctSolIds() {

        return depreciationRepository.countDistinctSolIds();
    }

    /**
     * Saves multiple items using multiple repositories for items encapsulated in the
     * DepreciationProceeds object
     *
     * @param list of depreciationProceeds
     */
    @Override
    public void saveAllDepreciationProceeds(List<DepreciationProceeds> list) {

        depreciationRepository.saveAll(
                list.stream()
                        .map(DepreciationProceeds::getDepreciation)
                        .collect(ImmutableListCollector.toImmutableList())
        );



        accruedDepreciationService.saveAllAccruedDepreciationRecords(
                list.stream()
                .map(DepreciationProceeds::getAccruedDepreciation)
                .collect(ImmutableListCollector.toImmutableList())
        );

        netBookValueService.saveAllNetBookValueItems(
                list.stream()
                .map(DepreciationProceeds::getNetBookValue)
                .collect(ImmutableListCollector.toImmutableList())
        );

    }
}
