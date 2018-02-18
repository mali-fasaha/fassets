package io.github.fasset.fasset.kernel.batch.depreciation.filter;

import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("filterChain")
public class FilterChain {

    private static final Logger log = LoggerFactory.getLogger(FilterChain.class);

    private List<Filter> filters = new FastList<Filter>();

    private Target target;

    public void addFilter(Filter filter){

        filters.add(filter);
    }

    public FilterChain setTarget(Target target) {
        this.target = target;
        return this;
    }

    public void execute(final Object... request){

        filters.forEach(f ->{
            log.trace("Executing request : {} with filter item : {}",request,f);
            f.execute(request);
        });
    }
}
