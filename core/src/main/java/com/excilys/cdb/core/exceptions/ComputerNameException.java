package main.java.com.excilys.cdb.core.exceptions;

public class ComputerNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message = "Error: the computer's name must not be empty! Please try again. ";
	
	@Override
	public String getMessage() {
		return message;
	}
}
