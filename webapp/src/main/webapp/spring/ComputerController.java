package main.webapp.spring;

import static main.java.com.excilys.cdb.core.errors.Errors.*;
import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.util.Map;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.com.excilys.cdb.core.page.*;
import main.java.com.excilys.cdb.core.model.Computer;

import main.java.com.excilys.cdb.binding.*;
import main.java.com.excilys.cdb.service.ComputerService;
import main.webapp.spring.UserInputValidator;
import main.java.com.excilys.cdb.core.exceptions.InvalidInputException;

@Controller
public class ComputerController {
	
	private final WebApp webApp;
	private final CompanyMapper companyMapper;
	private final ComputerMapper computerMapper;
	private final ComputerService computerService;
	private final UserInputValidator userInputValidator;
	static final Logger LOGGER = LogManager.getLogger(ComputerController.class);
	
	public ComputerController (WebApp webApp, CompanyMapper companyMapper, ComputerMapper computerMapper, ComputerService computerService, UserInputValidator userInputValidator) {
		this.webApp = webApp;
		this.companyMapper = companyMapper;
		this.computerMapper = computerMapper;
		this.computerService = computerService;
		this.userInputValidator = userInputValidator;
	}
	
	@GetMapping(DASHBOARD)
	public String getDashboard(ModelMap model, @RequestParam Map<String, String> params) {
		
		int currentPage = 1;
		int numberOfComputers;
		
		final PageData<ComputerDTO> pageData = new PageData<>();
		PageManagerFilterByName<Computer> pageManagerFilterByName = null;
		
		String searchField;
		String limitString = params.get(LIMIT);
		String filterString = params.get(COMPUTER_FILTERED_BY_NAME);
		
		searchField = (filterString == null || filterString.equals(EMPTY_STRING)) ? EMPTY_STRING : filterString;
		
		List<Function<Long, Boolean>> limitTests = new ArrayList<>();
		limitTests.add(limit -> (limit > 0));
		
		try {
			userInputValidator.isCorrectString(limitString, toParse -> Long.parseLong(toParse), true, false, limitTests);
		} catch (InvalidInputException | NumberFormatException e) {
			LOGGER.error(e.getClass().getName() + LIMIT_VALUE_ERROR + e.getMessage());
		}
		
		int limit = 10;
		limit = limitString == null ? limit : Integer.parseInt(limitString);

		String pageString = params.get(PAGE);
		List<Function<Long, Boolean>> pageTests = new ArrayList<>();
		
		try {
			userInputValidator.isCorrectString(pageString, toParse -> Long.parseLong(toParse), true, false, pageTests);
		} catch (InvalidInputException | NumberFormatException e) {
			LOGGER.error(e.getClass().getName() + PAGE_VALUE_ERROR + e.getMessage());
		}
		
		currentPage = pageString == null ? currentPage : Integer.parseInt(pageString);		
		numberOfComputers = (int) computerService.getNbSearch(searchField);
		
		pageManagerFilterByName = new PageManagerFilterByName<Computer>(limit, numberOfComputers, (Integer x, Integer y, String s) -> computerService.getPageByName(x, y, searchField));
		pageManagerFilterByName.goTo(currentPage);
		pageManagerFilterByName.setFilterField(searchField);
		pageManagerFilterByName.getPageData().forEach(computer -> pageData.getDataList().add(computerMapper.computerToDTO((Computer) computer)));
		
		pageData.setNumberOfComputers(numberOfComputers);
		pageData.setLimit(pageManagerFilterByName.getLimit());
		pageData.setMaxPage(pageManagerFilterByName.getMaxPage());
		pageData.setCurrentPage(pageManagerFilterByName.getPage());
		pageData.setSearch(pageManagerFilterByName.getFilterField());
		
		model.addAttribute(PAGE_DATA, pageData);
		model.addAttribute(COMPUTER_FILTERED_BY_NAME, searchField);
		return DASHBOARD;
	}

	@PostMapping(DASHBOARD)
	protected String deleteComputer(ModelMap model, @RequestParam Map<String, String> params) {
		for (String computersIdString : params.get(SELECTED_COMPUTERS).split(COMMA_SEPARATOR)) {
			int computersIds = Integer.parseInt(computersIdString);
			Computer computer = new Computer();
			computer.setId(computersIds);
			webApp.deleteComputerDTO(computer);
		}
		return DASHBOARD;
	}
	
	@GetMapping(CREATE_COMPUTER)
	public String getCreate(ModelMap model) {
		List<CompanyDTO> companiesListDTO = webApp.getCompaniesList();
		model.addAttribute(COMPANIES_LIST, companiesListDTO);
		return CREATE_COMPUTER;
	}

	@PostMapping(CREATE_COMPUTER)
	public String createComputer(ModelMap model, @RequestParam Map<String, String> params) {	
		String name = params.get(COMPUTER_NAME);
		String introductionDate = params.get(COMPUTER_INTRODUCTION_DATE), discontinuationDate = params.get(COMPUTER_DISCONTINUATION_DATE);
		String companyId = params.get(COMPUTER_COMPANY_ID);
		
		Map<String, String> computerCreationErrors = userInputValidator.createComputerValidation(name, 
				introductionDate, discontinuationDate, companyId);
		
		if(computerCreationErrors.isEmpty()) {
			Computer createdComputer = webApp.createComputer(name, introductionDate, discontinuationDate, Integer.parseInt(companyId));
			computerService.create(createdComputer);
			return DASHBOARD_REDIRECT;
		} else {		
			model.addAttribute(CREATE_COMPUTER_ERRORS, computerCreationErrors);
			return CREATE_COMPUTER;
		}
	}
	
	@GetMapping(UPDATE_COMPUTER)
	public String getUpdate(Model model,  @RequestParam Map<String, String> params) {
		List<CompanyDTO> companiesListDTO = webApp.getCompaniesList();
		model.addAttribute(COMPANIES_LIST, companiesListDTO);
		
		String computerIdInput = params.get(COMPUTER_ID);
		ComputerDTO selectedComputer = null;
		try {
			selectedComputer = webApp.getComputerDTO(Integer.parseInt(computerIdInput));
		} catch (NumberFormatException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		model.addAttribute(SELECTED_COMPUTER, selectedComputer);
	
		return UPDATE_COMPUTER;
	}

	@PostMapping(UPDATE_COMPUTER)
	public String updateComputer(ModelMap model, @RequestParam Map<String, String> params) {		
		String id = params.get(COMPUTER_ID), name = params.get(COMPUTER_NAME);
		String introductionDate = params.get(COMPUTER_INTRODUCTION_DATE), discontinuationDate = params.get(COMPUTER_DISCONTINUATION_DATE);
		String companyId = params.get(COMPUTER_COMPANY_ID);
		
		Map<String, String> computerCreationErrors = userInputValidator.updateComputerValidation(Integer.parseInt(id), name, 
				introductionDate, discontinuationDate, companyId);
		
		if(computerCreationErrors.isEmpty()) {
			Computer updatedComputer = webApp.updateComputer(Integer.parseInt(id), name, introductionDate, discontinuationDate, Integer.parseInt(companyId));
			computerService.update(updatedComputer);
			return DASHBOARD_REDIRECT;
		} else {		
			model.addAttribute(CREATE_COMPUTER_ERRORS, computerCreationErrors);
			return UPDATE_COMPUTER;
		}
	}

}
