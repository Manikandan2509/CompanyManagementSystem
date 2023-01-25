//$Id$
package com.companymanagement.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.companymanagement.dao.DepartmentDAO;
import com.companymanagement.dao.EmployeeDAO;
import com.companymanagement.dao.OrganizationDAO;
import com.companymanagement.dto.DepartmentsDTO;
import com.companymanagement.dto.EmployeesDTO;
import com.companymanagement.dto.OrganizationsDTO;
import com.companymanagement.utils.DbConnection;
import com.google.gson.JsonObject;

public class SignupService implements ServiceInterface{


	public String post(Object object, String Query,HashMap<String,Object> map) throws SQLException {
		
		Connection conn = DbConnection.getCon();
		try {
			EmployeeDAO empDAO = new EmployeeDAO();
			EmployeesDTO empDTO = (EmployeesDTO) object;
			System.out.print(empDTO.toString());
			conn.setAutoCommit(false);
			OrganizationDAO orgDAO = new OrganizationDAO();
			OrganizationsDTO orgDTO = new OrganizationsDTO();
			orgDTO.setOrganizationName(empDTO.getOrgName());
			orgDAO.createOrganization(orgDTO);
	
	//		
			DepartmentDAO deptDAO = new DepartmentDAO();
			DepartmentsDTO deptDTO = new DepartmentsDTO();
			deptDTO.setDepartmentName("Main Department");
			deptDTO.setDepartmentState("active");
			deptDTO.setOrgID(orgDTO.getOrganizationID());
			deptDAO.addDepartment(deptDTO);
	
//			throw new SQLException("Error");
//			System.out.print("hello");
			
			
			empDTO.setOrganizationID(orgDTO.getOrganizationID());
			empDTO.setDepartmentID(deptDTO.getDepartmentID());
			empDTO.setOrgAdmin(true);
			empDTO.setDeptManager(true);
			empDAO.createEmployee(empDTO,map);
//			
//			System.out.print(empDTO.toString());
			conn.commit();
			conn.close();
		
		}
		catch(Exception e) {
			conn.rollback();
			throw e;
		}
		return null;
		
		
	}


	public JsonObject delete(String Query) {
	
		return null;
	}


	public JsonObject get(String Query) {
		
		return null;
	}

	
	public JsonObject put(Object object, String Query) {
	
		return null;
	}
	
}
