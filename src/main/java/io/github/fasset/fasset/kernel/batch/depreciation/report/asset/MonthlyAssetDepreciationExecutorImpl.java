package io.github.fasset.fasset.kernel.batch.depreciation.report.asset;

import io.github.fasset.fasset.kernel.batch.depreciation.model.MonthlyAssetDepreciationDTO;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
import io.github.fasset.fasset.repository.DepreciationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        MonthlyAssetDepreciationDTO dto = null;

        try {
            List<MonthlyAssetDepreciationDTO> tempList = depreciationRepository.getMonthlyAssetDepreciation(fixedAsset.getId(),year);
            MonthlyAssetDepreciationDTO temp=null;

            if (!tempList.isEmpty()) {
                temp = tempList.get(0);
            } else {
                // do nothing
            }

            dto = temp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert dto != null;
        return new MonthlyAssetDepreciation(dto.getAssetId(),dto.getYear(),
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
