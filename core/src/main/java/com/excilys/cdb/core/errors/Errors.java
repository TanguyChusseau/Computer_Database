package main.java.com.excilys.cdb.core.errors;

public class Errors {
	
	//Computer fields errors
	public static final String COMPUTER_ID_NULL_ERROR = "Error, the computer's id must be a positive number. ";
	public static final String COMPUTER_NAME_EMPTY_ERROR = "Error, the computer's name must not be empty. ";
	public static final String COMPUTER_INTRODUCTION_DATE_NULL_ERROR = "Error, the computer's introduction date must not be null. ";
	public static final String COMPUTER_INTRODUCTION_DATE_PRIOR_TO_1970_ERROR = "Error, the computer's introduction date must be later than 1970. ";
	public static final String COMPUTER_DISCONTINUATION_DATE_PRIOR_TO_1970_ERROR = "Error, the computer's discontinuattion date must be later than 1970. ";
	public static final String COMPUTER_DISCONTINUATION_DATE_PRIOR_TO_INTRODUCTION_DATE_ERROR = "Error, the computer's discontinuation date must be later than the introduction date. ";
	public static final String LIMIT_VALUE_ERROR = " : Invalid limit value. ";
	public static final String PAGE_VALUE_ERROR = " : Invalid page value.  ";

	//Database errors
	public static final String DATABASE_QUERY_ERROR = "Error requesting the database:  ";
	public static final String DATABASE_OPEN_CONNECTION_ERROR = "Error connecting to the database:  ";
	public static final String DATABASE_CLOSE_CONNECTION_ERROR = "Error closing the connection to the database:  ";

	//Controller Errors
	public static final String CREATE_COMPUTER_ERRORS = "createComputerErrors";
	public static final String UPDATE_COMPUTER_ERRORS = "updateComputerErrors";
	
}
