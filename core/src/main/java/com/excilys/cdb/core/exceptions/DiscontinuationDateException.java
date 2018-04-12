package main.java.com.excilys.cdb.core.exceptions;

public class DiscontinuationDateException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiscontinuationDateException(String message) {
		super(message);
	}

    public DiscontinuationDateException(String message, Throwable cause) {
        super(message, cause);
    }

}
