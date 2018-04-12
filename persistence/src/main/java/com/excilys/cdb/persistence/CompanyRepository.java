package main.java.com.excilys.cdb.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import main.java.com.excilys.cdb.core.model.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {

}
