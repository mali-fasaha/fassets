package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Reads and brings into the batch process lists of items from the data sink in bundles of lists. <br> It is imagined that this class will aggregate results from the fixedAssetsItemReader and hereby
 * pass on the data as List. <br> Instead of monkey-patching that we could conceive a process that reads all ids of assets from the data sink and regulate such a collection to completion pulling a few
 * at a time. <br> The same might also be done with a paging repository, much faster and easier, but less control over resources.
 */
@Component("fixedAssetItemsReader")
public class FixedAssetItemsReader implements ItemReader<List<FixedAsset>> {

    @Override
    public List<FixedAsset> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
