package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationExecutor;
import io.github.fasset.fasset.kernel.batch.depreciation.DepreciationExecutorImpl;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.AccruedDepreciationColleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.FixedAssetsColleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.NetBookValueColleague;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DepreciationUpdateDispatcherConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DepreciationUpdateDispatcherConfiguration.class);

    @Autowired
    @Qualifier("accruedDepreciationColleague")
    private AccruedDepreciationColleague accruedDepreciationColleague;


    @Autowired
    @Qualifier("fixedAssetsColleague")
    private FixedAssetsColleague fixedAssetsColleague;

    @Autowired
    @Qualifier("netBookValueColleague")
    private NetBookValueColleague netBookValueColleague;

    @Autowired
    @Qualifier("depreciationUpdateDispatcher")
    private DepreciationUpdateDispatcher depreciationUpdateDispatcher;

    @Autowired
    @Qualifier("depreciationExecutor")
    private DepreciationExecutorImpl depreciationExecutor;


    @PostConstruct
    public void configureDispatcher(){

        log.info("Registering colleagues...");

        depreciationUpdateDispatcher.addColleague(accruedDepreciationColleague);
        depreciationUpdateDispatcher.addColleague(fixedAssetsColleague);
        depreciationUpdateDispatcher.addColleague(netBookValueColleague);
        depreciationUpdateDispatcher.addColleague(depreciationExecutor);
    }

}
