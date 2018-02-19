package io.github.fasset.fasset.kernel.batch.depreciation.report.category;

import io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlyCategoryDepreciationDTO;
import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import io.github.fasset.fasset.repository.DepreciationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("monthlyCategoryDepreciationExecutor")
public class MonthlyCategoryDepreciationExecutorImpl implements MonthlyCategoryDepreciationExecutor {

    private static final Logger log = LoggerFactory.getLogger(MonthlyCategoryDepreciationExecutorImpl.class);

    private final DepreciationRepository depreciationRepository;


    @Autowired
    public MonthlyCategoryDepreciationExecutorImpl(@Qualifier("depreciationRepository") DepreciationRepository depreciationRepository) {
        this.depreciationRepository = depreciationRepository;
    }

    /**
     * @param categoryName Name of the category we wish to summarise
     * @param year         Year of the depreciation
     * @return {@link MonthlyCategoryDepreciation } item relevant to the categoryName given
     * and the year
     */
    @Override
    public MonthlyCategoryDepreciation getMonthlyDepreciation(String categoryName, Integer year) {

        log.info("Pooling monthlyCategoryDepreciation in relation to category : {} for the year : {}",categoryName,year);

        MonthlyCategoryDepreciationDTO dto = depreciationRepository.getMonthlyCategoryDepreciation(categoryName,year).get(0);

        return new MonthlyCategoryDepreciation(dto.getCategoryName(),dto.getYear(),
                dto.getJan(),
                dto.getFeb(),
                dto.getMar(),
                dto.getApr(),
                dto.getMay(),
                dto.getJun(),
                dto.getJul(),
                dto.getAug(),
                dto.getSep(),
                dto.getOct(),
                dto.getNov(),
                dto.getDec());
    }
}
