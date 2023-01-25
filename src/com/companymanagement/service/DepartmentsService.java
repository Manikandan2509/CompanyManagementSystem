//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.companymanagement.dao.DepartmentDAO;
import com.companymanagement.dao.EmployeeDAO;
import com.companymanagement.dto.DepartmentsDTO;
import com.google.gson.JsonObject;

public class DepartmentsService implements ServiceInterface{

	
	public String post(Object object, String Query,HashMap<String,Object> map) throws SQLException{
		DepartmentDAO depDAO = new DepartmentDAO();
		DepartmentsDTO depDTO = (DepartmentsDTO) object;
		depDAO.addDepartment(depDTO);
		return null;
	}


	public JsonObject delete(String Query) throws SQLException{
		DepartmentDAO depDAO = new DepartmentDAO();
		depDAO.deleteDepartment();
		return null;
	}

	
	public JsonObject get(String Query) throws SQLException{
		JsonObject jsonObject = new JsonObject();
		DepartmentDAO depDAO = new DepartmentDAO();
		return depDAO.getDepartment();
	}


	public JsonObject put(Object object, String Query) throws SQLException{
		DepartmentDAO depDAO = new DepartmentDAO();
		DepartmentsDTO depDTO = (DepartmentsDTO) object;
		depDAO.putDepartment(depDTO);
		return null;
	}


	
}
