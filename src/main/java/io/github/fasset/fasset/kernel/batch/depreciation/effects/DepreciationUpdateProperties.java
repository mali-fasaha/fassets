package io.github.fasset.fasset.kernel.batch.depreciation.effects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("updates")
public class DepreciationUpdateProperties {

    private static final Logger log = LoggerFactory.getLogger(DepreciationUpdateProperties.class);

    private Integer tippingPoint = 10;

    public Integer getTippingPoint() {

        log.debug("Returning tiping point of  : {}",tippingPoint );
        return tippingPoint;
    }

    public DepreciationUpdateProperties setTippingPoint(Integer tippingPoint) {

        log.debug("Setting tippingPoint as : {}",tippingPoint);
        this.tippingPoint = tippingPoint;
        return this;
    }
}
