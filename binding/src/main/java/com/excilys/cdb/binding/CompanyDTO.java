package main.java.com.excilys.cdb.binding;

public class CompanyDTO {
	
	public int companyId;
	public String companyName;
	
	public CompanyDTO() {
		
	}
	/**
	 * @return the id
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param id the id to set
	 */
	public void setCompanyId(int id) {
		this.companyId = id;
	}
	/**
	 * @return the name
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param name the name to set
	 */
	public void setCompanyName(String name) {
		this.companyName = name;
	}

}