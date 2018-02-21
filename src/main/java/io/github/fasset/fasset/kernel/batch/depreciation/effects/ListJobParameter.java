package io.github.fasset.fasset.kernel.batch.depreciation.effects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;

import java.util.List;

public class ListJobParameter<T> extends JobParameter {

    private static final Logger log = LoggerFactory.getLogger(ListJobParameter.class);

    private JobParameter jobParameter;

    private final List<T> value;

    public enum ParameterType {STRING, DATE, LONG, LIST ,DOUBLE;}

    private ParameterType parameterType;

    /**
     * Construct a new JobParameter as a String.
     *
     * @param parameter {@link String} instance.
     */
    public ListJobParameter(String parameter, List<T> value) {
        super(parameter);
        jobParameter = new JobParameter(parameter);
        this.value=value;

        log.debug("List job parameter has been created : {}",this);
    }
    /**
     * @return the value contained within this JobParameter.
     */
    @Override
    public Object getValue() {
        log.debug("Returing a list of items for depreciation : {}",value);
        return value;
    }

    @Override
    public String toString() {
        return jobParameter.toString()+String.format(" & list of %s items",value.size());
    }
}
