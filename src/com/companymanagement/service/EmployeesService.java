//$Id$
package com.companymanagement.service;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.companymanagement.dao.EmployeeDAO;
import com.companymanagement.dto.EmployeesDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class EmployeesService implements ServiceInterface {
	
	
	
	public JsonObject get(String query) throws SQLException {
			
		
		
		
		JsonObject jsonObject = new JsonObject();
		EmployeeDAO empDAO = new EmployeeDAO();
		System.out.print("GET Employee : ");
		return empDAO.getEmployee();
		
		
	}
	public String post(Object object,String query,HashMap<String,Object> map) {
		
		
		EmployeeDAO empDao = new EmployeeDAO();
		EmployeesDTO empDto = new EmployeesDTO();
		empDto =(EmployeesDTO) object;
		
		
		return  empDao.createEmployee(empDto,map);
		
	}
	public JsonObject put(Object object,String query) {
		
		EmployeeDAO empDAO = new EmployeeDAO();
		EmployeesDTO empDTO = (EmployeesDTO) object;
		//empDAO.createEmployee(empDTO);
		
		return null;
		
		
	}
	public JsonObject delete(JsonArray arr) throws SQLException {

		EmployeeDAO empDAO = new EmployeeDAO();
		if(arr != null || arr.size() > 0) {
			int employeeId = CurrentData.getCurrentEmployee();
			arr = new JsonArray();
			arr.add(employeeId);
		}
		empDAO.deleteEmployee(arr);
		return null;
		
	}
	@Override
	public JsonObject delete(String Query) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
