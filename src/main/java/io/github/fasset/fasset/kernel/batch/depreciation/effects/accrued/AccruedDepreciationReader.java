package io.github.fasset.fasset.kernel.batch.depreciation.effects.accrued;

import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

import java.util.ArrayList;
import java.util.List;

public class AccruedDepreciationReader extends ListItemReader<AccruedDepreciationDto> implements ItemReader<AccruedDepreciationDto>{

    private static final Logger log = LoggerFactory.getLogger(AccruedDepreciationReader.class);

    public AccruedDepreciationReader(List<AccruedDepreciationDto> list) {

        super(list==null? new ArrayList<>() : list);

        if(list==null)
            log.error("The list of accruedDepreciationDtos passed is NULL!");


    }
}
