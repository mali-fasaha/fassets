package io.github.fasset.fasset.kernel.batch.depreciation.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class FilterManager {

    private final FilterChain filterChain;

    @Autowired
    public FilterManager(@Qualifier("filterChain") FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public void setFilter(Filter filter){

        filterChain.addFilter(filter);
    }

    public void filterRequest(Object... request){

        filterChain.execute(request);
    }
}
