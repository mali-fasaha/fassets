package io.github.fasset.fasset.kernel.batch.depreciation.filter;

import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component("filterManager")
public class FilterManager {

    private final FilterChain filterChain;

    @Autowired
    public FilterManager(@Qualifier("filterChain") FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public void setFilter(Filter filter){

        filterChain.addFilter(filter);
    }

    public void filterRequest(FixedAsset asset, YearMonth month){

        filterChain.execute(asset,month);
    }
}
