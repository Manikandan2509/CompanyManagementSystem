//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.companymanagement.dao.OrganizationDAO;
import com.companymanagement.dto.OrganizationsDTO;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class OrgsService implements ServiceInterface {
	
	

	public String post(Object object, String Query,HashMap<String,Object> map) throws SQLException{
		
		OrganizationDAO orgDAO = new OrganizationDAO();
		OrganizationsDTO org = (OrganizationsDTO) object; 
		
	
			orgDAO.createOrganization(org);
	
		
		return null;
	}
	public JsonObject delete(String Query)throws SQLException {
		OrganizationDAO orgDAO = new OrganizationDAO();
		
		
		orgDAO.deleteOrganization(Query);
		
	
		
		return null;
	}
	
	public JsonObject get(String Query) throws SQLException{
				
		OrganizationDAO orgDAO = new OrganizationDAO();
		
		
		
		return orgDAO.getOrganization(Query);
	}


	public JsonObject put(Object json, String Query) {
		OrganizationDAO orgDAO = new OrganizationDAO();
		
		
		//orgDAO.putOrganization(Query);
		return null;
	}

}
