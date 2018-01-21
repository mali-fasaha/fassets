package io.github.fasset.fasset.kernel.batch.brief;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("serviceOutletBriefProcessor")
public class ServiceOutletBriefProcessor implements ItemProcessor<FixedAsset,ServiceOutletBrief> {


    @Override
    public ServiceOutletBrief process(FixedAsset fixedAsset) throws Exception {

        ServiceOutletBrief brief = new ServiceOutletBrief();

        brief.setDesignation(fixedAsset.getSolId());

        return brief;
    }
}
