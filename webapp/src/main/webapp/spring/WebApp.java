package main.webapp.spring;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.com.excilys.cdb.binding.*;
import main.java.com.excilys.cdb.core.model.*;
import main.java.com.excilys.cdb.service.*;

import org.springframework.stereotype.Component;

@Component
public class WebApp {

	private ComputerDTO selectedComputer;
	private final CompanyMapper companyMapper;
	private final ComputerMapper computerMapper;
	private final CompanyService companyService;
	private final ComputerService computerService;

	public WebApp(CompanyMapper companyMapper, ComputerMapper computerMapper, CompanyService companyService, ComputerService computerService) {
		this.companyMapper = companyMapper;
		this.computerMapper = computerMapper;
		this.companyService = companyService;
		this.computerService = computerService;
	}

	public void setSelectedComputer(ComputerDTO selectedComputer) {
		this.selectedComputer = selectedComputer;
	}

	public ComputerDTO getSelectedComputer() {
		return selectedComputer;
	}

	public List<ComputerDTO> getComputersList() {

		List<ComputerDTO> computersListDTO = new ArrayList<ComputerDTO>();
		for(Computer computer: computerService.getAll()) {
			ComputerDTO computerDTO = computerMapper.computerToDTO(computer);
			computersListDTO.add(computerDTO);
		}
		return computersListDTO;
	}

	public List<ComputerDTO> getComputersListPaginated(long offset, long limit) {

		List<ComputerDTO> computersListDTO = new ArrayList<ComputerDTO>();
		for(Computer computer: computerService.getAll()) {
			ComputerDTO computerDTO = computerMapper.computerToDTO(computer);
			computersListDTO.add(computerDTO);
		}
		return computersListDTO;
	}

	public List<CompanyDTO> getCompaniesList() {

		List<CompanyDTO> companiesListDTO = new ArrayList<CompanyDTO>();
		for(Company company: companyService.getAll()) {
			CompanyDTO companyDTO = companyMapper.companyToDTO(company);
			companiesListDTO.add(companyDTO);
		}
		return companiesListDTO;
	}

	public ComputerDTO getComputerDTO(int computerId) throws SQLException {
		setSelectedComputer(computerMapper.computerToDTO(computerService.findById(computerId).orElse(new Computer())));
		return selectedComputer;
	}

	public Computer createComputer(String computerName, String introductionDate, String discontinuationDate, int companyId) {

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setComputerName(computerName);
		computerDTO.setComputerIntroductionDate(introductionDate);
		computerDTO.setComputerDiscontinuationDate(discontinuationDate);
		computerDTO.setCompanyDTO(companyMapper.companyToDTO((companyService.findById(companyId).orElse(new Company()))));
		Computer computer = computerMapper.dtoToComputer(computerDTO);
		return computer;
	}

	public Computer updateComputer(int computerId, String computerName, String introductionDate, String discontinuationDate, int companyId) {

		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setComputerId(computerId);
		computerDTO.setComputerName(computerName);
		computerDTO.setComputerIntroductionDate(introductionDate);
		computerDTO.setComputerDiscontinuationDate(discontinuationDate);
		computerDTO.setCompanyDTO(companyMapper.companyToDTO((companyService.findById(companyId).orElse(new Company()))));
		Computer computer = computerMapper.dtoToComputer(computerDTO);
		return computer;
	}

	public void deleteComputerDTO(Computer computer) {
		computerService.delete(computer);
	}

}
