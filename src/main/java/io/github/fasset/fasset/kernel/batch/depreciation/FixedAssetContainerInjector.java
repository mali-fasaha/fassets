package io.github.fasset.fasset.kernel.batch.depreciation;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * This object compiles data into the fixedAssetsContainer before the step is implemented in which
 * the ItemProcessor is passed list data encapsulated into the fixedAssetContainer
 */
@Component("fixedAssetsContainerInjector")
public class FixedAssetContainerInjector implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
