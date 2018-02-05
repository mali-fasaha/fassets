package io.github.fasset.fasset.kernel.batch.depreciation.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.YearMonth;

@ConfigurationProperties("depreciation")
public class DepreciationProperties {

    private YearMonth startMonth = YearMonth.of(2018,1);
    private YearMonth stopMonth = YearMonth.of(2025,6);

    public YearMonth getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(YearMonth startMonth) {
        this.startMonth = startMonth;
    }

    public YearMonth getStopMonth() {
        return stopMonth;
    }

    public void setStopMonth(YearMonth stopMonth) {
        this.stopMonth = stopMonth;
    }
}
