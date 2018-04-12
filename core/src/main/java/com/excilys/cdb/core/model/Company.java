package main.java.com.excilys.cdb.core.model;

import static main.java.com.excilys.cdb.core.constants.Constants.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = COMPANY)
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	public Company() {
		
	}
	
	public Company(int companyId, String companyName) {
		this.id =companyId;
		this.name =companyName;
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
	
	@Override
	public String toString() { 
	    return "\n" + "Id : " + this.id + ", Name : " + this.name;
	}
}