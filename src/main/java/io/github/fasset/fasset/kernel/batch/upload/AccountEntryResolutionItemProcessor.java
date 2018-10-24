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

import io.github.fasset.fasset.accounts.BatchEntryResolver;
import io.github.fasset.fasset.kernel.book.keeper.AccountingEntry;
import io.github.fasset.fasset.model.FixedAsset;
import org.slf4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This processor primarily converts lists of FixedAsset objects into lists of Account domain AccountingEntry objects
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("accountEntryResolutionItemProcessor")
public class AccountEntryResolutionItemProcessor implements ItemProcessor<List<FixedAsset>, List<AccountingEntry>> {

    private static final Logger log = getLogger(AccountEntryResolutionItemProcessor.class);

    private BatchEntryResolver batchEntryResolver;

    /**
     * <p>Constructor for AccountEntryResolutionItemProcessor.</p>
     *
     * @param batchEntryResolver a {@link io.github.fasset.fasset.accounts.BatchEntryResolver} object.
     */
    @Autowired
    public AccountEntryResolutionItemProcessor(@Qualifier("batchEntryResolver") BatchEntryResolver batchEntryResolver) {
        this.batchEntryResolver = batchEntryResolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountingEntry> process(List<FixedAsset> fixedAssets) throws Exception {

        log.trace("Creating entries for posting {} fixedAssets", fixedAssets);

        return batchEntryResolver.resolveEntries(fixedAssets);
    }
}
