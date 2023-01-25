//$Id$
package com.companymanagement.dto;

import com.google.gson.annotations.SerializedName;

public class DepartmentsDTO {
	//@SerializedName ("department_id")
	int departmentID;
	//@SerializedName ("department_name")
	String departmentName;
	//@SerializedName ("department_state")
	String departmentState;
	//@SerializedName ("org_id")
	int orgID;
	public int getOrgID() {
		return orgID;
	}
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
	
	
	@Override
	public String toString() {
		return "DepartmentDTO [departmentID=" + departmentID + ", departmentName=" + departmentName + ", departmentState=" + departmentState + ", orgID=" + orgID + "]";
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getDepartmentState() {
		return departmentState;
	}
	public void setDepartmentState(String departmentState) {
		this.departmentState = departmentState;
	}
	
}
