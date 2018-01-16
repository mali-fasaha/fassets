package io.github.fasset.fasset.kernel.util;

public class StorageFileNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832798591585236987L;

	public StorageFileNotFoundException() {
		super();
	}

	public StorageFileNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
	
}
