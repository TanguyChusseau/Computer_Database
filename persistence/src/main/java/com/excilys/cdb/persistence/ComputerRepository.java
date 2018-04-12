package main.java.com.excilys.cdb.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import main.java.com.excilys.cdb.core.model.Computer;

@Repository
public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {

	Long countByNameContaining(String name);
	
	Page<Computer> findByNameContaining(Pageable p, String name);	
}
