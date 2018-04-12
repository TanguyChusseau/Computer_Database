package main.java.com.excilys.cdb.core.exceptions;

public class CompanyIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message = "Error: the computer's company id must not be null! Please try again. ";
	
	@Override
	public String getMessage() {
		return message;
	}

}
