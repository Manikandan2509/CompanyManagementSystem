//$Id$
package com.companymanagement.dto;

import com.google.gson.annotations.SerializedName;

public class OrganizationsDTO {
	//@SerializedName ("organization_id")
	int organizationID;
	//@SerializedName ("organization_name")
	String organizationName;
	//@SerializedName ("organization_state")
	String organizationState;
	
	public String toString() {
		return "OrganizationDTO [organizationID=" + organizationID + ", organizationName=" + organizationName + ", organizationState=" + organizationState + "]";
	}
	public int getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationState() {
		return "active";
//		return organizationState;
	}
	public void setOrganizationState(String organizationState) {
		this.organizationState = organizationState;
	}
}
