//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.companymanagement.dao.DependantDAO;
import com.companymanagement.dto.DependantsDTO;
import com.google.gson.JsonObject;

public class DependantsService implements ServiceInterface{


	public String post(Object object, String Query,HashMap<String,Object> map) throws SQLException {
		DependantDAO depDAO = new DependantDAO();
		DependantsDTO depDTO = (DependantsDTO) object;
		depDAO.addDependant(depDTO);
		return null;
	}


	public JsonObject delete(String Query) throws SQLException {
	
		return null;
	}


	public JsonObject get(String Query) throws SQLException {
		
		DependantDAO depDAO = new DependantDAO();
	
		return depDAO.getDependant();
	}

	
	public JsonObject put(Object object, String Query) throws SQLException {
		
		return null;
	}

}
