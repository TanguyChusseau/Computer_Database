package main.java.com.excilys.cdb.core.constants;

public class Constants {
		
	//Hibernate properties
	public static final String HIBERNATE_PROPERTIES = "classpath:hibernate.properties";
	public static final String DATABASE_DRIVER = "driver";
	public static final String DATABASE_URL = "url";
	public static final String DATABASE_LOGIN = "login";
	public static final String DATABASE_PASSWORD = "password";
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_PACKAGES_TO_SCAN = "entitymanager.packagesToScan";
	
	//Computer fields
	public static final String COMPUTER = "computer";
	public static final String COMPUTER_ID_FIELD = "computer.id";
	public static final String ID = "id";
	public static final String COMPUTER_NAME_FIELD = "computer.name";
	public static final String NAME = "name";
	public static final String INTRODUCTION_DATE = "introduced";
	public static final String DISCONTINUATION_DATE = "discontinued";
	public static final String COMPUTER_COMPANY_ID_FIELD = "company_id";
	
	//Company fields
	public static final String COMPANY = "company";
	public static final String COMPANY_ID_FIELD = "company.id";
	public static final String COMPANY_NAME_FIELD = "company.name";

	//Generic fields
	public static final String COUNT = "count(*)";

	//Paths
	public static final String BASE_PATH = "main.java.com.excilys.cdb";
	public static final String CSS_PATH = "/css/";
	public static final String CSS_FILES = "/css/**";
	public static final String JS_PATH = "/css/";
	public static final String JS_FILES = "/js/**";
		
	//Controller
	public static final String DASHBOARD = "dashboard";
	public static final String DASHBOARD_REDIRECT = "redirect:dashboard";	
	public static final String CREATE_COMPUTER = "/createComputer";	
	public static final String UPDATE_COMPUTER = "/updateComputer";
	public static final String SELECTED_COMPUTER = "selectedComputer";
	public static final String SELECTED_COMPUTERS = "selection";
	
	//Views
	public static final String VIEW_PREFIX = "/WEB-INF/views/";
	public static final String VIEW_SUFFIX = ".jsp";
	public static final String ERROR_403_VIEW = "/WEB-INF/views/403.jsp";
	public static final String ERROR_404_VIEW = "/WEB-INF/views/404.jsp";
	public static final String ERROR_500_VIEW = "/WEB-INF/views/500.jsp";
	
	//Entity's fields
	public static final String COMPUTER_ID = "computerId", COMPUTER_NAME = "computerName", COMPUTER_INTRODUCTION_DATE = "introductionDate", 
			COMPUTER_DISCONTINUATION_DATE = "discontinuationDate", COMPUTER_COMPANY_ID = "companyId";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String COMPUTER_FILTERED_BY_NAME = "computerFilteredByName";
	public static final String COMPUTERS_LIST = "computersList";
	public static final String COMPUTERS_NUMBER = "numberOfComputers";
	public static final String COMPANIES_LIST = "companiesList";
	
	//Pagination
	public static final String PAGE = "page";
	public static final String PAGE_DATA = "pageData";
	public static final String LIMIT = "limit";
	
	//Useful constants
	public static final String COMMA_SEPARATOR = ",";
	public static final String SLASH_SEPARATOR = "/";
	public static final String NULL_STRING = null;
	public static final String EMPTY_STRING = "";
	public static final String SPACE_STRING = " ";
	public static final int ZERO = 0;
	
	//Suppress Warnings
	public static final String SERIAL = "serial";
	public static final String RESOURCE = "resource";

	//Internationalization
	public static final String I18N_MESSAGES = "classpath:messages";
	public static final String COOKIE = "cookie";
	public static final String LANG = "lang";
	public static final String ENCODING = "UTF-8";
	public static final String LOCALE = "locale";
	
}