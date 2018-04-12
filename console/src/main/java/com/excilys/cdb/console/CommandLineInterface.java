package main.java.com.excilys.cdb.console;

/*import static com.excilys.cdb.core.constants.Constants.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.cdb.core.model.Computer;
import com.excilys.cdb.core.page.*;
import com.excilys.cdb.service.*;*/

public class CommandLineInterface {
	
	/*private CompanyService companyService;
	private ComputerService computerService;
	private static String computerIntroductionDate;
	private static String computerDiscontinuationDate;
	
	static final Logger LOGGER = LogManager.getLogger(CommandLineInterface.class);
	
	public CommandLineInterface() {	
	}
	
	public static void main(String[] args) throws SQLException {
		new CommandLineInterface().menuOptions();
	}

	public static void print(String intructions) {
		System.out.println(intructions);
	}

	public static void mainMenu() {
		print("************************************************");
		print("\n>>>>>>>>>>>>>>>>>> MAIN  MENU <<<<<<<<<<<<<<<<<<");
		print("\n************************************************");
		print("\n1 - Display computer's list ");
		print("\n2 - Display computer's list with pagination");
		print("\n3 - Display company's list ");
		print("\n4 - Display a specific computer (id required) ");
		print("\n5 - Create a new computer ");
		print("\n6 - Update an existing computer (id required)");
		print("\n7 - Delete an existing computer (id required) ");
		print("\n8 - Exit ");
		print("\n************************************************");
		print("\n>>>>>>>>>>>>>>>>>> MAIN  MENU <<<<<<<<<<<<<<<<<<");
		print("\n************************************************");
	}
	
	@SuppressWarnings(RESOURCE)
	public int userIntInput() {
		while(true) {
			Scanner sc =  new Scanner(System.in);
			if(sc.hasNextInt()) {
				int userChoice = sc.nextInt();
				return userChoice;
			} else
				print("\nWarning: the input must be a number! Please try again. ");
		}
	}
	
	@SuppressWarnings(RESOURCE)
	public String userStringInput() {
		Scanner sc =  new Scanner(System.in);
		String userStringInput = sc.nextLine();
		return userStringInput;
	}
	
	public void getAllComputers() {
		System.out.println(computerService.getAll());
	}
	
	private Object getAllComputersPaginated() {
		boolean keepGoing = true;
		int offset = 0;
		int limit=1;
		print("\nPlease enter the number of computers you want to display per page: ");
		limit = userIntInput();
		if(limit>=0 && limit<=500) {
			String nameFilter = null;
			int max = (int) computerService.getNbSearch(nameFilter);
			PageManager<Computer> pageManager = new PageManagerGetAll<Computer>(limit, max, (x,y) -> computerService.getPageByName(x, y, nameFilter));
			while (keepGoing) {

				print(pageManager.getPageData().toString());
				print("\nPage " + pageManager.getPage() + "/" + pageManager.getMaxPage());

				boolean first = true;
				boolean validInput = false;
				int choice = -1;
				TreeMap<Integer, Method> methods = new TreeMap<>();
				for (Method method : PageManager.class.getMethods()) {
					if (method.isAnnotationPresent(UserChoice.class))
						methods.put(method.getAnnotation(UserChoice.class).order(), method);					
				}

				while (!validInput && offset < max) {
					if (!first)
						print("\nInvalid input! Please enter a correct value. ");
					first = false;
					print("\nWhat do you want to do ? \n");
					int i = 1;
					for (Entry<Integer, Method> method : methods.entrySet()) {
						print(i++ + " - " + method.getValue().getAnnotation(UserChoice.class).name());
					}
					print((methods.size()+1) + " - Exit");
					try {
						choice = userIntInput();
						if (choice <= (methods.size()+1) && choice >= 1)
							validInput = true;
						else
							LOGGER.error("Invalid input ");
					} catch (InputMismatchException e) {
						LOGGER.error( "Invalid input : " + e.getMessage());
					}
				}
				if (choice == (methods.size()+1)) {
					keepGoing = false;
					break;
				} else {
					try {
						if (!(boolean) methods.get(choice).invoke(pageManager, new Object[0]))
							print("Request failure ");
						else
							print(pageManager.getPageData().toString());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						LOGGER.error("Error in method : " + e.getMessage());
						print("Request failure");
					}
				}
			}
		} else {
			print("\nInvalid input. you must enter a number between 1 and 500! Please try again. ");
		}
		return "\n";
	}
	
	public void getAllCompanies() {
		print("******[LIST OF ALL COMPANIES]******");
		companyService.getAll();
	}
	
	public Optional<Computer> getComputerById() throws SQLException  {
		while(true) {
			print("\nEnter the id (number) of the computer you are looking for (from the current computers list) : ");
			int computerToDisplayId = userIntInput();
			print("******[COMPUTER INFORMATIONS]******");
			Optional<Computer> existingComputer = computerService.findById(computerToDisplayId);
			if(existingComputer.isPresent()) {
				return existingComputer;
			} else {
				print("\nWarning: invalid input, the id should be a number! Please try again. ");
			}
		}
	}
	
	public boolean createComputer() {
		while(true) {
			print("\n1 - Type the name of the new computer: ");
			String createdComputerName = userStringInput();
			
			String[] computerIntroductionDateAndDiscontinuation = setComputerIntroductionAndDiscontinuationDate();
			String computerIntroductionDate = computerIntroductionDateAndDiscontinuation[0];
			String computerDiscontinuationDate = computerIntroductionDateAndDiscontinuation[1];
			LocalDate introductionDateYear = LocalDate.parse(computerIntroductionDate);
			LocalDate discontinuationDateYear = LocalDate.parse(computerDiscontinuationDate);
			if(discontinuationDateYear.getYear()<introductionDateYear.getYear()) {
				print("Warning: the discontinuation year you selected is prior to the introduction year! Please try again.");
				setComputerIntroductionAndDiscontinuationDate();
			}		
			Timestamp createdComputerIntroductionDate = convertStringToTimestamp(computerIntroductionDate);
			Timestamp createdComputerDiscontinuationDate = convertStringToTimestamp(computerIntroductionDate);
			
			print("\n4 - Update the company id (must be a number) of the new computer: ");
			int createdComputerCompanyId = userIntInput();
			
			Computer newComputer = new Computer(createdComputerName, createdComputerIntroductionDate, 
					createdComputerDiscontinuationDate, createdComputerCompanyId);
			
			try {
				computerService.create(newComputer);
				print("\nComputer successfully created! You can find below it's informations. ");
				print("\n" + newComputer.toString());
			} catch (Exception e) {
				LOGGER.error("Error creating the computer" + e.getMessage());
			}
		}
	}
	
	public boolean updateComputer() throws SQLException {
		while(true) {
			print("\nEnter the id (number) of the computer you want to update: ");
			int computerToUpdateId = userIntInput();
			Optional<Computer> existingComputer = computerService.findById(computerToUpdateId);
			if(existingComputer.isPresent()) {
				print("\n1 - Update the name of the new computer: ");
				String updatedComputerName = userStringInput();
				
				String[] computerIntroductionDateAndDiscontinuation = setComputerIntroductionAndDiscontinuationDate();
				String computerIntroductionDate = computerIntroductionDateAndDiscontinuation[0];
				String computerDiscontinuationDate = computerIntroductionDateAndDiscontinuation[1];
				LocalDate introductionDateYear = LocalDate.parse(computerIntroductionDate);
				LocalDate discontinuationDateYear = LocalDate.parse(computerDiscontinuationDate);
				if(discontinuationDateYear.getYear()<introductionDateYear.getYear()) {
					print("Warning: the discontinuation year you selected is prior to the introduction year! Please try again.");
					setComputerIntroductionAndDiscontinuationDate();
				}		
				Timestamp updatedComputerIntroductionDate = convertStringToTimestamp(computerIntroductionDate);
				Timestamp updatedComputerDiscontinuationDate = convertStringToTimestamp(computerIntroductionDate);
				
				print("\n4 - Update the company id (must be a number) of the new computer: ");
				int updatedComputerCompanyId = userIntInput();
				
				Computer newComputer = new Computer(updatedComputerName, updatedComputerIntroductionDate, 
						updatedComputerDiscontinuationDate, updatedComputerCompanyId);
				
				try {
					computerService.update(newComputer);
					print("\nComputer successfully updated! You can find below it's informations. ");
					print("\n" + newComputer.toString());
				} catch (Exception e) {
					LOGGER.error("Error updating the computer" + e.getMessage());
				}
			}
		}
	}
		
	public boolean deleteComputer() {
		while(true) {
			print("\nEnter the id (number) of the computer you want to remove (from the current computers list) : ");
			int computerToDeleteId = userIntInput();
			Computer existingComputer = new Computer();
			existingComputer.setId(computerToDeleteId);;
			
			try {
				computerService.delete(existingComputer);
				print("\nComputer successfully deleted! ");
			} catch (Exception e) {
				LOGGER.error("Error deleting the computer" + e.getMessage());
			}

		}
	}
	
	public String verifyYearInput(String yearInput) {
		while(true) {
			yearInput = userStringInput();
			int yearInteger = Integer.parseInt(yearInput);
			if(!yearInput.equals("")) {
				if(yearInteger != 0000 && yearInteger>1970) {
					return yearInput;
				} else {
					print("Warning: invalid input, the year must be later than 1970! Please try again. ");
				}
			} else {
				print("Warning: invalid input, the year must not be empty! Please try again. ");
			}
		}
	}

	public String verifyMonthInput(String monthInput) {
		while(true) {
			monthInput = userStringInput();
			int monthInteger = Integer.parseInt(monthInput);
			if(!monthInput.equals("")) {
				if(monthInteger != 00 && 1<=monthInteger && monthInteger<=12) {
					return monthInput;
				} else {
					print("Warning: invalid input for the month! Please try again. ");
				}
			} else {
				print("Warning: invalid input, the month must not be empty! Please try again. ");
			}
		}
	}
	
	public String verifyDayInput(String dayInput) {
		while(true) {
			dayInput = userStringInput();
			int dayInteger = Integer.parseInt(dayInput);
			if(!dayInput.equals("")) {
				if(dayInteger != 00 && 1<=dayInteger && dayInteger<=31) {
					return dayInput;
				} else {
					print("Warning: invalid input for the day! Please try again. ");
				}
			} else {
				print("Warning: the year should be later than 1970! Please try again. ");
			}
		}
	}
			
	public String[] setComputerIntroductionAndDiscontinuationDate() {
		print("\n2 - Set the introduction date of the new computer ");

		print("\nFirst, enter the year (format YYYY): ");
		String computerIntroductionYear = userStringInput();
		verifyYearInput(computerIntroductionYear);
		
		print("\nThen, enter the month (format MM): ");
		String computerIntroductionMonth = userStringInput();
		verifyMonthInput(computerIntroductionMonth);
		
		print("\nFinally, enter the day (format DD): ");
		String computerIntroductionDay = userStringInput();
		verifyDayInput(computerIntroductionDay);
		
		computerIntroductionDate = computerIntroductionYear + "-" + computerIntroductionMonth +"-" + computerIntroductionDay;
		
		while(true) {
			print("\n1 - Update the discontinuation date of the new computer (optional, must be later than the introduction date) \n");
			print("\n2 - Skip this step and move on to the Company's id update section ");
			int userChoice = userIntInput();
			switch(userChoice) {
			case 1:
				print("\nFirst, enter the year (format YYYY): ");
				String computerDiscontinuationYear = userStringInput();
				verifyYearInput(computerDiscontinuationYear);
				
				print("\nThen, enter the month (format MM): ");
				String computerDiscontinuationMonth = userStringInput();
				verifyMonthInput(computerDiscontinuationMonth);
				
				print("\nFinally, enter the day (format DD): ");
				String computerDiscontinuationDay = userStringInput();
				verifyDayInput(computerDiscontinuationDay);
				
				computerDiscontinuationDate = computerDiscontinuationYear + "-" + computerDiscontinuationMonth + "-" + computerDiscontinuationDay;
				return new String[] {computerIntroductionDate, computerDiscontinuationDate};
			case 2:
				computerDiscontinuationDate = null;
				return new String[] {computerIntroductionDate, computerDiscontinuationDate};
			default:
				print("Warning: invalid input, you mus enter either 1 or 2! Please try again.");
			}	
		}
		
	}
	
	public void goToMainMenu() throws SQLException  {
		print("\n1 : Main menu ");
		print("\n2 : Exit ");
		while(true) {
			int userInput = userIntInput();
			switch(userInput) {
			case 1:
				menuOptions();
				break;
			case 2:
				System.exit(0);
			default:
				print("\nInvalid input. you must enter either 1 or 2! Please try again. ");
			}
		}
	}
	
	public void getAnotherComputerByIdOrGoToMainMenu() throws SQLException {
		print("\n1 : Main menu ");
		print("\n2 : Display another computer's informations ");
		print("\n3 : Exit ");
		while(true) {
			int userInput = userIntInput();
			switch(userInput) {
			case 1:
				menuOptions();
				break;
			case 2:
				getComputerById();
				break;
			case 3:
				System.exit(0);
			default:
				print("\nInvalid input, you must enter either 1, 2 or 3! Please Try again. ");
			}
		}
	}
	
	public void createAnotherComputerOrGoToMainMenu() throws SQLException {	
		print("\n1 : Main menu ");
		print("\n2 : Create another computer ");
		print("\n3 : Exit ");
		int userInput = userIntInput();	
		switch(userInput) {
		case 1:
			menuOptions();
			break;
		case 2:
			createComputer();
			break;
		case 3:
			System.exit(0);
		default:
			print("\nInvalid input, you must enter either 1, 2 or 3! Please Try again. ");
			createAnotherComputerOrGoToMainMenu();
		}	
	}
	
	public void updateAnotherComputerOrGoToMainMenu() throws SQLException {
		print("\n1 : Main menu ");
		print("\n2 : Update another computer ");
		print("\n3 : Exit ");
		int userInput = userIntInput();
		switch(userInput) {
		case 1:
			menuOptions();
			break;
		case 2:
			updateComputer();
			break;
		case 3:
			System.exit(0);
		default:
			print("\nInvalid input, you must enter either 1, 2 or 3! Please Try again. ");
			updateAnotherComputerOrGoToMainMenu();
		}
	}
	
	public void deleteAnotherComputerOrGoToMainMenu() throws SQLException  {
		print("\n1 : Main menu ");
		print("\n2 : Delete another computer ");
		print("\n3 : Exit ");
		int userInput = userIntInput();
		switch(userInput) {
		case 1:
			menuOptions();
			break;
		case 2:
			deleteComputer();
			break;
		case 3:
			System.exit(0);
		default:
			print("\nInvalid input, you must enter either 1, 2 or 3! Please Try again. ");
			deleteAnotherComputerOrGoToMainMenu();
		}
	}
	
	public void createComputerResult() throws SQLException  {
		boolean createComputerResult = createComputer();
		if(createComputerResult == true) {
			createAnotherComputerOrGoToMainMenu();
		} else {
			print("\nError creating the computer! Please try again. ");
			createComputer();
		}
	}
	
	public void updateComputerResult() throws SQLException {
		boolean updateComputerResult = updateComputer();
		if(updateComputerResult == true) {
			updateAnotherComputerOrGoToMainMenu();
		} else {
			print("\nError updating the computer! Please try again. ");
			updateComputer();
		}
	}

	public void deleteComputerResult() throws SQLException {
		boolean deleteComputerResult = deleteComputer();
		if(deleteComputerResult == true) {
			print("\nComputer successfully deleted. ");
			deleteAnotherComputerOrGoToMainMenu();
		} else {
			print("\nError deleting the computer! Please try again. ");
			deleteComputer();
		}
	}

	public Timestamp convertStringToTimestamp(String date) {
    	
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        LocalDate introductionDateParsed = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        
        try {
        introductionDateParsed = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            LOGGER.error("Error converting the date into Timestamp : " + e.getMessage());
        }
        
        Timestamp timestamp = Timestamp.valueOf(introductionDateParsed.atStartOfDay());
        return timestamp;
    }
	
	public void menuOptions() throws SQLException {
		mainMenu();
		int userChoice = userIntInput();	
		do {
			switch(userChoice) {
			case 1:
				getAllComputers();
				goToMainMenu();
				break;
			case 2:
				getAllComputersPaginated();
				goToMainMenu();
				break;
			case 3:
				getAllCompanies();
				goToMainMenu();
				break;
			case 4:
				getComputerById();
				getAnotherComputerByIdOrGoToMainMenu();
				break;
			case 5:
				createComputerResult();
				break;
			case 6:
				updateComputerResult();
				break;
			case 7:
				deleteComputerResult();
				break;
			case 8:
				System.exit(0);
			default:
				print("Invalid input. Please try again! ");
				mainMenu();
			}
		} while(userChoice!=1 && userChoice!=2 && userChoice!=3 && userChoice!=4 && userChoice!=5 && userChoice!=6 && userChoice!=7);
	}*/		
	
}
