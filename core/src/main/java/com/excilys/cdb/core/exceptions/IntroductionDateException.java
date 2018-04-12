package main.java.com.excilys.cdb.core.exceptions;

public class IntroductionDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IntroductionDateException(String message) {
		super(message);
	}

    public IntroductionDateException(String message, Throwable cause) {
        super(message, cause);
    }

}
