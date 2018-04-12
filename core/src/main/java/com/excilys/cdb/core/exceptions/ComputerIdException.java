package main.java.com.excilys.cdb.core.exceptions;

public class ComputerIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message = "Error: the computer's id must not be null! Please try again. ";
	
	@Override
	public String getMessage() {
		return message;
	}

}
