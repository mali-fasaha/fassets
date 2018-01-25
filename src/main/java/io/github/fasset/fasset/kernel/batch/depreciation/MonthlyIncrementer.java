package io.github.fasset.fasset.kernel.batch.depreciation;

import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationJobInstance;
import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationJobInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Component("MonthlyIncrementer")
public class MonthlyIncrementer {

    private final DepreciationJobInstanceRepository depreciationJobInstanceRepository;

    @Autowired
    public MonthlyIncrementer(@Qualifier("depreciationJobInstanceRepository") DepreciationJobInstanceRepository depreciationJobInstanceRepository) {
        this.depreciationJobInstanceRepository = depreciationJobInstanceRepository;
    }

    public List<DepreciationJobInstance> getJobInstanceSequence(){

        return depreciationJobInstanceRepository
                .findAll()
                .parallelStream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<YearMonth> getMonthSequence(){

       return getJobInstanceSequence()
                .stream()
                .map(DepreciationJobInstance::getMonth)
                .collect(Collectors.toList());
    }

    public YearMonth getLatest(){

        List<DepreciationJobInstance> jobInstanceList = getJobInstanceSequence();

        int listSize = jobInstanceList.size();

        if(listSize != 0){
            return null;
        } else{
            return jobInstanceList.get(listSize -1).getMonth();
        }
    }

    public YearMonth getNext(YearMonth month){

        /*if(depreciationJobInstanceRepository.findAllByMonthBefore(month)==null){
            //TODO implement month when months are empty
        }*/

        depreciationJobInstanceRepository.save(new DepreciationJobInstance(month));

        return month.plusMonths(1);
    }
}
