package com.daniel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Generated;
 // this is for the persistence javax entity
@javax.persistence.Entity
public class StudentFile {
	@Id   
@GeneratedValue(strategy = GenerationType.IDENTITY)
     private	 Integer id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	
    private Address address;
	@ManyToOne
	private Department department;
	
	@ManyToMany
	@JoinTable(
			name ="Student_Project",
	         joinColumns={@JoinColumn(name="Student_id")},
	         inverseJoinColumns = {@JoinColumn(name="Project_id")}
	)
	Set<Project> project=new HashSet<Project>();

	public Set<Project> getProject() {
		return project;
	}
	public void setProject(Set<Project> project) {
		this.project = project;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
