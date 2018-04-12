package main.java.com.excilys.cdb.service;

import java.util.List;
import java.util.Optional;

import main.java.com.excilys.cdb.core.model.Company;
import main.java.com.excilys.cdb.persistence.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

	private CompanyRepository companyRepository;
	
	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<Company> getAll() {
		return (List<Company>) companyRepository.findAll();
	}

	@Transactional 
	public void delete(Company company) {
		companyRepository.delete(company);
	}

	public Optional<Company> findById(int companyId) {
		return companyRepository.findById(companyId);
	}
	
}