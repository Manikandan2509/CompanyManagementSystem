//$Id$
package com.companymanagement.dto;

import com.google.gson.annotations.SerializedName;

public class LocationsDTO {
	//@SerializedName ("location_id")
 int locationID;
	//@SerializedName ("city")
 String city;
	//@SerializedName ("state")
 String state;
	//@SerializedName ("zip")
 int zip;
public int getLocationID() {
	return locationID;
}
public void setLocationID(int locationID) {
	this.locationID = locationID;
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
public int getZip() {
	return zip;
}
public void setZip(int zip) {
	this.zip = zip;
}
}
