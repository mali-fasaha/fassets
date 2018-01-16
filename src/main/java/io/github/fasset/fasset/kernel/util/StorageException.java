package io.github.fasset.fasset.kernel.util;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 1569324982377679466L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
