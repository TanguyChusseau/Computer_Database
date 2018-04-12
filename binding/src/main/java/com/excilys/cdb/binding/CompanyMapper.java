package main.java.com.excilys.cdb.binding;

import main.java.com.excilys.cdb.core.model.Company;
import main.java.com.excilys.cdb.binding.CompanyDTO;

import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

	public CompanyMapper() {

	}
	
	public Company dtoToCompany(CompanyDTO companyDTO) {
		return new Company(companyDTO.getCompanyId(), companyDTO.getCompanyName());
	}
	
	public CompanyDTO companyToDTO(Company company) {
		
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.companyId = company.getId();
		companyDTO.companyName = company.getName();
		return companyDTO;
		
	}
}