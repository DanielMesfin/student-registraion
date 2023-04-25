package com.daniel;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	@Column(name="PROJECT_NAME")
	private String projectName;
	@ManyToMany(mappedBy="project")
    private Set<StudentFile> setudents;
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Set<StudentFile> getSetudents() {
		return setudents;
	}
	public void setSetudents(Set<StudentFile> setudents) {
		this.setudents = setudents;
	}
	
}
