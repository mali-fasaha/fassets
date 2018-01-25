package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationProperties;
import io.github.fasset.fasset.model.Depreciation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

@Component("depreciationRelay")
public class DepreciationRelay {

    @Autowired
    @Qualifier("MonthlyIncrementer")
    private MonthlyIncrementer monthlyIncrementer;

    @Autowired
    private DepreciationProperties depreciationProperties;


    public List<YearMonth> getMonthlyDepreciationSequence(){

        YearMonth from = depreciationProperties.getStartMonth();
        YearMonth to = depreciationProperties.getStopMonth();

        List<YearMonth> monthlySequence = new LinkedList<>();

        monthlySequence.add(from);

        int no_of_months = to.compareTo(from);

        for(int i = 0; i < no_of_months; i++) {

            monthlySequence.add(monthlyIncrementer.getNext(from));
        }

        return monthlySequence;
    }
}
