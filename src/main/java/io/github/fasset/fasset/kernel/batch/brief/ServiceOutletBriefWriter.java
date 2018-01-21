package io.github.fasset.fasset.kernel.batch.brief;

import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
import io.github.fasset.fasset.service.FixedAssetService;
import io.github.fasset.fasset.service.ServiceOutletBriefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("serviceOutletBriefWriter")
public class ServiceOutletBriefWriter implements ItemWriter<ServiceOutletBrief> {

    private final static Logger log = LoggerFactory.getLogger(ServiceOutletBriefWriter.class);

    @Autowired
    @Qualifier("serviceOutletBriefService")
    private ServiceOutletBriefService serviceOutletBriefService;

    @Autowired
    @Qualifier("fixedAssetService")
    private FixedAssetService fixedAssetService;

    @Override
    public void write(List<? extends ServiceOutletBrief> list) throws Exception {

        List<ServiceOutletBrief> serviceOutletBriefList = new ArrayList<>();

        list.forEach(i ->

                serviceOutletBriefList.add(fixedAssetService.getServiceOutletBrief(i.getDesignation()))
        );

        log.info("Saving a list of  {} serviceOutletBrief items to the serviceOutletBrief repo",list.size());

        serviceOutletBriefService.saveAllServiceOutletBriefItems(serviceOutletBriefList);
    }
}
