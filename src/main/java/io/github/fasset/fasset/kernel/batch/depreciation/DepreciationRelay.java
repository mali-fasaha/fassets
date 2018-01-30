package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationProperties;
import io.github.fasset.fasset.model.Depreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@Component("depreciationRelay")
public class DepreciationRelay {

    private static final Logger log = LoggerFactory.getLogger(DepreciationRelay.class);

    @Autowired
    @Qualifier("MonthlyIncrementer")
    private MonthlyIncrementer monthlyIncrementer;

    @Autowired
    private DepreciationProperties depreciationProperties;


    public List<YearMonth> getMonthlyDepreciationSequence(){

        YearMonth from = depreciationProperties.getStartMonth();
        YearMonth to = depreciationProperties.getStopMonth();

        log.debug("Producing depreciation relay...between : {} and : {}",from,to);

        List<YearMonth> monthlySequence = new LinkedList<>();

        monthlySequence.add(from);

        long no_of_months = from.until(to, ChronoUnit.MONTHS)+1;

        log.debug("Creating a monthly depreciation sequence for : {}",no_of_months);

        for(long i = 0; i < no_of_months; i++) {

            YearMonth monthSeq = monthlyIncrementer.getNext(from.plusMonths(i));

            log.debug("Adding the month : {} to the sequence",monthSeq);

            monthlySequence.add(monthSeq);
        }

        return monthlySequence;
    }
}
