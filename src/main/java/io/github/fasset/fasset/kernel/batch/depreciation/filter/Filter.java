package io.github.fasset.fasset.kernel.batch.depreciation.filter;

import io.github.fasset.fasset.kernel.batch.depreciation.filter.criteria.Criteria;
import io.github.fasset.fasset.model.FixedAsset;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectListImpl;

import java.time.YearMonth;
import java.util.List;

public interface Filter<F> {

    void execute(F fixedAsset, YearMonth month);

    List<Criteria> getCriteria();

    boolean meetsCriteria(F asset, YearMonth month, List<Criteria> criteria);
}
