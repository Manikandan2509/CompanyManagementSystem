//$Id$
package com.companymanagement.dto;

import java.sql.Date;

import com.companymanagement.utils.LRUCache;
import com.google.gson.annotations.SerializedName;

public class EmployeesDTO {
	
	//@SerializedName ("emp_id")
	int empID;
	
	//@SerializedName ("org_id")
	int organizationID;
	
	//@SerializedName ("first_name")
	String firstName;
	
	//@SerializedName ("second_name")
	String secondName;
	
	//@SerializedName ("email")
	String email;
	
	//@SerializedName ("date_of_joining")
	String dateOfJoining;
	
	//@SerializedName ("date_of_birth")
	String dateOfBirth;
	
	//@SerializedName ("department_id")
	int departmentID;
	
	//@SerializedName ("password_hash")
	String passwordHash;
	
	//@SerializedName ("role")
	String role;
	
	//@SerializedName ("address1")
	String address1;
	//@SerializedName ("address2")
	String address2;
	//@SerializedName ("city")
	String city;
	//@SerializedName ("state")
	String state;
	//@SerializedName ("pincode")
	int pincode;
	//@SerializedName ("employee_state")
	String employeeState;
	//@SerializedName ("is_org_admin")
	boolean isOrgAdmin = false;
	//@SerializedName ("is_dept_manager")
	boolean isDeptManager = false;
	//@SerializedName ("org_name")
	String orgName;
	public String getEmployeeState() {
		return employeeState;
	}
	public void setEmployeeState(String employeeState) {
		this.employeeState = employeeState;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public boolean isOrgAdmin() {
		return isOrgAdmin;
	}
	public void setOrgAdmin(boolean isOrgAdmin) {
		this.isOrgAdmin = isOrgAdmin;
	}
	public boolean isDeptManager() {
		return isDeptManager;
	}
	public void setDeptManager(boolean isDeptManager) {
		this.isDeptManager = isDeptManager;
	}
	
	public String getEmpStatus() {
//		return empStatus;
		return "active";
	}
	public void setEmpStatus(String empStatus) {
		this.employeeState = empStatus;
	}
	public int getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getRole() {
//		return role;
		return "Admin";
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	
	public String toString() {
		return "EmployeeDTO [empID=" + empID + ", organizationID=" + organizationID + ", firstName=" + firstName + ", secondName=" + secondName + ", email=" + email + ", dateOfJoining=" + dateOfJoining + ", dateOfBirth=" + dateOfBirth + ", departmentID=" + departmentID + ", passwordHash=" + passwordHash + ", role=" + role + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", empStatus=" + employeeState  + "]";
	}
	public void setPincode(int i) {
		this.pincode = i;
	}
	
	
}
