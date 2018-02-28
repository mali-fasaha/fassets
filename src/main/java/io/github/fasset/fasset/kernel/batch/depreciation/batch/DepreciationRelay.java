package io.github.fasset.fasset.kernel.batch.depreciation.batch;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@Component("depreciationRelay")
public class DepreciationRelay {

    private static final Logger log = LoggerFactory.getLogger(DepreciationRelay.class);

    private final MonthlyIncrementer monthlyIncrementer;

    private final DepreciationProperties depreciationProperties;

    private List<YearMonth> monthlySequence = new LinkedList<>();

    @Autowired
    public DepreciationRelay(@Qualifier("MonthlyIncrementer") MonthlyIncrementer monthlyIncrementer, DepreciationProperties depreciationProperties) {
        this.monthlyIncrementer = monthlyIncrementer;
        this.depreciationProperties = depreciationProperties;
    }

    public List<YearMonth> getMonthlyDepreciationSequence(){

        return monthlySequence;
    }

    @PostConstruct
    private List<YearMonth> generateMonthlyDepreciationSequence(){

        YearMonth from = depreciationProperties.getStartMonth();
        YearMonth to = depreciationProperties.getStopMonth();

        log.debug("Producing depreciation relay...between : {} and : {}",from,to);

        monthlySequence.add(from);

        long no_of_months = from.until(to, ChronoUnit.MONTHS);

        log.debug("Creating a monthly depreciation sequence for : {}",no_of_months);

        for(long i = 0; i < no_of_months; i++) {

            YearMonth monthSeq = monthlyIncrementer.getNext(from.plusMonths(i));

            log.debug("Adding the month : {} to the sequence",monthSeq);

            monthlySequence.add(monthSeq);
        }

        return monthlySequence;
    }
}
