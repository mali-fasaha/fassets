package io.github.fasset.fasset.service;

import io.github.fasset.fasset.model.NetBookValue;
import io.github.fasset.fasset.model.brief.CategoryBrief;

import java.util.List;

public interface NetBookValueService {

    /**
     * Saves the {@link NetBookValue} object in the param
     * @param netBookValue
     */
    void saveNetBookValue(NetBookValue netBookValue);

    /**
     * Saves a {@link List} collection of all items passed in the parameter to the
     * {@link io.github.fasset.fasset.repository.NetBookValueRepository}
     * @param netBookValues
     */
    void saveAllNetBookValueItems(List<? extends NetBookValue> netBookValues);


}
