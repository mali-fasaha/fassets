package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;
import io.github.fasset.fasset.kernel.batch.depreciation.model.DepreciationUpdate;

import java.util.List;

/**
 * Sends the proceeds of depreciation update for further processing in other batches
 *
 * @author edwin.njeru
 */
public interface DepreciationUpdateDispatcher<T> {

    void send(UpdateProvider<T> updateMessage, Colleague originator);

    void addColleague(Colleague colleague);

    List<Colleague<DepreciationUpdate>> getColleagues();
}
