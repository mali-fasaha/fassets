package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Update;
import io.github.fasset.fasset.kernel.messaging.dto.AccruedDepreciationDto;
import io.github.fasset.fasset.kernel.messaging.dto.FixedAssetDto;
import io.github.fasset.fasset.kernel.messaging.dto.NetBookValueDto;

/**
 * Sends the proceeds of depreciation update for further processing in other batches
 *
 * @author edwin.njeru
 */
public interface DepreciationUpdateDispatcher {

    void send(Update updateMessage, Colleague originator);

    void addColleague(Colleague colleague);
}
