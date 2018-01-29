package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationProperties;
import io.github.fasset.fasset.model.Depreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
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

        //FIXME int no_of_months = to.compareTo(from);
        int no_of_months = 60;

        for(int i = 0; i < no_of_months; i++) {

            YearMonth monthSeq = monthlyIncrementer.getNext(from.plusMonths(i));

            log.debug("Adding the month : {} to the sequence",monthSeq);

            monthlySequence.add(monthSeq);
        }

        return monthlySequence;
    }
}
