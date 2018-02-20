package io.github.fasset.fasset.kernel.batch.depreciation.effects.accrued;

import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

import java.util.List;

public class AccruedDepreciationReader extends ListItemReader<AccruedDepreciationDto> implements ItemReader<AccruedDepreciationDto>{

    public AccruedDepreciationReader(List<AccruedDepreciationDto> list) {
        super(list);
    }
}
