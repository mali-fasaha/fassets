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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("depreciationService")
public class DepreciationServiceImpl implements DepreciationService {

    private static final Logger log = LoggerFactory.getLogger(DepreciationServiceImpl.class);


    private final DepreciationRepository depreciationRepository;

    private final AccruedDepreciationService accruedDepreciationService;

    private final NetBookValueService netBookValueService;

    @Autowired
    public DepreciationServiceImpl(@Qualifier("depreciationRepository") DepreciationRepository depreciationRepository, AccruedDepreciationService accruedDepreciationService, NetBookValueService netBookValueService) {
        this.depreciationRepository = depreciationRepository;
        this.accruedDepreciationService = accruedDepreciationService;
        this.netBookValueService = netBookValueService;
    }


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
     * @param depreciationList to be persisted to the depreciationRepository
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
