package io.github.fasset.fasset.kernel.batch.depreciation.report.asset;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationRelay;
import io.github.fasset.fasset.model.Depreciation;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service("queriedMonthlyAssetDepreciationExecutor")
@Transactional
public class QueriedMonthlyAssetDepreciationExecutor {

    private final static Logger log = LoggerFactory.getLogger(QueriedMonthlyAssetDepreciationExecutor.class);


    @Qualifier("depreciationRepository")
    @Autowired
    private DepreciationRepository depreciationRepository;

    @Qualifier("depreciationRelay")
    @Autowired
    private DepreciationRelay depreciationRelay;


    public List<MonthlyAssetDepreciation> getMonthlyDepreciation(FixedAsset fixedAsset) {

        List<Depreciation> depreciations = getDepreciationsForAsset(fixedAsset);

        List<MonthlyAssetDepreciation> monthlyAssetDepreciations = getMonthlyAssetDepreciations(fixedAsset, depreciations);

        for (Depreciation depreciation : depreciations) {

            for (MonthlyAssetDepreciation monthlyAssetDepreciation : monthlyAssetDepreciations) {

                updateMonthlyDepreciation(depreciation, monthlyAssetDepreciation);
            }

        }


        return monthlyAssetDepreciations;
    }

    /**
     * Returns a List of MonthlyAssetDepreciation items related to a given fixedAsset item, using its
     * list of depreciations
     *
     * @param fixedAsset
     * @param depreciations
     * @return
     */
    private List<MonthlyAssetDepreciation> getMonthlyAssetDepreciations(FixedAsset fixedAsset, List<Depreciation> depreciations) {
        return depreciations
                .stream()
                .filter(Objects::nonNull)
                .map(depreciation -> new MonthlyAssetDepreciation()
                        .setYear(depreciation.getDepreciationPeriod().getYear())
                        .setAssetId(fixedAsset.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Returns depreciation items for a given fixedAsset
     *
     * @param fixedAsset
     * @return
     */
    private List<Depreciation> getDepreciationsForAsset(FixedAsset fixedAsset) {

        return depreciationRepository
                .getDistinctDepreciationPeriods()
                .stream()
                .map(month ->
                        depreciationRepository.getDepreciationByDepreciationPeriodAndFixedAssetId(month, fixedAsset.getId())
                )
                .collect(Collectors.toList());
    }

    /**
     * Will update the monthlyAssetDepreciation parameter with the correct depreciation in the correct month
     *
     * @param depreciation
     * @param monthlyAssetDepreciation
     */
    private void updateMonthlyDepreciation(Depreciation depreciation, MonthlyAssetDepreciation monthlyAssetDepreciation) {
        switch (depreciation.getDepreciationPeriod().getMonthValue()) {
            case 1:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJan(depreciation.getDepreciation());
                break;
            case 2:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setFeb(depreciation.getDepreciation());
                break;
            case 3:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setMar(depreciation.getDepreciation());
                break;
            case 4:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setApr(depreciation.getDepreciation());
                break;
            case 5:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setMay(depreciation.getDepreciation());
                break;
            case 6:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJun(depreciation.getDepreciation());
                break;
            case 7:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJul(depreciation.getDepreciation());
                break;
            case 8:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setAug(depreciation.getDepreciation());
                break;
            case 9:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setSep(depreciation.getDepreciation());
                break;
            case 10:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setOct(depreciation.getDepreciation());
                break;
            case 11:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setNov(depreciation.getDepreciation());
                break;
            case 12:
                log.debug("Updating : {} model for the month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setDec(depreciation.getDepreciation());
                break;
            default:
                log.warn("Updating : {} model for the DEFAULT month # : {}", monthlyAssetDepreciation, depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJan(depreciation.getDepreciation());
        }
    }
}
