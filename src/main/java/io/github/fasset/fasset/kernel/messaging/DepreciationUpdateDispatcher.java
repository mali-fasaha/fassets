package io.github.fasset.fasset.kernel.messaging;

import io.github.fasset.fasset.kernel.batch.depreciation.agent.UpdateProvider;
import io.github.fasset.fasset.kernel.batch.depreciation.colleague.Colleague;

import java.util.List;

/**
 * Sends the proceeds of depreciation update for further processing in other batches
 *
 * @author edwin.njeru
 */
public interface DepreciationUpdateDispatcher {

    /**
     * Sends the update provider for the Depreciation Update Dto where
     * parameter T is the type of the Dto
     *
     * @param updateProvider Provider item being sent
     * @param originator the colleague generating the information
     *
     */
    void send(UpdateProvider updateProvider, Colleague originator);

    /**
     * Adds to a collection a Colleague typed with the DTO it is expected to send
     *
     * @param colleague the Colleague supposed to send the DTO
     */
    void addColleague(Colleague colleague);

    /**
     *
     * @return A list of all the Colleagues
     */
    List<Colleague> getColleagues();
}
