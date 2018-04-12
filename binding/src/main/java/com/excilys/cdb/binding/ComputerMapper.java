package main.java.com.excilys.cdb.binding;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.sql.Timestamp;
import java.util.Optional;
import java.time.LocalDate;

import main.java.com.excilys.cdb.core.model.*;
import main.java.com.excilys.cdb.binding.ComputerDTO;

import org.springframework.stereotype.Component;

@Component
public class ComputerMapper {

	private final CompanyMapper companyMapper;

	public ComputerMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;
	}

	public ComputerDTO computerToDTO(Computer computer) {
		
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setComputerId(computer.getId());
		computerDTO.setComputerName(computer.getName());
		
		Optional<Timestamp> introductionDate = Optional.ofNullable(computer.getIntroductionDate());
		Optional<Timestamp> discontinuationDate = Optional.ofNullable(computer.getDiscontinuationDate());
		
		if (introductionDate.isPresent()) {
			computerDTO.setComputerIntroductionDate(String.valueOf(computer.getIntroductionDate()));
		} else {
			computerDTO.setComputerIntroductionDate(NULL_STRING);
		}
		
		if (discontinuationDate.isPresent()) {
			computerDTO.setComputerDiscontinuationDate(String.valueOf(computer.getDiscontinuationDate()));
		} else {
			computerDTO.setComputerDiscontinuationDate(NULL_STRING);
		}
		
		if (computer.getCompany().getId() != ZERO) {
			computerDTO.setCompanyId(String.valueOf(computer.getCompany().getId()));
		} else {
			computerDTO.setCompanyId("0");
		}
		
		computerDTO.setCompanyName(computer.getCompany().getName());
		
		return computerDTO;
	}

	public Computer dtoToComputer(ComputerDTO computerDTO) {
		
		Computer computer = new Computer();

		computer.setName(computerDTO.getComputerName());
		
		if(computerDTO.getComputerIntroductionDate() != NULL_STRING && !computerDTO.getComputerIntroductionDate().trim().isEmpty() && !computerDTO.getComputerIntroductionDate().trim().equals(SPACE_STRING)) {
			computer.setIntroductionDate(Timestamp.valueOf(LocalDate.parse(computerDTO.getComputerIntroductionDate()).atStartOfDay()));
		} else {
			computer.setIntroductionDate(null);
		}
		
		if(computerDTO.getComputerDiscontinuationDate() != NULL_STRING && !computerDTO.getComputerDiscontinuationDate().trim().isEmpty() && !computerDTO.getComputerDiscontinuationDate().trim().equals(SPACE_STRING)) {
			computer.setDiscontinuationDate(Timestamp.valueOf(LocalDate.parse(computerDTO.getComputerDiscontinuationDate()).atStartOfDay()));
		} else {
			computer.setDiscontinuationDate(null);
		}
		
		if(computerDTO.getCompanyId() == NULL_STRING) {
			computer.setCompanyId((int) 0);
		} else {
			computer.setCompanyId(Integer.parseInt(computerDTO.getCompanyId()));
		}
			
		computer.setCompany(companyMapper.dtoToCompany(computerDTO.getCompanyDTO()));
		
		return computer;
	}
	
}