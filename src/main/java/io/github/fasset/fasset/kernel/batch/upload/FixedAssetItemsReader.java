package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.model.FixedAsset;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Reads and brings into the batch process lists of items from the data sink in bundles of lists.
 *
 */
@Component("FixedAssetItemsReader")
public class FixedAssetItemsReader implements ItemReader<List<FixedAsset>> {

    @Override
    public List<FixedAsset> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
