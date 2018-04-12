package main.java.com.excilys.cdb.core.model;

import static main.java.com.excilys.cdb.core.constants.Constants.*
;
import java.sql.Timestamp;


import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = COMPUTER)
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID)
	private int id;
	
	@Column(name = NAME)
	private String name;
	
	@Column(name = INTRODUCTION_DATE)
	private Timestamp introductionDate;
	
	@Column(name = DISCONTINUATION_DATE)
	private Timestamp discontinuationDate;
	
	@ManyToOne(optional = true)
	@PrimaryKeyJoinColumn
	private Company company;
	
	private int companyId;
	private String companyName;
	
	public Computer() {
		
	}
	
	public Computer(String name, Timestamp introductionDate, Timestamp discontinuationDate, int companyId) {
		this.name = name;
		this.introductionDate = introductionDate;
		this.discontinuationDate = discontinuationDate;
		this.companyId = companyId;	
	}
	
	public Computer(int computerId, String computerName, Timestamp computerIntroductionDate, Timestamp computerDiscontinuationDate, Company company) {	
		this.id = computerId;
		this.name = computerName;
		this.introductionDate = computerIntroductionDate;
		this.discontinuationDate = computerDiscontinuationDate;
		this.company = company;
	}
	
	public Computer(int computerId, String computerName, Timestamp computerIntroductionDate, Timestamp computerDiscontinuationDate, int companyId) {	
		this.id = computerId;
		this.name = computerName;
		this.introductionDate = computerIntroductionDate;
		this.discontinuationDate = computerDiscontinuationDate;
		this.companyId = companyId;	
	}
	
	public int getId() {
	    return id;
	  }

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Timestamp getIntroductionDate() {
		return introductionDate;
	}
	
	public void setIntroductionDate(Timestamp introductionDate) {
		this.introductionDate = introductionDate;
	}
	  
	public Timestamp getDiscontinuationDate() {
		return discontinuationDate;
	}
	
	public void setDiscontinuationDate(Timestamp discontinuationDate) {
		this.discontinuationDate = discontinuationDate;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
	public String toString() { 
	    return "\n" + "ID : " + this.id + ",  NAME: " + this.name + ",  INTRODUCTION DATE: " + this.introductionDate
	    		+ ",  DISCONTINUATION DATE: " + this.discontinuationDate + ",  COMPANY: " + this.company.getName() + this.company.getId();
	} 
	
}