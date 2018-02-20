package io.github.fasset.fasset.kernel.batch.depreciation.effects;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("updates")
public class DepreciationUpdateProperties {

    private Integer tippingPoint = 100;

    public Integer getTippingPoint() {
        return tippingPoint;
    }

    public DepreciationUpdateProperties setTippingPoint(Integer tippingPoint) {
        this.tippingPoint = tippingPoint;
        return this;
    }
}
