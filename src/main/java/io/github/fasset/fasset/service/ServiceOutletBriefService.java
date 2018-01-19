package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.brief.ServiceOutletBrief;

import java.util.List;

public interface ServiceOutletBriefService {

    /**
     *
     * @return {@link List<ServiceOutletBrief>} of service outlets from repository
     */
    List<ServiceOutletBrief> fetchAllServiceOutletBriefs();

    /**
     *
     * @param id of the serviceOutletBrief
     * @return {@link ServiceOutletBrief} of the id given as parameter
     */
    ServiceOutletBrief fetchServiceOutletBriefGivenId(int id);
}
