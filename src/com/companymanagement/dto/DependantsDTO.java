//$Id$
package com.companymanagement.dto;

import com.google.gson.annotations.SerializedName;

public class DependantsDTO {
	//@SerializedName ("dependant_id")
	int dependantID;
	//@SerializedName ("name")
	String dependantName;
	//@SerializedName ("relationship")
	String relationship;
	//@SerializedName ("gender")
	String gender;
	//@SerializedName ("emp_id")
	int empID;
	//@SerializedName ("dependant_status")
	String dependantStatus;
	public int getDependantID() {
		return dependantID;
	}
	public void setDependantID(int dependantID) {
		this.dependantID = dependantID;
	}
	public String getDependantName() {
		return dependantName;
	}
	public void setDependantName(String dependantName) {
		this.dependantName = dependantName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getDependantStatus() {
		return dependantStatus;
	}
	public void setDependantStatus(String dependantStatus) {
		this.dependantStatus = dependantStatus;
	}
	
}
