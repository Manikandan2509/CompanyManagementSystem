//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.companymanagement.dao.EmployeeDAO;
import com.companymanagement.dto.EmployeesDTO;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class SigninService {
	
	

	public JsonObject post(Object object, String s,HashMap<String,Object> map) throws SQLException {
		EmployeeDAO empDao = new EmployeeDAO();
	
		
		
		//if(object.getClass().getName().equals(EmployeeDTO.class.getName())) {
		
		EmployeesDTO empDto = new EmployeesDTO();
		empDto =(EmployeesDTO) object;
	
		
		return empDao.validateEmployee(empDto);
		
	}
}
