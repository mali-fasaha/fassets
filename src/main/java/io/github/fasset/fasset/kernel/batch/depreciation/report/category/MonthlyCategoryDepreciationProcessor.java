package io.github.fasset.fasset.kernel.batch.depreciation.report.category;

import io.github.fasset.fasset.model.depreciation.MonthlyCategoryDepreciation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonthlyCategoryDepreciationProcessor implements ItemProcessor<String, MonthlyCategoryDepreciation> {

    private static final Logger log = LoggerFactory.getLogger(MonthlyCategoryDepreciationProcessor.class);

    private MonthlyCategoryDepreciationExecutor executor;
    String year;

    @Autowired
    public MonthlyCategoryDepreciationProcessor(@Qualifier("monthylDepreciationDepreciationExecutor") MonthlyCategoryDepreciationExecutor executor, String year) {
        this.executor = executor;
        this.year=year;
    }

    /**
     * Process the provided item, returning a potentially modified or new item for continued
     * processing.  If the returned result is null, it is assumed that processing of the item
     * should not continue.
     *
     * @param item to be processed
     * @return potentially modified or new item for continued processing, null if processing of the
     * provided item should not continue.
     * @throws Exception thrown if exception occurs during processing.
     */
    @Override
    public MonthlyCategoryDepreciation process(String item) throws Exception {

        if(year==null){

            log.warn("The year value passed is null : {}",year);
        }

        return executor.getMonthlyDepreciation(item,Integer.parseInt(year));
    }
}
