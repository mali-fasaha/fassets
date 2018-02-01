package io.github.fasset.fasset.kernel.batch.depreciation.report;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.repository.DepreciationRepository;
import io.github.fasset.fasset.service.DepreciationService;
import io.github.fasset.fasset.service.MonthlyAssetDepreciationService;
import io.github.fasset.fasset.service.impl.MonthlyAssetDepreciationServiceImpl;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service("monthlyAssetDepreciationExecutor")
@Transactional
public class MonthlyAssetDepreciationExecutorImpl implements MonthlyAssetDepreciationExecutor {

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationExecutorImpl.class);

    private final DepreciationRepository depreciationRepository;

    @Autowired
    public MonthlyAssetDepreciationExecutorImpl(@Qualifier("depreciationRepository") DepreciationRepository depreciationRepository) {
        this.depreciationRepository = depreciationRepository;
    }

    /**
     * Returns {@link MonthlyAssetDepreciation} item that is updated with data from the depreciation
     * item
     *
     * @param fixedAsset
     * @return
     */
    @Override
    public MonthlyAssetDepreciation getMonthlyDepreciation(FixedAsset fixedAsset,Integer year) {

        return depreciationRepository.getMonthlyAssetDepreciation(fixedAsset.getId(),year);
    }
}
