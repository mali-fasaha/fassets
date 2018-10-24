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
package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.kernel.util.ConcurrentList;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import io.github.fasset.fasset.kernel.util.queue.MQException;
import io.github.fasset.fasset.kernel.util.queue.MessageConsumer;
import io.github.fasset.fasset.kernel.util.queue.QueueMessage;
import io.github.fasset.fasset.kernel.util.queue.util.OnCompletion;
import io.github.fasset.fasset.kernel.util.queue.util.OnError;
import io.github.fasset.fasset.model.files.FileUpload;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * This is a sample implementation of the {@code MessageConsumer} whose implementation will emit a List of files which have not yet been deserialised. <br> The emitted list contains an 'immutalised'
 * fast list which is wrapped in a {@link io.github.fasset.fasset.kernel.util.ConcurrentList} which is basically a list which is "mostly" backed by a {@link java.util.concurrent.ConcurrentHashMap}
 * whose index is an internally perpetrated integer, also used as the HashMap's key. <br> This message is intended to be used by a scheduler, once set up will continue checking for messages on
 * FileUploads during the entire run of the application. <br> In event of there being a message a batch processing protocol is triggered to consume the messages and the messages (in this case)
 * FileUploads are marked as 'Deserialised', meaning that whatever data was in the file has been uploaded in to the server and all expected batch methods and processes against it have ran. <br> As
 * this particular procedure or chain of procedures could take time it is expected that customer facing interfaces will have taken back control of the servers and the controllers will have returned a
 * response for the filing request.
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Component("fileUploadsConsumer")
public class FileUploadsConsumer implements MessageConsumer<List<FileUpload>> {


    private final FileUploadService fileUploadService;

    /**
     * <p>Constructor for FileUploadsConsumer.</p>
     *
     * @param fileUploadService a {@link io.github.fasset.fasset.kernel.util.queue.files.FileUploadService} object.
     */
    @Autowired
    public FileUploadsConsumer(@Qualifier("fileUploadService") FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method checks for messages from the queue once it has been subscribed to as shown: <br>
     * <pre>
     *         {@code
     *         T message = checkMessages(
     *             (e) -> log.error("could not retrieve message due to : {}",e),
     *             () -> log.debug("message has been retrieved from the queue"))
     *                  .subscribe() // Retrieve Optional from Queue
     *                  .getOrElse(new T) // retrieve QueueMessage from Optional
     *                  .message(); // retrieve message
     *         }
     *     </pre>
     */
    @Override
    public Observable<Optional<QueueMessage<List<FileUpload>>>> checkMessages(OnError onError, OnCompletion completion) {

        List<FileUpload> uploads = null;

        try {
            uploads = fileUploadService.uploadedFiles().stream().filter(i -> !i.isDeserialized()).collect(ImmutableListCollector.toImmutableFastList());
        } catch (MQException e) {

            // call error event handler
            onError.handleError(e);
        }

        final List<FileUpload> finalUploads = ConcurrentList.of(uploads);

        // call completion lifecycle
        completion.handleCompletion();

        return Observable.just(Optional.of((QueueMessage<List<FileUpload>>) () -> finalUploads));
    }
}
