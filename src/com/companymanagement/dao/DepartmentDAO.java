//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.companymanagement.dto.DepartmentsDTO;
import com.companymanagement.dto.OrganizationsDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class DepartmentDAO {
	Connection con;
	public DepartmentDAO()
	{
		con = DbConnection.getCon();
	}
	String tableName = "departments"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	public void addDepartment(DepartmentsDTO departmentDTO) throws SQLException{
		
		String query = "INSERT INTO departments(department_name,department_state,org_id) \n"
				+ "values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, departmentDTO.getDepartmentName());
		ps.setString(2,departmentDTO.getDepartmentState());
		ps.setInt(3, departmentDTO.getOrgID());
		if(CurrentData.getCurrentOrg() != -1) {
			ps.setInt(3, CurrentData.getCurrentOrg());
		}
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		departmentDTO.setDepartmentID(rs.getInt("department_id"));
	
		
	}
	public JsonObject getDepartment()  throws SQLException {

		int orgId = CurrentData.getCurrentOrg();
		int employeeId = CurrentData.getCurrentEmployee();
		int departmentId = CurrentData.getCurrentDepartment();
		//Pagination using LIMIT and OFFSET
		GenerateQuery gq = new GenerateQuery();
		if( departmentId == -1) {
			getCondition.add("org_id");
		}
		else {
			getCondition.add("org_id");
			getCondition.add("department_id");
		}
		//System.out.println(employeeId);
		//Pagination using LIMIT and OFFSET
		String query =  gq.selectquery(tableName, getColumns , getCondition);
		System.out.println(query);
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		
		ps.setInt(1, orgId);
		if(departmentId != -1) {
			ps.setInt(2, departmentId);
			//Generator.where(employeeId.equals(id))
		}
		ResultSet rs = ps.executeQuery();
		return Utility.convertToJSON(rs);
		
	}
	public JsonObject deleteDepartment()  throws SQLException{

		int orgId = CurrentData.getCurrentOrg();
		int employeeId = CurrentData.getCurrentEmployee();
		int departmentId = CurrentData.getCurrentDepartment();
		//Pagination using LIMIT and OFFSET
		String query = departmentId != -1 ? "DELETE * from departments where org_id=? and department_id=?" : "Delete * FROM departments where org_id=?";
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, orgId);
		if(departmentId != -1) {
			ps.setInt(2, departmentId);
			//Generator.where(employeeId.equals(id))
		}
		ResultSet rs = ps.executeQuery();
		return Utility.convertToJSON(rs);
	}
	public JsonObject putDepartment(DepartmentsDTO depDTO) throws SQLException{
		

		int orgId = CurrentData.getCurrentOrg();
		int employeeId = CurrentData.getCurrentEmployee();
		//Pagination using LIMIT and OFFSET
		String query = employeeId != -1 ? "insert into table department where org_id=? and department_id=?" : "SELECT * FROM department where org_id=?";
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, orgId);
		if(employeeId != -1) {
			ps.setInt(2, employeeId);
			//Generator.where(employeeId.equals(id))
		}
		ResultSet rs = ps.executeQuery();
		return Utility.convertToJSON(rs);
		
	}

}
