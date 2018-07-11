package io.github.fasset.fasset.kernel.util.queue.files;

import io.github.fasset.fasset.kernel.util.queue.MQException; /**
 * Exception is thrown while trying to read data from a qeue specifically concerning a FileUpload
 */
class FileMessageReadingException extends RuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    FileMessageReadingException() {

        super(String.format("Sorry, could no read the file from the queue"));
    }

    FileMessageReadingException(MQException e) {
        super("Sorry, could no read the file from the queue, see error message", e);
    }
}
