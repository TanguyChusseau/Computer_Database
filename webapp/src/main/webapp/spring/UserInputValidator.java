package main.webapp.spring;

import static main.java.com.excilys.cdb.core.errors.Errors.*;
import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.function.Function;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import main.java.com.excilys.cdb.core.exceptions.*;

@Component
public class UserInputValidator {

	static final Logger LOGGER = LogManager.getLogger(UserInputValidator.class);
	
	public UserInputValidator() {
		
	}
	
	public void computerIdValidation(int computerId) throws ComputerIdException {
		if(computerId <= 0 || String.valueOf(computerId).trim() == NULL_STRING) {
			LOGGER.error(COMPUTER_ID_NULL_ERROR);
			throw new ComputerIdException();
		}
	}
	
	public void nameInputValidation(String nameInput) throws ComputerNameException {
		if(nameInput.trim().length() == ZERO) {
			LOGGER.error(COMPUTER_NAME_EMPTY_ERROR);
			throw new ComputerNameException();
		}
	}
	
	public boolean dateInputIsNotEmpty(String dateInput) {
		boolean dateCheck = true;
		if(dateInput == null || dateInput.trim().isEmpty()){
			dateCheck = false;
		}
		return dateCheck;
	}
	
	public void dateInputValidation(String introductionDateInput, String discontinuationDateInput) throws IntroductionDateException, DiscontinuationDateException {
		
		boolean introductionDate = dateInputIsNotEmpty(introductionDateInput);
		boolean discontinuationDate = dateInputIsNotEmpty(discontinuationDateInput);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		if(introductionDate) {
			
			if(discontinuationDate) {
				
				LocalDate introductionDateParsed = LocalDate.parse(introductionDateInput, formatter);
				LocalDate discontinuationDateParsed = LocalDate.parse(discontinuationDateInput, formatter);
				
				if(introductionDateParsed.getYear() < 1970) {
					LOGGER.error(COMPUTER_INTRODUCTION_DATE_PRIOR_TO_1970_ERROR);
					throw new IntroductionDateException(COMPUTER_INTRODUCTION_DATE_PRIOR_TO_1970_ERROR);
				}
				
				if(discontinuationDateParsed.getYear() < 1970) {
					LOGGER.error(COMPUTER_DISCONTINUATION_DATE_PRIOR_TO_1970_ERROR);
					throw new DiscontinuationDateException(COMPUTER_DISCONTINUATION_DATE_PRIOR_TO_1970_ERROR);
				} else if(discontinuationDateParsed.isBefore(introductionDateParsed)) {
					LOGGER.error(COMPUTER_DISCONTINUATION_DATE_PRIOR_TO_INTRODUCTION_DATE_ERROR);
					throw new DiscontinuationDateException(COMPUTER_DISCONTINUATION_DATE_PRIOR_TO_INTRODUCTION_DATE_ERROR);
				}
			} 
			
		} else {
			
			LOGGER.error(COMPUTER_INTRODUCTION_DATE_NULL_ERROR);
			throw new IntroductionDateException("Warning: the introduction date must not be empty! Please try again. ");
		}
	}
	
	public int companyIdSelectionValidation(String companyIdSelection) throws CompanyIdException{
		
		int companyId;
		if(companyIdSelection.equals("--")) {
			companyId = ZERO;
		} else {
			companyId = Integer.parseInt(companyIdSelection);
		}
		return companyId;
	}
	
	public void createInputValidation(String nameInput, String introductionDateInput, String discontinuationDateInput, String companyIdSelection) throws ComputerNameException, IntroductionDateException, DiscontinuationDateException, CompanyIdException {
		nameInputValidation(nameInput);
		dateInputValidation(introductionDateInput, discontinuationDateInput);
		companyIdSelectionValidation(companyIdSelection);
	}
	
	
	public void updateInputValidation(int computerId, String nameInput, String introductionDateInput, String discontinuationDateInput, String companyIdSelection) throws ComputerIdException, ComputerNameException, IntroductionDateException, DiscontinuationDateException, CompanyIdException {
		computerIdValidation(computerId);
		nameInputValidation(nameInput);
		dateInputValidation(introductionDateInput, discontinuationDateInput);
		companyIdSelectionValidation(companyIdSelection);
	}
	
	
	public Map<String, String> createComputerValidation(String computerName, String computerIntroductionDate, String computerDiscontinuationDate, String companyId) {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		try {
			
			createInputValidation(computerName, computerIntroductionDate, computerDiscontinuationDate, companyId);

		} catch (ComputerNameException e) {
			errors.put(computerName, e.getMessage());

		} catch (IntroductionDateException e) {
			errors.put(computerIntroductionDate, e.getMessage());

		} catch (DiscontinuationDateException e) {
			errors.put(computerDiscontinuationDate, e.getMessage());

		} catch (CompanyIdException e) {
			errors.put(companyId, e.getMessage());

		}

		return errors;
	}
	
	
	public Map<String, String> updateComputerValidation(int computerId, String computerName, String computerIntroductionDate, String computerDiscontinuationDate, String companyId) {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		try {
			updateInputValidation(computerId, computerName, computerIntroductionDate, computerDiscontinuationDate, companyId);

		} catch (ComputerIdException e) {
			errors.put(computerName, e.getMessage());
			
		} catch (ComputerNameException e) {
			errors.put(computerName, e.getMessage());

		} catch (IntroductionDateException e) {
			errors.put(computerIntroductionDate, e.getMessage());

		} catch (DiscontinuationDateException e) {
			errors.put(computerDiscontinuationDate, e.getMessage());

		} catch (CompanyIdException e) {
			errors.put(companyId, e.getMessage());

		}

		return errors;
	}
	
	public <T, E extends Exception> void isCorrectString(String s, ExceptionFunction<String, T, E> parse, 
			boolean nullOk, boolean emptyOk, List<Function<T, Boolean>> tests) throws InvalidInputException, E {

		if (s == null) {
			if (!nullOk) {
				throw new InvalidInputException("- null value");
			}
		} else if (s.trim().equals("")) {
			if (!emptyOk) {
				throw new InvalidInputException("- empty value");
			}
		} else {

			T parsed = null;

			parsed = parse.apply(s);

			if (parsed == null) {
				if (!nullOk) {
					throw new InvalidInputException("- null value");
				}
			} else {

				for (Function<T, Boolean> test : tests) {
					if (!test.apply(parsed)) {
						throw new InvalidInputException("- incorrect value");
					}
				}

			}
		}
	}
	
}
