package io.github.fasset.fasset;

import java.time.YearMonth;

/**
 * This objects records the accrued depreciation for a given fixed asset at a given point in time being the end of a
 * given {@link java.time.YearMonth}
 *
 * @author edwin.njeru
 */
public class AccruedDepreciation extends DomainModel{

    /**
     * The period the end of which we are recording this accrued depreciation
     */
    private YearMonth month;
}
