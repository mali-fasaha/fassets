/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.batch.upload;

import io.github.fasset.fasset.kernel.util.ConcurrentList;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.service.FixedAssetService;
import org.slf4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Reads and brings into the batch process lists of items from the data sink in bundles of lists. <br> It is imagined that this class will aggregate results from the fixedAssetsItemReader and hereby
 * pass on the data as List. <br> Instead of monkey-patching that we could conceive a process that reads all ids of assets from the data sink and regulate such a collection to completion pulling a few
 * at a time. <br> The same might also be done with a paging repository, much faster and easier, but less control over resources.
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("fixedAssetItemsReader")
public class FixedAssetItemsReader implements ItemReader<List<FixedAsset>> {

    private static final Logger log = getLogger(FixedAssetItemsReader.class);

    private FixedAssetService fixedAssetService;

    // size of items to be return per read()-call
    private int maximumPageSize;

    // list of already processed assets
    private List<Integer> processedAssetsIndices = ConcurrentList.newList();

//    @Value("${reader.fixed.assets.list.size}")
//    public void setMaximumPageSize(int maximumPageSize) {
//        log.trace("Setting reader page size as {} items per call", maximumPageSize);
//        this.maximumPageSize = maximumPageSize;
//    }

    @Autowired
    FixedAssetItemsReader(@Qualifier("fixedAssetService") FixedAssetService fixedAssetService, @Value("${reader.fixed.assets.list.size}") int maximumPageSize) {
        this.fixedAssetService = fixedAssetService;
        this.maximumPageSize = maximumPageSize;
    }

    /**
     * {@inheritDoc}
     *
     * Every time this method is called, it will return a List of unprocessed fixedAssets the size of which is dictated by the maximumPageSize;
     * <p>
     * Once the list of unprocessed items hits zero, the method call will return null;
     * </p>
     */
    @Override
    public List<FixedAsset> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        List<FixedAsset> unProcessedItems = getProcessingPage(fixedAssetService.fetchAllExistingAssets()
                                                                               .stream()
                                                                               .filter(asset -> !processedAssetsIndices.contains(asset.getId()))
                                                                               .collect(ImmutableListCollector.toImmutableFastList()), maximumPageSize);

        log.trace("Updating processedAssetsIndices list with {} items", unProcessedItems.size());
        // update processedAssetsIndices list
        unProcessedItems.stream()
                        .map(FixedAsset::getId)
                        .forEach(processedAssetsIndices::add);

        return unProcessedItems.size() == 0 ? null : unProcessedItems;
    }

    private List<FixedAsset> getProcessingPage(List<FixedAsset> unProcessedAssets, int pageSize) {

        log.trace("Generating processing page of size {} from {} unprocessed assets", pageSize, unProcessedAssets.size());

        List<FixedAsset> processingPage = ConcurrentList.newList();

        if (unProcessedAssets.size() > pageSize) {

            int i = 0;

            Iterator<FixedAsset> assetIterator = unProcessedAssets.iterator();

            while (i <= pageSize) {

                while (assetIterator.hasNext()) {

                    i++;

                    processingPage.add(assetIterator.next());

                }
            }
        } else {

            log.trace("Sending all {} unprocessed assets being less than the maximum maximumPageSize", unProcessedAssets.size());

            processingPage.addAll(unProcessedAssets);
        }

        return ConcurrentList.of(processingPage);
    }
}
