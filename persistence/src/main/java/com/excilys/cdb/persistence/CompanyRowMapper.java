package main.java.com.excilys.cdb.persistence;

import main.java.com.excilys.cdb.core.model.Company;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CompanyRowMapper implements RowMapper<Company> {
	
    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Company(rs.getInt(COMPANY_ID_FIELD), rs.getString(COMPANY_NAME_FIELD));
    }
    
}