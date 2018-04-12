package main.java.com.excilys.cdb.persistence;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import main.java.com.excilys.cdb.core.model.Company;
import main.java.com.excilys.cdb.core.model.Computer;

@Component
public class ComputerRowMapper implements RowMapper<Computer> {
	
    @Override
    public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Company company = new Company(resultSet.getInt(COMPUTER_COMPANY_ID_FIELD), resultSet.getString(COMPANY_NAME_FIELD));
        Computer computer = new Computer(resultSet.getInt(COMPUTER_ID_FIELD), resultSet.getString(COMPUTER_NAME_FIELD), resultSet.getTimestamp(COMPUTER_INTRODUCTION_DATE_FIELD),
                resultSet.getTimestamp(COMPUTER_DISCONTINUATION_DATE_FIELD), company);
        return computer;
    }
    
}