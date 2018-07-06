package io.github.fasset.fasset.kernel.util;

/**
 * <br> Jsonable Interface
 * <br> This interface is a small utility whose clients can convert their internals into
 * a JSON string.
 * <br> A simple call to {link this#toJson} will return a string with JSON formatted
 * string.
 */
public interface Jsonable {

    /**
     *
     * @return String representing the object notation of the client
     */
    String toJson();
}
