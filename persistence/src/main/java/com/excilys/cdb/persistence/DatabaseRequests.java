package main.java.com.excilys.cdb.persistence;

public class DatabaseRequests {

	public static final String QUERY_GET_ALL_COMPUTERS = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id";
	public static final String QUERY_GET_ALL_COMPUTERS_PAGINED = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id LIMIT ?,?";
	public static final String QUERY_FIND_COMPUTER_BY_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.id = ?";
	public static final String QUERY_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	public static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	public static final String QUERY_DELETE_COMPUTER_BY_COMPANY_ID = "DELETE FROM computer WHERE company_id = ?";
	public static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	public static final String QUERY_GET_NUMBER_OF_COMPUTERS = "SELECT count(*) FROM computer";
	
	public static final String QUERY_GET_ENTITIES_BY_NAME = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? LIMIT ? , ?";
	public static final String QUERY_GET_NUMBER_OF_ENTITIES_BY_NAME = "SELECT count(*) FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";

	public static final String QUERY_GET_ALL_COMPANIES = "SELECT id, name FROM company";
	public static final String QUERY_FIND_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	public static final String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	
}