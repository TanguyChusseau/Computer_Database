package main.java.com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.com.excilys.cdb.core.model.Computer;
import main.java.com.excilys.cdb.persistence.ComputerRepository;


@Service
public class ComputerService {

	private final ComputerRepository computerRepository;

	@Autowired
	public ComputerService(ComputerRepository computerRepositoryImpl) {
		this.computerRepository = computerRepositoryImpl;
	}

	public List<Computer> getAll() {
		return (List<Computer>) computerRepository.findAll();
	}

	public Page<Computer> getPage(int numPage, int nbElement) {
		Page<Computer> page = computerRepository.findAll(PageRequest.of(numPage, nbElement));
		return page;
	}

	public Page<Computer> getPageByName(int numPage, int nbElementPage, String name) {
		Page<Computer> page = computerRepository.findByNameContaining(PageRequest.of(numPage,nbElementPage),name);
		return page;
	}

	public long getNbSearch(String name) {
		long count =computerRepository.countByNameContaining(name);
		return count;
	}

	public Page<Computer> getPageByOrder(int numPage, int nbElementPage, Direction direction) {
		Page<Computer> page = computerRepository.findAll(PageRequest.of(numPage, nbElementPage, direction));
		return page;
	}

	public int getNbTotal() {
		return (int) computerRepository.count();
	}

	public void create(Computer computer) {
		computerRepository.save(computer);
	}

	@Transactional
	public void update(Computer computer) {
		computerRepository.save(computer);
	}

	public void delete(Computer computer) {
		computerRepository.delete(computer);
	}

	public Optional<Computer> findById(int id) throws SQLException {
		return computerRepository.findById((long)id);
	}
	
}