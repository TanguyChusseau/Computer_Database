package main.java.com.excilys.cdb.binding;

public class ComputerDTO {

	private int computerId;
	private String computerName;
	private String computerIntroductionDate;
	private String computerDiscontinuationDate;
	private CompanyDTO companyDTO;
	private String companyId;
	private String companyName;

	public ComputerDTO() {
		
	}
	/**
	 * @return the id
	 */
	public int getComputerId() {
		return computerId;
	}
	/**
	 * @param id the id to set
	 */
	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}
	/**
	 * @return the name
	 */
	public String getComputerName() {
		return computerName;
	}
	/**
	 * @param name the name to set
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	/**
	 * @return the introduction date
	 */
	public String getComputerIntroductionDate() {
		return computerIntroductionDate;
	}
	/**
	 * @param introductionDate the introduction date to set
	 */
	public void setComputerIntroductionDate(String introductionDate) {
		this.computerIntroductionDate = introductionDate;
	}
	/**
	 * @return the discontinuation date
	 */
	public String getComputerDiscontinuationDate() {
		return computerDiscontinuationDate;
	}
	/**
	 * @param discontinuationDate the discontinuation date to set
	 */
	public void setComputerDiscontinuationDate(String discontinuationDate) {
		this.computerDiscontinuationDate = discontinuationDate;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

}