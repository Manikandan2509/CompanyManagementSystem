//$Id$
package com.companymanagement.dto;

import com.google.gson.annotations.SerializedName;

public class ProjectsDTO {
	//@SerializedName ("project_id")
	int projectID;
	//@SerializedName ("project_name")
	String projectName;
	//@SerializedName ("revenue")
	int revenue;
	//@SerializedName ("department_id")
	int departmentID;
	//@SerializedName ("project_status")
	String projectStatus;
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	String departmentStatus;
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentStatus() {
		return departmentStatus;
	}
	public void setDepartmentStatus(String departmentStatus) {
		this.departmentStatus = departmentStatus;
	}
	
}
