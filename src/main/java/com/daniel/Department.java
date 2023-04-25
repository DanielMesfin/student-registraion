package com.daniel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer departmentId;
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	public List<StudentFile> getStudent() {
		return student;
	}
	public void setStudent(List<StudentFile> student) {
		this.student = student;
	}
	@OneToMany(mappedBy="department")
	private List<StudentFile> student;
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
