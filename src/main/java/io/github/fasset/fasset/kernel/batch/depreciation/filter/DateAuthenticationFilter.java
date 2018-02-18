package io.github.fasset.fasset.kernel.batch.depreciation.filter;

import io.github.fasset.fasset.kernel.batch.depreciation.filter.criteria.Criteria;
import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.List;

@Component("dateAuthenticationFilter")
public class DateAuthenticationFilter implements Filter<FixedAsset> {

    @Override
    public void execute(FixedAsset fixedAsset, YearMonth month) {
        // crickets
    }

    @Override
    public List<Criteria> getCriteria() {
        return null;
    }

    @Override
    public boolean meetsCriteria(FixedAsset asset, YearMonth month, List<Criteria> criteria) {

        // to redesign
        criteria.forEach(c -> c.meetsCriteria(asset));

        return false;
    }
}
