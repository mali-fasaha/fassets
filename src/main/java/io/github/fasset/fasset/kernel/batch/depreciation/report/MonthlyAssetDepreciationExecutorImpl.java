package io.github.fasset.fasset.kernel.batch.depreciation.report;

import io.github.fasset.fasset.model.Depreciation;
import io.github.fasset.fasset.model.depreciation.MonthlyAssetDepreciation;
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

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service("monthlyAssetDepreciationExecutor")
@Transactional
public class MonthlyAssetDepreciationExecutorImpl implements MonthlyAssetDepreciationExecutor {

    private static final Logger log = LoggerFactory.getLogger(MonthlyAssetDepreciationExecutorImpl.class);

    @Autowired
    @Qualifier("monthlyAssetDepreciationService")
    private MonthlyAssetDepreciationService monthlyAssetDepreciationService;

    @Autowired
    @Qualifier("depreciationService")
    private DepreciationService depreciationService;

    /**
     * Returns {@link MonthlyAssetDepreciation} item that is updated with data from the depreciation
     * item
     *
     * @param depreciation
     * @return
     */
    @Override
    public MonthlyAssetDepreciation getMonthlyDepreciation(Depreciation depreciation) {

        log.debug("Adding depreciation : {} to our MonthlyDepreciation model",depreciation);

        MonthlyAssetDepreciation monthlyAssetDepreciation = refreshMonthlyAssetDepreciation(depreciation);

        udpateMonthlyDepreciation(depreciation, monthlyAssetDepreciation);

        return monthlyAssetDepreciation;
    }

    private void udpateMonthlyDepreciation(Depreciation depreciation, MonthlyAssetDepreciation monthlyAssetDepreciation) {
        switch (depreciation.getDepreciationPeriod().getMonthValue()) {
            case 1:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJan(depreciation.getDepreciation());
                break;
            case 2:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setFeb(depreciation.getDepreciation());
                break;
            case 3:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setMar(depreciation.getDepreciation());
                break;
            case 4:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setApr(depreciation.getDepreciation());
                break;
            case 5:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setMay(depreciation.getDepreciation());
                break;
            case 6:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJun(depreciation.getDepreciation());
                break;
            case 7:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJul(depreciation.getDepreciation());
                break;
            case 8:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setAug(depreciation.getDepreciation());
                break;
            case 9:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setSep(depreciation.getDepreciation());
                break;
            case 10:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setOct(depreciation.getDepreciation());
                break;
            case 11:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setNov(depreciation.getDepreciation());
                break;
            case 12:
                log.debug("Updating : {} model for the : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setDec(depreciation.getDepreciation());
                break;
            default:
                log.warn("Updating : {} model for the DEFAULT : {} month",monthlyAssetDepreciation,depreciation.getDepreciationPeriod().getMonthValue());
                monthlyAssetDepreciation.setJan(depreciation.getDepreciation());
        }
    }

    private MonthlyAssetDepreciation refreshMonthlyAssetDepreciation(Depreciation depreciation) {

        log.debug("Checking if assetId : {} has been added to the MonthlyDepreciation model " +
                "for the year : {}",depreciation.getFixedAssetId(),depreciation.getDepreciationPeriod().getYear());
        MonthlyAssetDepreciation monthlyAssetDepreciation =
                monthlyAssetDepreciationService
                        .getMonthlyAssetDepreciationByAssetIdAndYear(depreciation.getFixedAssetId(),
                                getYear(depreciation));

        if (monthlyAssetDepreciation == null) {

            log.debug("The fixedAssetId : {} has not been logged to our MonthlyAssetDepreciation " +
                    "repository. Creating a new MonthlyAssetDepreciation item...",depreciation.getFixedAssetId());
            monthlyAssetDepreciation = new MonthlyAssetDepreciation();
            monthlyAssetDepreciation.setAssetId(depreciation.getFixedAssetId())
                    .setYear(getYear(depreciation));
        }else{
            log.debug("The repository already contains fixedAssetId : {} embodied in depreciation " +
                    "model : {}",depreciation.getFixedAssetId(),monthlyAssetDepreciation);
        }

        return monthlyAssetDepreciation;
    }

    private int getYear(Depreciation depreciation) {
        return depreciation.getDepreciationPeriod().getYear();
    }
}
