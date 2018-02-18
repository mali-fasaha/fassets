package io.github.fasset.fasset.kernel.batch.depreciation.filter;

import io.github.fasset.fasset.model.FixedAsset;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;

@Component("filterChain")
public class FilterChain{

    private static final Logger log = LoggerFactory.getLogger(FilterChain.class);

    /* Will run before the actual depreciation is executed. Will run whether or not
    certain process-specific criterias are met
     */
    private List<Filter<FixedAsset>> beforeProcessFilters = new FastList<>();

    /* Will run with the process. If the request passed does not meet the criteria, it will not
    be processed
     */
    private List<Filter<FixedAsset>> inProcessFilters = new FastList<>();

    /*
    Will run after the process critical activity has run, being processed whether or not process-critical
    criteria is met
     */
    private List<Filter<FixedAsset>> afterProcessFilters = new FastList<>();

    private Target target;

    public void addFilter(Filter filter){

        beforeProcessFilters.add(filter);
    }

    public FilterChain setTarget(Target target) {
        this.target = target;
        return this;
    }

    public void execute(FixedAsset asset, YearMonth month){

        if(!beforeProcessFilters.isEmpty())
        beforeProcessFilters.forEach(f ->{
            log.trace("Executing asset : {}, for the month : {} with filter item : {}",asset,month,f);
            f.execute(asset,month);
        });

        if(!inProcessFilters.isEmpty())
        inProcessFilters.forEach(
                filter -> {
                    if(filter.meetsCriteria(asset,month,filter.getCriteria()))
                        filter.execute(asset,month);
                }
        );

        if(!afterProcessFilters.isEmpty())
        afterProcessFilters.forEach(f -> f.execute(asset,month));

        target.execute(asset,month);
    }
}
