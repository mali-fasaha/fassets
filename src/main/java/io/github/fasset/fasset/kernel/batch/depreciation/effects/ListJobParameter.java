package io.github.fasset.fasset.kernel.batch.depreciation.effects;

import org.springframework.batch.core.JobParameter;

import java.util.List;

public class ListJobParameter<T> extends JobParameter {

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
    }
    /**
     * @return the value contained within this JobParameter.
     */
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return jobParameter.toString()+String.format(" & list of %s items",value.size());
    }
}
